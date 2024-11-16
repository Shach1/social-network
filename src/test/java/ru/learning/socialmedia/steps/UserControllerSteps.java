package ru.learning.socialmedia.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.request.UserRegistrationRequest;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc()
public class UserControllerSteps {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private MvcResult result;

    @Given("the user service returns a list of users")
    public void theUserServiceReturnsAListOfUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(new UserFullInformation()));
    }

    @When("the client calls GET \\/api\\/v1\\/users")
    public void theClientCallsGETApiVUsers() throws Exception {
        result = mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Then("the client receives status code {int}")
    public void theClientReceivesStatusCode(int statusCode) throws Exception {
        assertThat(result.getResponse().getStatus()).isEqualTo(statusCode);
    }

    @Then("the client receives a list of users")
    public void theClientReceivesAListOfUsers() throws Exception {
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    @Given("the user service can add a user")
    public void theUserServiceCanAddAUser() {
        Mockito.when(userService.createUser(any(UserRegistrationRequest.class))).thenReturn(new UserLogInResponse());
    }

    @When("the client calls POST \\/api\\/v1\\/users with user data")
    public void theClientCallsPOSTApiVUsersWithUserData() throws Exception {
        result = mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Then("the client receives the created user")
    public void theClientReceivesTheCreatedUser() throws Exception {
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }
}