package vn.thanhpt.expense_tracker.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User management APIs")
class UserController(private val userService: UserService) {
        @Operation(summary = "Get all users", description = "Retrieves a list of all users")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully retrieved all users"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @GetMapping
        fun getAll() = userService.findAll()

        @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully retrieved the user"
                                ),
                                ApiResponse(responseCode = "404", description = "User not found"),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @GetMapping("/{id}")
        fun getById(@Parameter(description = "ID of the user to retrieve") @PathVariable id: Long) =
                userService.findById(id)

        @Operation(summary = "Delete user", description = "Deletes a user by their ID")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "204",
                                        description = "Successfully deleted the user"
                                ),
                                ApiResponse(responseCode = "404", description = "User not found"),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @DeleteMapping("/{id}")
        fun delete(@Parameter(description = "ID of the user to delete") @PathVariable id: Long) =
                userService.deleteUser(id)
}
