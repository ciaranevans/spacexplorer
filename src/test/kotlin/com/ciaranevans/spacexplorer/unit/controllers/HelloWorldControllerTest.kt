package com.ciaranevans.spacexplorer.unit.controllers

import com.ciaranevans.spacexplorer.controllers.HelloWorldController
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