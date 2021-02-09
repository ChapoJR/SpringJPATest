package com.nobelsoft;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import static org.assertj.core.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberTest {

}
