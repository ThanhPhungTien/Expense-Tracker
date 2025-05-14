package vn.thanhpt.expense_tracker.family

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/families")
class FamilyController(private val service: FamilyService) {
    @GetMapping fun getAll() = service.findAll()

    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = service.findById(id)

    @PostMapping fun create(@RequestBody family: Family) = service.save(family)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody family: Family) = service.update(id, family)

    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = service.delete(id)

    @GetMapping("/search/tag") fun searchByTag(@RequestParam tag: String) = service.searchByTag(tag)

    @GetMapping("/search/name")
    fun searchByName(@RequestParam name: String) = service.searchByName(name)
}
