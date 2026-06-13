package com.dmslob.tasks;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * It is initialized with a set of bills and dispenses bills up to a specified amount or refuses.
 * When dispensing, bills are debited from the balance.
 * Denominations:
 * - 50, 100, 500, 1000, 5000 ₽
 * - 20, 100, 500 EUR
 * - Currencies are processed separately; exchange is not supported.
 * - The ATM can be used multithreaded (dispensing reservations).
 * - Multi-threading support can be implemented in a separate iteration.
 */
public class AtmTest {

    enum Currency {
        UAH, EUR
    }

    static class AtmRefusedException extends RuntimeException {
        public AtmRefusedException(String message) {
            super(message);
        }
    }

    static class Atm {
        // Inventory: Currency -> (Denomination -> Count)
        // TreeMap with reverse order ensures we process largest bills first (Greedy approach)
        private final Map<Currency, TreeMap<Integer, Integer>> inventory;
        // Locks per currency to support concurrent dispensing reservations
        private final Map<Currency, ReentrantLock> locks;

        public Atm() {
            inventory = new ConcurrentHashMap<>();
            locks = new ConcurrentHashMap<>();

            for (Currency c : Currency.values()) {
                inventory.put(c, new TreeMap<>(Collections.reverseOrder()));
                locks.put(c, new ReentrantLock());
            }
        }

        /**
         * Initializes or adds bills to the ATM.
         */
        public void loadBills(Currency currency, int denomination, int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("Bill count must be positive.");
            }
            validateDenomination(currency, denomination);
            locks.get(currency).lock();
            try {
                Map<Integer, Integer> bills = inventory.get(currency);
                bills.put(denomination, bills.getOrDefault(denomination, 0) + count);
            } finally {
                locks.get(currency).unlock();
            }
        }

