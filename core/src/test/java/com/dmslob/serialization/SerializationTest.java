package com.dmslob.serialization;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import org.testng.annotations.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SerializationTest {

	@Test
	public void when_serialize_and_deserialize_then_object_is_the_same() throws IOException, ClassNotFoundException {
		// given
		var u1 = new User("Bob", 30);
		// when serialize user object
		var fileOutputStream = new FileOutputStream("user.txt");
		var objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(u1);
		objectOutputStream.flush();
		objectOutputStream.close();
		// and deserialize user object
		var fileInputStream = new FileInputStream("user.txt");
		var objectInputStream = new ObjectInputStream(fileInputStream);
		User u2 = (User) objectInputStream.readObject();
		objectInputStream.close();
		// then
		boolean usersAreSame = u1 == u2;
		assertFalse(usersAreSame);
		assertEquals(u1.age(), u2.age());
		assertEquals(u2.name(), u1.name());
	}

	@Test
	public void should_not_serialize_transient_field() throws IOException, ClassNotFoundException {
		// given
		var user = new User("Bob", 30);
		var testId = UUID.randomUUID().toString();
		var testUser1 = new TestUser(testId, "test_1", user);
		// when
		var fileOutputStream = new FileOutputStream("test_user.txt");
		var objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(testUser1);
		objectOutputStream.flush();
		objectOutputStream.close();
		// and deserialize user object
		var fileInputStream = new FileInputStream("test_user.txt");
		var objectInputStream = new ObjectInputStream(fileInputStream);
		var testUser2 = (TestUser) objectInputStream.readObject();
		objectInputStream.close();
		// then
		assertEquals(testUser1.getTestId(), testUser2.getTestId());
		assertEquals(testUser1.getTestName(), testUser2.getTestName());
		assertNotNull(testUser1.getUser());
		assertNull(testUser2.getUser());
	}

	@Test
	public void should_throw_NotSerializableException_when_not_serializable() throws IOException {
		// given
		var card = new Card(1L, "Card");
		var testCard = new TestCard(card);
		// when
		var fileOutputStream = new FileOutputStream("test_card.txt");
		var objectOutputStream = new ObjectOutputStream(fileOutputStream);
		// then
		assertThatThrownBy(() -> objectOutputStream.writeObject(testCard))
				.isInstanceOf(NotSerializableException.class);
	}

	record User(String name, Integer age) implements Serializable {}
	record Card(Long id, String name) {}

	@Getter
	@AllArgsConstructor
	static class TestUser implements Serializable {
		private final String testId;
		private final String testName;
		private final transient User user;
	}

	@Getter
	@AllArgsConstructor
	static class TestCard implements Serializable {
		private Card card;
	}
}
