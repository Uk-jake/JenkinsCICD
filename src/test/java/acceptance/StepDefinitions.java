package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    // 서버 URL 저장
    private String server = System.getProperty("calculator.url");

    // 웹 서비스 요청을 위한 클래스
    private RestTemplate restTemplate = new RestTemplate();

    // 매개변수와 결과를 저장할 변수
    private String a;
    private String b;
    private String result;

    @Given("^I have two numbers: (.*) and (.*)$")
    public void i_have_two_numbers(String a, String b) throws Throwable {
        this.a = a;
        this.b = b;
    }

    @When("^the calculator sums them$")
    public void the_calculator_sums_them() throws Throwable {
        String url = String.format("%s/cal?a=%s&b=%s", server, a, b);
        result = restTemplate.getForObject(url, String.class);
    }

    @Then("^I receive (.*) as a result")
    public void i_receive_as_a_result(String expectedRuled) throws Throwable {
        assertEquals(expectedRuled, result);
    }
}
