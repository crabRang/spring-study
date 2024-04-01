package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 어노테이션 사용
public class HelloController {
    @GetMapping("hello") //get, post mapping을 통해 찾아온다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); //model로 데이터를 보낸다.

        /*
         * 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리
         * resources: templates/ + {ViewName} + .html
         */
        return "hello"; //templates/hello.html을 찾음 -> Thymeleaf템플릿 엔진 처리
    }
}
