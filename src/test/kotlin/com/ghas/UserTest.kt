package kotlin.com.ghas

import com.ghas.entity.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `createUser should create a user with given parameters`() {
        // Arrange
        val id = 1L
        val name = "John Doe"
        val email = "john.doe@example.com"

        // Act
        val user = User(id, name, email)

        // Assert
        assertEquals(id, user.id)
        assertEquals(name, user.name)
        assertEquals(email, user.email)
    }

    @Test
    fun `defaultConstructor should create a user with default values`() {
        // Act
        val user = User()

        // Assert
        assertEquals(0L, user.id)
        assertEquals("", user.name)
        assertEquals("", user.email)
    }

    @Test
    fun `twoUsersWithSameValuesShouldBeEqual`() {
        // Arrange
        val user1 = User(1L, "John Doe", "john.doe@example.com")
        val user2 = User(1L, "John Doe", "john.doe@example.com")

        // Assert
        assertEquals(user1, user2)
    }

    @Test
    fun `toStringShouldReturnExpectedString`() {
        // Arrange
        val user = User(1L, "John Doe", "john.doe@example.com")

        // Act
        val toStringResult = user.toString()

        // Assert
        assertTrue(toStringResult.contains("id=1"))
        assertTrue(toStringResult.contains("name=John Doe"))
        assertTrue(toStringResult.contains("email=john.doe@example.com"))
    }
}
