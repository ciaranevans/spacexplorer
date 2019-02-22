package com.ciaranevans.spacexplorer.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloWorldControllerTest {

    private val helloWorldController = HelloWorldController()

    @Test
    fun whenCallingHelloWorldThenCorrectResponseReturned() {
        assertThat(helloWorldController.helloWorld())
                .`as`("Returns correct hello world message")
                .isEqualTo("Hello, World!")
    }

}