package com.jake.jenkinscicdproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Autowired를 사용하면 Lazy Instance임
    // 생성자를 이용해서 만들면, 이미 만들고 필요할 때 바로 사용 => 서버에서 필요한거는 다 만들어놓고 써야함 그래야지 속도가 빠름
    // Autowired는 필요할 때 만들어서 사용 => 속도 측면에서 느림.
    private final JenkinsServices jenkinsServices;

    public TestController(JenkinsServices jenkinsServices) {
        this.jenkinsServices = jenkinsServices;
    }

    @GetMapping("/")
    public String index(){
        return "Hi Springboot";
    }

    // http://localhost:8080/cal?a=5&b=3
    @GetMapping("/cal")
    public String calculate(@RequestParam int a, @RequestParam int b) {
        int sumResult = jenkinsServices.sum(a, b);
        int minusResult = jenkinsServices.minus(a, b);
        return "Sum: " + sumResult + ", Minus: " + minusResult;
    }

//    public String


}
