package vn.thanhpt.expense_tracker.transaction

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transactions")
class TransactionController(private val service: TransactionService) {
    @GetMapping fun getAll() = service.findAll()

    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = service.findById(id)

    @PostMapping fun create(@RequestBody transaction: Transaction) = service.save(transaction)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody transaction: Transaction) =
            service.update(id, transaction)

    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = service.delete(id)
}
