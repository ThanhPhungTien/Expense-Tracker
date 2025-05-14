package vn.thanhpt.expense_tracker.user

import org.springframework.web.bind.annotation.*
import vn.thanhpt.expense_tracker.family.Family

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping fun getAll() = userService.findAll()

    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = userService.findById(id)

    @PostMapping fun create(@RequestBody user: User) = userService.save(user)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User) = userService.update(id, user)

    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = userService.delete(id)

    // Tìm kiếm user theo tag family
    @GetMapping("/search/family-tag")
    fun findByFamilyTag(@RequestParam tag: String) = userService.findByFamilyTagLike(tag)

    // Tạo family mới, userId là người tạo (admin)
    @PostMapping("/{userId}/create-family")
    fun createFamily(@PathVariable userId: Long, @RequestBody family: Family) =
            userService.createFamily(userId, family)

    // Thêm thành viên vào family (chỉ admin)
    @PostMapping("/{adminId}/add-member/{memberId}")
    fun addMember(@PathVariable adminId: Long, @PathVariable memberId: Long) =
            userService.addMemberToFamily(adminId, memberId)

    // Xoá thành viên khỏi family (chỉ admin)
    @PostMapping("/{adminId}/remove-member/{memberId}")
    fun removeMember(@PathVariable adminId: Long, @PathVariable memberId: Long) =
            userService.removeMemberFromFamily(adminId, memberId)
}
