package com.dmslob.functional.functors;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FunctorTest {

    @Test
    public void should_string_identity() {
        // given
        Identity<String> idString = new Identity<>("abc");
        // when
        Identity<Integer> idInt = idString.map(String::length);
        // then
        assertThat(idInt.toString())
                .isEqualTo("3");
    }

    @Test
    public void should_map_street_identity() {
        // given
        var address = new Address("Street 23/2", "London");
        var customer = new Customer(address);
        // when
        Identity<String> street = new Identity<>(customer)
                .map(Customer::address)
                .map(Address::street)
                .map(String::toLowerCase);
        // then
        assertThat(street.toString())
                .isEqualTo("street 23/2");
    }

    @Test
    public void should_test() {
        // given
        var a1 = new Address("Street 23/2", "London");
        var a2 = new Address("Street 25/2", "London");
        var customer_1 = new Customer(a1);
        var customer_2 = new Customer(a2);
        FList<Customer> customers = new FList<>(Arrays.asList(customer_1, customer_2));
        // when
        var streets = customers
                .map(Customer::address)
                .map(address -> ((Address) address).street);
        // then
        System.out.println(streets);
    }

    record Address(String street, String city) {
    }

    record Customer(Address address) {
    }
}
