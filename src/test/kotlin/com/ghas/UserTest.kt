package com.ghas

import com.ghas.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun testPrimaryConstructor() {
        val user = User(id = 1, name = "Pratiksha bhende", email = "pratikshab@gmail.com")

        assertEquals(1, user.id)
        assertEquals("Pratiksha bhende", user.name)
        assertEquals("pratikshab@gmail.com", user.email)
    }

    @Test
    fun testDefaultConstructor() {
        val defaultUser = User()

        assertEquals(0, defaultUser.id)
        assertEquals("", defaultUser.name)
        assertEquals("", defaultUser.email)
    }

    @Test
    fun testGetterMethods() {
        val user = User(id = 2, name = "Pratiksha bhende", email = "pratikshab@gmail.com")

        assertEquals(2, user.id)
        assertEquals("Pratiksha bhende", user.name)
        assertEquals("pratikshab@gmail.com", user.email)
    }
}
