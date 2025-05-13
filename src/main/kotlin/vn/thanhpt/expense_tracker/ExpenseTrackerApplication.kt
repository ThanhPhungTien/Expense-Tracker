package vn.thanhpt.expense_tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ExpenseTrackerApplication

fun main(args: Array<String>) {
    runApplication<ExpenseTrackerApplication>(*args)
}
