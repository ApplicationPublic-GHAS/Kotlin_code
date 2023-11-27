package com.ghas

import com.ghas.entity.User
import com.ghas.repository.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional
import org.mockito.Mockito.`when`
import org.springframework.data.repository.findByIdOrNull
import org.junit.jupiter.api.Assertions.*

@ExtendWith(MockitoExtension::class)
class UserRepositoryTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Test
    fun testSaveUser() {
        val userToSave = User(name = "Pratiksha Bhende", email = "pratiksha@gmail.com")
        val savedUser = User(id = 1, name = "Pratiksha Bhende", email = "pratiksha@gmail.com")

        `when`(userRepository.save(userToSave)).thenReturn(savedUser)

        val result: User = userRepository.save(userToSave)

        assertEquals(savedUser, result)
    }

    @Test
    fun testFindById() {
        val userId = 1L
        val user = User(id = userId, name = "Pratiksha bhende", email = "pratiksha@gmail.com")

        `when`(userRepository.findById(userId)).thenReturn(Optional.of(user))

        val result: Optional<User> = userRepository.findById(userId)

        assertTrue(result.isPresent)
        assertEquals(user, result.get())
    }

    @Test
    fun testFindByIdNotFound() {
        val userId = 1L

        `when`(userRepository.findById(userId)).thenReturn(Optional.empty())

        val result: Optional<User> = userRepository.findById(userId)

        assertFalse(result.isPresent)
    }

    @Test
    fun testFindAll() {
        val users = listOf(
            User(id = 1, name = "Pratiksha", email = "pratiksha@gmail.com"),
            User(id = 2, name = "Pooja", email = "pooja@gmail.com")
        )

        `when`(userRepository.findAll()).thenReturn(users)

        val result: Iterable<User> = userRepository.findAll()

        assertIterableEquals(users, result.toList())
    }

}
