package com.ciaranevans.spacexplorer.component

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(features = ["target/test-classes/component"],
        glue = ["com.ciaranevans.spacexplorer.component"])
class SpaceXplorerIT {
}