package com.ciaranevans.spacexplorer.component

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources/component/features"],
        glue = ["com.ciaranevans.spacexplorer.component"])
class SpaceXplorerIT {
}