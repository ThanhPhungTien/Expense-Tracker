package vn.thanhpt.expense_tracker.user

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import vn.thanhpt.expense_tracker.family.Family
import vn.thanhpt.expense_tracker.family.FamilyRepository

@Service
class UserService(
        private val userRepository: UserRepository,
        private val familyRepository: FamilyRepository
) {
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

    fun findByFamilyTagLike(tag: String): List<User> = userRepository.findByFamilyTagLike(tag)

    // Tạo Family mới, user đầu tiên là admin
    fun createFamily(userId: Long, family: Family): Family {
        val savedFamily = familyRepository.save(family)
        val user =
                userRepository.findById(userId).orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
                }
        userRepository.save(user.copy(familyId = savedFamily.id, isFamilyAdmin = true))
        return savedFamily
    }

    // Thêm thành viên vào Family (chỉ admin mới được thêm)
    fun addMemberToFamily(adminId: Long, memberId: Long) {
        val admin =
                userRepository.findById(adminId).orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found")
                }
        if (!admin.isFamilyAdmin || admin.familyId == null)
                throw ResponseStatusException(HttpStatus.FORBIDDEN, "Not family admin")
        val member =
                userRepository.findById(memberId).orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found")
                }
        userRepository.save(member.copy(familyId = admin.familyId, isFamilyAdmin = false))
    }

    // Xoá thành viên khỏi Family (chỉ admin mới được xoá)
    fun removeMemberFromFamily(adminId: Long, memberId: Long) {
        val admin =
                userRepository.findById(adminId).orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found")
                }
        if (!admin.isFamilyAdmin || admin.familyId == null)
                throw ResponseStatusException(HttpStatus.FORBIDDEN, "Not family admin")
        val member =
                userRepository.findById(memberId).orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found")
                }
        if (member.familyId != admin.familyId)
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Member not in your family")
        userRepository.save(member.copy(familyId = null, isFamilyAdmin = false))
    }
}
