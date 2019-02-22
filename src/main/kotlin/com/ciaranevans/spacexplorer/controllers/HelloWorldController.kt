package com.ciaranevans.spacexplorer.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloWorldController {

    @GetMapping(produces = [MediaType.TEXT_PLAIN_VALUE])
    fun helloWorld() : String {
        return "Hello, World!"
    }

}