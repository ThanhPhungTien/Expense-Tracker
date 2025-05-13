package vn.thanhpt.expense_tracker.user

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping fun getAll() = userService.findAll()

    @GetMapping("/{id}") fun getById(@PathVariable id: String) = userService.findById(id)

    @PostMapping fun create(@RequestBody user: User) = userService.save(user)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody user: User) = userService.update(id, user)

    @DeleteMapping("/{id}") fun delete(@PathVariable id: String) = userService.delete(id)
}
