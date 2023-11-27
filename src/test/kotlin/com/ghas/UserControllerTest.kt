package com.ghas

import com.ghas.controller.UserController
import com.ghas.entity.User
import com.ghas.repository.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*

@ExtendWith(MockitoExtension::class)
class UserControllerTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userController: UserController

    @Test
    fun testGetUsers() {
        val users = listOf(User(1, "Pratiksha", "pratiksha@gmail.com"))
        `when`(userRepository.findAll()).thenReturn(users)

        val result: Iterable<User> = userController.getUsers()

        assertIterableEquals(users, result)
    }

    @Test
    fun testGetUser() {
        val userId = 1L
        val user = User(userId, "Pratiksha", "pratiksha@gmail.com")
        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user))

        val result: User = userController.getUser(userId)

        assertEquals(user, result)
    }

    @Test
    fun testGetUserNotFound() {
        val userId = 1L
        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.empty())

        assertThrows(NoSuchElementException::class.java) {
            userController.getUser(userId)
        }
    }

    @Test
    fun testCreateUser() {
        val newUser = User(0, "Pratiksha", "pratiksha@gmail.com")
        `when`(userRepository.save(any(User::class.java))).thenReturn(newUser)

        val result: User = userController.createUser(newUser)

        assertEquals(newUser, result)
    }

    @Test
    fun testUpdateUser() {
        val userId = 1L
        val existingUser = User(userId, "Pratiksha", "pratiksha@gmail.com")
        val updatedUser = User(userId, "Updated Pratiksha", "pratiksha@gmail.com")

        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.of(existingUser))
        `when`(userRepository.save(any(User::class.java))).thenReturn(updatedUser)

        val result: User = userController.updateUser(userId, updatedUser)

        assertEquals(updatedUser, result)
    }

    @Test
    fun testUpdateUserNotFound() {
        val userId = 1L
        val updatedUser = User(userId, "Updated Pratiksha", "pratikshab@gmail.com")

        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.empty())

        assertThrows(NoSuchElementException::class.java) {
            userController.updateUser(userId, updatedUser)
        }
    }

    @Test
    fun testDeleteUser() {
        val userId = 1L

        `when`(userRepository.existsById(userId)).thenReturn(true)

        assertDoesNotThrow { userController.deleteUser(userId) }

        verify(userRepository, times(1)).deleteById(userId)
    }

    @Test
    fun testDeleteUserNotFound() {
        val userId = 1L

        `when`(userRepository.existsById(userId)).thenReturn(false)

        assertThrows(NoSuchElementException::class.java) {
            userController.deleteUser(userId)
        }
    }
}
