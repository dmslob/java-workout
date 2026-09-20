package com.dmslob.tasks;

import org.testng.annotations.Test;

import java.util.BitSet;

public class PoisonedWineDetectorTest {

    /**
     * Calculates the minimum number of test subjects needed for N bottles.
     */
    public int getRequiredSubjects(int totalBottles) {
        return (int) Math.ceil(Math.log(totalBottles) / Math.log(2));
    }

    /**
     * Simulates the test process and identifies the poisoned bottle ID.
     *
     * @param totalBottles     Total number of wine bottles
     * @param poisonedBottleId The actual poisoned bottle (0-indexed)
     * @return The detected poisoned bottle ID reconstructed from subject results
     */
    public int detectPoisonedBottle(int totalBottles, int poisonedBottleId) {
        if (poisonedBottleId < 0 || poisonedBottleId >= totalBottles) {
            throw new IllegalArgumentException("Poisoned bottle ID out of valid range.");
        }
        int subjectCount = getRequiredSubjects(totalBottles);
        // Step 1: Simulate consumption and determine which subjects die
        BitSet deadSubjects = new BitSet(subjectCount);
        for (int bit = 0; bit < subjectCount; bit++) {
            // Subject 'bit' dies if the corresponding bit in poisonedBottleId is set (1)
            boolean drankPoison = ((poisonedBottleId >> bit) & 1) == 1;
            if (drankPoison) {
                deadSubjects.set(bit);
            }
        }
        // Step 2: Reconstruct bottle ID from dead subjects (BitSet to int)
        int detectedId = 0;
        for (int bit = 0; bit < subjectCount; bit++) {
            if (deadSubjects.get(bit)) {
                detectedId |= (1 << bit);
            }
        }
        return detectedId;
    }

    @Test
    public void should_detect_poisoned_bottle() {
        // given
        int totalBottles = 1000;
        int targetPoisonedBottle = 100; // Example secret poisoned bottle (0 to 999)
        int subjects = getRequiredSubjects(totalBottles);
        int detected = detectPoisonedBottle(totalBottles, targetPoisonedBottle);
        System.out.println("--- Poisoned Wine Detection Test ---");
        System.out.println("Total Bottles: " + totalBottles);
        System.out.println("Test Subjects Required: " + subjects);
        System.out.println("Target Poisoned Bottle ID: " + targetPoisonedBottle);
        System.out.println("Detected Poisoned Bottle ID: " + detected);
        System.out.println("Result: " + (detected == targetPoisonedBottle ? "SUCCESS" : "FAILURE"));
    }
}