        /**
         * Attempts to dispense the specified amount.
         *
         * @return A map of dispensed denominations and their counts.
         * @throws AtmRefusedException if exact change cannot be made or funds are insufficient.
         */
        public Map<Integer, Integer> withdraw(Currency currency, int amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            locks.get(currency).lock();
            try {
                TreeMap<Integer, Integer> bills = inventory.get(currency);
                Map<Integer, Integer> toDispense = new HashMap<>();
                int remainingAmount = amount;
                // Greedy algorithm: Try to fulfill the amount with the largest available denominations first
                for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
                    int denom = entry.getKey();
                    int availableCount = entry.getValue();
                    if (remainingAmount >= denom && availableCount > 0) {
                        int neededBills = remainingAmount / denom;
                        int billsToTake = Math.min(neededBills, availableCount);
                        toDispense.put(denom, billsToTake);
                        remainingAmount -= (billsToTake * denom);
                    }
                }
                // Check if we successfully matched the exact requested amount
                if (remainingAmount == 0) {
                    // Debit the bills from the actual balance
                    for (Map.Entry<Integer, Integer> entry : toDispense.entrySet()) {
                        int denom = entry.getKey();
                        int countToDeduct = entry.getValue();
                        bills.put(denom, bills.get(denom) - countToDeduct);
                    }
                    return toDispense;
                } else {
                    throw new AtmRefusedException("Refused: Cannot dispense exact amount with available denominations.");
                }
            } finally {
                locks.get(currency).unlock();
            }
        }

        /**
         * Utility to check the total balance of a specific currency.
         */
        public int getBalance(Currency currency) {
            locks.get(currency).lock();
            try {
                return inventory.get(currency).entrySet().stream()
                        .mapToInt(e -> e.getKey() * e.getValue())
                        .sum();
            } finally {
                locks.get(currency).unlock();
            }
        }

        private void validateDenomination(Currency currency, int denomination) {
            List<Integer> validUAH = List.of(50, 100, 500, 1000, 5000);
            List<Integer> validEur = List.of(20, 100, 500);

            if (currency == Currency.UAH && !validUAH.contains(denomination)) {
                throw new IllegalArgumentException("Invalid UAH denomination: " + denomination);
            }
            if (currency == Currency.EUR && !validEur.contains(denomination)) {
                throw new IllegalArgumentException("Invalid EUR denomination: " + denomination);
            }
        }
    }

    private Atm atm;

    @BeforeMethod
    public void setUp() {
        atm = new Atm();
    }

    @Test
    public void test_successful_withdrawal_uah() {
        // given
        atm.loadBills(Currency.UAH, 5000, 2);
        atm.loadBills(Currency.UAH, 1000, 5);
        atm.loadBills(Currency.UAH, 500, 10);
        atm.loadBills(Currency.UAH, 100, 20);
        // when
        // Withdraw 6600: Expect 1x5000, 1x1000, 1x500, 1x100
        Map<Integer, Integer> dispensed = atm.withdraw(Currency.UAH, 6600);
        // then
        Assert.assertEquals((int) dispensed.get(5000), 1);
        Assert.assertEquals((int) dispensed.get(1000), 1);
        Assert.assertEquals((int) dispensed.get(500), 1);
        Assert.assertEquals((int) dispensed.get(100), 1);
        // Original balance was 22000. 22000 - 6600 = 15400
        Assert.assertEquals(atm.getBalance(Currency.UAH), 15400);
    }

    @Test
    public void test_successful_withdrawal_eur() {
        // given
        atm.loadBills(Currency.EUR, 500, 1);
        atm.loadBills(Currency.EUR, 100, 3);
        atm.loadBills(Currency.EUR, 20, 10);
        // when
        // Withdraw 160: Expect 1x100, 3x20
        Map<Integer, Integer> dispensed = atm.withdraw(Currency.EUR, 160);
        // then
        Assert.assertEquals((int) dispensed.get(100), 1);
        Assert.assertEquals((int) dispensed.get(20), 3);
        Assert.assertNull(dispensed.get(500));
        Assert.assertEquals(atm.getBalance(Currency.EUR), 840);
    }

    @Test(expectedExceptions = AtmRefusedException.class)
    public void test_refusal_insufficient_funds() {
        // given
        atm.loadBills(Currency.UAH, 1000, 1);
        // when && then
        atm.withdraw(Currency.UAH, 5000); // Throws AtmRefusedException
    }

    @Test(expectedExceptions = AtmRefusedException.class)
    public void test_refusal_cannot_make_exact_change() {
        // given
        atm.loadBills(Currency.EUR, 100, 5);
        atm.loadBills(Currency.EUR, 500, 2);
        // when && then
        // Total balance is 1500, but we cannot dispense exactly 120 (no 20s available)
        atm.withdraw(Currency.EUR, 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_invalid_denomination() {
        // 300 is not a valid EUR denomination
        atm.loadBills(Currency.EUR, 300, 5);
    }

    @Test
    public void test_currencies_are_separated() {
        // given
        atm.loadBills(Currency.UAH, 500, 2); // 1000 RUB
        atm.loadBills(Currency.EUR, 500, 2); // 1000 EUR
        // when
        atm.withdraw(Currency.UAH, 500);
        // then
        Assert.assertEquals(atm.getBalance(Currency.UAH), 500);
        Assert.assertEquals(atm.getBalance(Currency.EUR), 1000); // EUR balance is unaffected
    }

    @Test
    public void test_multi_threaded_withdrawal() throws InterruptedException {
        // given
        // Load 10,000 UAH total (100 bills of 100 RUB)
        atm.loadBills(Currency.UAH, 100, 100);
        int threads = 100;
        AtomicInteger successfulWithdrawals;
        try (var executor = Executors.newFixedThreadPool(10)) {
            var latch = new CountDownLatch(threads);
            successfulWithdrawals = new AtomicInteger(0);
            // 100 concurrent requests trying to withdraw 100 UAH each
            for (int i = 0; i < threads; i++) {
                executor.submit(() -> {
                    try {
                        // when
                        atm.withdraw(Currency.UAH, 100);
                        successfulWithdrawals.incrementAndGet();
                    } catch (AtmRefusedException e) {
                        // Ignored for this test, though none should fail
                    } finally {
                        latch.countDown();
                    }
                });
            }
            // Wait for all threads to finish
            latch.await(5, TimeUnit.SECONDS);
            executor.shutdown();
        }
        // then
        // Verify the ATM processed exactly 100 withdrawals without race conditions
        Assert.assertEquals(successfulWithdrawals.get(), 100);
        Assert.assertEquals(atm.getBalance(Currency.UAH), 0);
    }
}
