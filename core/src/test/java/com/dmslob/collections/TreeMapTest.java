package com.dmslob.collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Comparator;
import java.util.TreeMap;

import org.testng.annotations.Test;

public class TreeMapTest {

	@Test
	public void should_throw_ClassCastException_when_key_is_not_comparable() {
		// given
		User bob = new User("Bob", 23);
		var users = new TreeMap<User, String>(Comparator.comparingInt(o -> o.age));
		// when | then
		assertThatThrownBy(() -> users.put(bob, "token"))
				.isInstanceOf(ClassCastException.class);
	}

	@Test
	public void should_throw_NullPointerException_when_key_is_null() {
		// given
		User bob = null;
		var users = new TreeMap<User, String>();
		// when | then
		assertThatThrownBy(() -> users.put(bob, "token"))
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void should_put_users_by_comparator() {
		// given
		User bob = new User("Bob", 25);
		User rob = new User("Rob", 23);
		User kevin = new User("Kevin", 18);
		var users = new TreeMap<User, String>(Comparator.comparingInt(o -> o.age));

		// when
		users.put(bob, "bob_token");
		users.put(rob, "rob_token");
		users.put(kevin, "kevin_token");

		// then
		assertThat(users.size()).isEqualTo(3);
		assertThat(users.firstKey().name()).isEqualTo("Kevin");
		assertThat(users.firstKey().age()).isEqualTo(18);
	}

	private record User(String name, int age) {}
}
