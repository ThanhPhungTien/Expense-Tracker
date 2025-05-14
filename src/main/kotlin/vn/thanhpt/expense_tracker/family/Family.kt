package vn.thanhpt.expense_tracker.family

import jakarta.persistence.*

@Entity
@Table(name = "families")
data class Family(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val name: String = "",
        val tag: String = ""
) {
    constructor() : this(0, "", "")
}
