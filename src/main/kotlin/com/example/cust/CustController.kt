package com.example.cust

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustController {
    @GetMapping
    fun getCustomers(): ResponseEntity<Any> {
        println("In getCustomers")
        return ResponseEntity.ok("Customers")
    }
}