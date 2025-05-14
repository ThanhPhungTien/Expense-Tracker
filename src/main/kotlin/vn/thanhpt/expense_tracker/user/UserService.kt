package vn.thanhpt.expense_tracker.user

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(private val userRepository: UserRepository) {
    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun save(user: User): User {
        // Check if user exists by phone number
        userRepository.findByPhone(user.phone)?.let {
            throw ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User with phone number ${user.phone} already exists"
            )
        }

        // Check if user exists by email
        userRepository.findByEmail(user.email)?.let {
            throw ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User with email ${user.email} already exists"
            )
        }

        // If no existing user found, save the new user
        return userRepository.save(user)
    }

    fun update(id: Long, user: User): User? {
        return if (userRepository.existsById(id)) {
            userRepository.save(user.copy(id = id))
        } else null
    }
    fun delete(id: Long) {
        userRepository.delete(userRepository.findById(id).orElseThrow())
    }
}
