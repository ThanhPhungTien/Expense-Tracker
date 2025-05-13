package vn.thanhpt.expense_tracker.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findAll(): List<User> = userRepository.findAll()
    fun findById(id: String): User? = userRepository.findById(id).orElse(null)
    fun save(user: User): User = userRepository.save(user)
    fun update(id: String, user: User): User? {
        return if (userRepository.existsById(id)) {
            userRepository.save(user.copy(id = id))
        } else null
    }
    fun delete(id: String) {
        userRepository.delete(userRepository.findById(id).orElseThrow())
    }
}
