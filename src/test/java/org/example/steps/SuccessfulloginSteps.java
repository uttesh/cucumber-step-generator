
package org.example.steps;

import io.cucumber.java.en.*;

public class SuccessfulloginSteps {

    @Given("^the user is on the login page$")
    public void the_user_is_on_the_login_page() {
        // TODO: Implement step logic for: the user is on the login page
        System.out.println("Executing step: the user is on the login page");
    }
    @When("^the user enters valid credentials$")
    public void the_user_enters_valid_credentials() {
        // TODO: Implement step logic for: the user enters valid credentials
        System.out.println("Executing step: the user enters valid credentials");
    }
    @Then("^the user is redirected to the dashboard$")
    public void the_user_is_redirected_to_the_dashboard() {
        // TODO: Implement step logic for: the user is redirected to the dashboard
        System.out.println("Executing step: the user is redirected to the dashboard");
    }
}
