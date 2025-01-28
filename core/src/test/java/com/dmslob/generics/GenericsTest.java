package com.dmslob.generics;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GenericsTest {

    @Test
    public void should_create_generic_records() {
        // given
        record GenericEntry<T> (T data, int rank) {}
        // when
        var integerEntry = new GenericEntry<>(1000, 1);
        var doubleEntry = new GenericEntry<>(120.78d, 1);
        // then
        assertThat(integerEntry.data()).isInstanceOf(Integer.class);
        assertThat(doubleEntry.data()).isInstanceOf(Double.class);
    }

    @SafeVarargs // possible heap pollution
    public final <T> List<T> toList(T... values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    @Test
    public void should_create_list_when_parameterized_varargs() {
        // when
        List<String> strings = toList("item-1", "item-2", "item-3");
        List<Integer> integers = toList(1, 2, 3, 4);
        // then
        assertThat(strings).isEqualTo(List.of("item-1", "item-2", "item-3"));
        assertThat(integers).isEqualTo(List.of(1, 2, 3, 4));
    }


    @Test
    public void should_copy_integers_to_numbers() {
        // given
        List<Number> nums = Arrays.asList(4.1F, 0.2F);
        List<Integer> ints = Arrays.asList(1, 2);
        // when
        Collections.copy(nums, ints);
        //Collections.copy(ints, nums); // Compile time error
        // then
        assertThat(nums)
                .hasSize(2)
                .isEqualTo(List.of(1, 2));
    }

    @Test
    public void should_use_generic_interface() {
        // given
        Holder<Product> cameraHolder = new CameraHolder();
        cameraHolder.save(new Camera("Sony"));
        cameraHolder.save(new Camera("Cannon"));

        // when
        List<Product> cameras = cameraHolder.get();
        // then
        assertThat(cameras)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    public void should_infer_target_type() {
        List<String> strings = Collections.emptyList();
        // when we use var -> we need to specify target type
        var ints = Collections.<Integer>emptyList();
        // if not -> it will be List<Object> objects
        var objects = Collections.emptyList();
    }

    @Test
    public void should_inference_generic_methods() {
        // given
        List<Box<Integer>> listOfIntegerBoxes = new ArrayList<>();
        // when
        BoxDemo.addBox(10, listOfIntegerBoxes);
        BoxDemo.addBox(20, listOfIntegerBoxes);
        BoxDemo.addBox(30, listOfIntegerBoxes);
        // then
        BoxDemo.outputBoxes(listOfIntegerBoxes);
    }

    String foo(Object o) {
        return "Object: " + o;
    }

    String foo(String s) {
        return "String: " + s;
    }

    <T> String generic(T t) {
        return foo(t);
    }

    @Test
    public void should_return_object_when_call_generic() {
        // when
        var result = generic("Test");
        // then
        assertThat(result).isEqualTo("Object: Test");
    }
}

abstract class Product {
    protected String name;
}

class Camera extends Product {
    Camera(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "name='" + name + '\'' +
                '}';
    }
}

interface Holder<T extends Product> {
    void save(T t);

    List<T> get();
}

class CameraHolder implements Holder<Product> {
    private final List<Product> cameras = new LinkedList<>();

    @Override
    public void save(Product camera) {
        cameras.add(camera);
    }

    @Override
    public List<Product> get() {
        return cameras;
    }
}

class Box<T> {
    private T item;

    public void set(T t) {
        this.item = t;
    }

    public T get() {
        return item;
    }
}

class BoxDemo {
    public static <U> void addBox(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<>();
        box.set(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box : boxes) {
            U boxContents = box.get();
            System.out.println("Box #" + counter + " contains [" +
                    boxContents.toString() + "]");
            counter++;
        }
    }
}