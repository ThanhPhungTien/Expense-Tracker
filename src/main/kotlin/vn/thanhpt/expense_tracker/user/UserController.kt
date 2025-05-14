package vn.thanhpt.expense_tracker.user

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping fun getAll() = userService.findAll()

    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = userService.findById(id)

    @PostMapping fun create(@RequestBody user: User) = userService.save(user)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User) = userService.update(id, user)

    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = userService.delete(id)
}
