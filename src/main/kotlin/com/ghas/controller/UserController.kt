package com.ghas.controller

import com.ghas.entity.User
import com.ghas.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(@Autowired private val userRepository: UserRepository) {

    @GetMapping("/")
    fun getUsers(): Iterable<User> = userRepository.findAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User = userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }

    @PostMapping("/")
    fun createUser(@RequestBody user: User): User = userRepository.save(user)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: User): User {
        return userRepository.findById(id).map { existingUser ->
            val newUser = existingUser.copy(
                name = updatedUser.name,
                email = updatedUser.email
            )
            userRepository.save(newUser)
        }.orElseThrow { NoSuchElementException("User with ID $id not found") }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        } else {
            throw NoSuchElementException("User with ID $id not found")
        }
    }
}
