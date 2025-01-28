package com.dmslob.generics;

public class ExtendsInterfaceTest {

}

interface Bar<T> {
}

interface SpecificBar {
}

// OK
class Foo<T extends Bar<T> & SpecificBar> {
}

// compile error, Interface has to be only one
// class Foo1<T extends Bar<T> & Bar<Object> & Bar1> { }