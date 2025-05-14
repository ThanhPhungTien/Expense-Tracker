package vn.thanhpt.expense_tracker.transactiontype

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction-types")
class TransactionTypeController(private val service: TransactionTypeService) {
    @GetMapping fun getAll() = service.findAll()

    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = service.findById(id)

    @PostMapping fun create(@RequestBody type: TransactionType) = service.save(type)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody type: TransactionType) =
            service.update(id, type)

    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = service.delete(id)
}
