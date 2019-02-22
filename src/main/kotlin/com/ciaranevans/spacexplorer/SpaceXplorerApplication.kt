package com.ciaranevans.spacexplorer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpaceXplorerApplication

fun main(args: Array<String>) {
    runApplication<SpaceXplorerApplication>(*args)
}
