package com.dmslob.java17.records;

import lombok.extern.slf4j.Slf4j;

/**
 * - Is an abstract class
 * - A record class declaration does not have an extends clause.
 * - A record class is implicitly final, and cannot be abstract
 * - The fields derived from the record components are final.
 * - A record class cannot explicitly declare instance fields,
 * and cannot contain instance initializers.
 * - Any explicit declarations of a member that would otherwise be automatically derived
 * must match the type of the automatically derived member exactly,
 * disregarding any annotations on the explicit declaration.
 * Any explicit implementation of accessors or the equals or hashCode methods should
 * be careful to preserve the semantic invariants of the record class.

 * - Can be declared top level or nested, and can be generic.
 * - Can declare static methods, fields, and initializers.
 * - Can declare instance methods.
 * - Can implement interfaces
 * - Can declare nested types, including nested record classes
 * - Record class, and the components in its header, may be decorated with annotations.
 * Any annotations on the record components are propagated to the automatically derived fields,
 * methods, and constructor parameters, according to the set of applicable targets for the annotation.
 * - Instances of record classes can be serialized and deserialized.
 * - Records Can't Be JPA/Hibernate Entities, proxies rely on the entity class to have a no-args constructor
 *   and setters. Since records don't have these, they can't be used as entities.
 * According to the JPA specification, an entity must follow these requirements:
 *      - the entity class needs to be non-final,
 *      - the entity class needs to have a no-arg constructor that is either public or protected,
 *      - the entity attributes must be non-final.
 *   Other Ways to Use Records with JPA
 *   - Convert the results of a query to a record
 *   - Use records as DTOs to transfer data between layers
 *   - Convert entities to records.
 *
 */
@Slf4j
public record Point(int x, int y) {
    // private final int a = 0; // Compile error - instance field is not allowed here
//    public Point() { // Compile error
//    }

    // Compact constructor
    public Point {
        log.info("params x={}, y={}", x, y);
        log.info("fields x={}, y={}", this.x(), this.y());

        if (x > y)  // referring here to the implicit constructor parameters
            throw new IllegalArgumentException(String.format("(%d,%d)", x, y));
    }

    static {
        // OK
    }

    // implicitly-static
    interface A {}
    enum B {}
    record Foo() {}

    public int x() {
        return this.x > 0 ? this.x : -1;
    }

    public int getX() {
        return this.x > 0 ? this.x : -1;
    }

    static class SubType {

    }

    private static record Type() implements AutoCloseable {
        @Override
        public void close() {

        }
    }
}
