package com.dmslob.java21;

import org.testng.annotations.Test;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.ByteBuffer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ForeignFunctionAndMemoryApiTest {

    @Test
    public void should_test_Arena_api() {
        // An arena is responsible for managing the lifecycle of native memory segments
        // offering flexible memory allocation, and ensuring proper deallocation when no longer needed.

        // The global arena has an unbounded lifetime and cannot be closed manually
        Arena globalArena = Arena.global();
        MemorySegment segment = globalArena.allocate(100);

        // An automatic arena has a bounded lifetime managed by the garbage collector and will be cleared
        // when the arena and all its allocated segments become unreachable
        Arena autoArena = Arena.ofAuto();
        segment = autoArena.allocate(100);

        // A confined arena has a bounded lifetime and restricts access to the creating thread.
        Arena confinedArena = Arena.ofConfined();
        segment = confinedArena.allocate(100);

        // A shared arena has a bounded lifetime and allows access from multiple threads.
        Arena sharedArena = Arena.ofShared();
        segment = sharedArena.allocate(100);

        String s = "My string";
        MemorySegment ms;
        try (Arena arena = Arena.ofConfined()) {
            // Allocate off-heap memory
            MemorySegment nativeText = arena.allocateUtf8String(s);
            ms = nativeText;
            // Access off-heap memory
            for (int i = 0; i < s.length(); i++) {
                System.out.print((char) nativeText.get(ValueLayout.JAVA_BYTE, i));
            }
        } // Off-heap memory is deallocated
        // // Exception in thread "main" java.lang.IllegalStateException: Already closed
        System.out.print((char) ms.get(ValueLayout.JAVA_BYTE, 1));
    }

    @Test
    public void should_test_MemorySegment() {
        // A memory segment is a contiguous region of memory. This can be either heap or off-heap memory.
        // A memory segment backed by native memory is known as a native memory segment.
        MemorySegment memorySegment = Arena.ofAuto().allocate(200);

        // A memory segment can also be backed by an existing heap-allocated Java array.
        memorySegment = MemorySegment.ofArray(new long[100]);

        // can be backed by an existing Java ByteBuffer
        memorySegment = MemorySegment.ofBuffer(ByteBuffer.allocateDirect(200));
    }

    @Test
    public void should_test_strlen() throws Throwable {
        // The following example calls strlen with the Foreign Function and Memory API:
        System.out.println(invokeStrlen("Test"));
    }

    long invokeStrlen(String s) throws Throwable {
        try (Arena arena = Arena.ofConfined()) {
            // Allocate off-heap memory and
            // copy the argument, a Java string, into off-heap memory
            MemorySegment nativeString = arena.allocateUtf8String(s);
            // Link and call the C function strlen
            // Obtain an instance of the native linker
            var linker = Linker.nativeLinker();
            // Locate the address of the C function signature
            var stdLib = linker.defaultLookup();
            // size_t strlen(const char *s);
            MemorySegment strlen_addr = stdLib.find("strlen").get();
            // Create a description of the C function
            var strlen_sig = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
            // Create a downcall handle for the C function
            MethodHandle strlen = linker.downcallHandle(strlen_addr, strlen_sig);
            // Call the C function directly from Java
            return (long) strlen.invokeExact(nativeString);
        }
    }

    @Test
    public void should_test_ValueLayout() {
        // A ValueLayout models a memory layout for basic data types such as integer and floating types.
        // Each ValueLayout has a size and a byte order.
        var intLayout = ValueLayout.JAVA_INT;
        var charLayout = ValueLayout.JAVA_CHAR;

        assertThat(intLayout.byteSize()).isEqualTo(4);
        assertThat(charLayout.byteSize()).isEqualTo(2);
    }

    @Test
    public void should_test_MethodHandle() throws Throwable {
        // The MethodHandle class serves as a bridge between Java and foreign functions,
        // representing a reference to a function that can be invoked from Java code.
        // It allows dynamic binding and invocation of foreign functions
        // with appropriate arguments and return values.
        var linker = Linker.nativeLinker();
        var symbol = linker.defaultLookup().find("strlen").orElseThrow();
        MethodHandle strlen = linker.downcallHandle(symbol,
                FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS));
        Arena arena = Arena.ofAuto();
        MemorySegment str = arena.allocateUtf8String("Hello");
        long len = (long) strlen.invoke(str);

        assertThat(5).isEqualTo(len);
    }
}
