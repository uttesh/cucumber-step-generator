
package org.example.steps;

import io.cucumber.java.en.*;

public class FailedloginSteps {

    @Given("^the user is on the login page$")
    public void the_user_is_on_the_login_page() {
        // TODO: Implement step logic for: the user is on the login page
        System.out.println("Executing step: the user is on the login page");
    }
    @When("^the user enters invalid credentials$")
    public void the_user_enters_invalid_credentials() {
        // TODO: Implement step logic for: the user enters invalid credentials
        System.out.println("Executing step: the user enters invalid credentials");
    }
    @Then("^the user sees an error message$")
    public void the_user_sees_an_error_message() {
        // TODO: Implement step logic for: the user sees an error message
        System.out.println("Executing step: the user sees an error message");
    }
}
