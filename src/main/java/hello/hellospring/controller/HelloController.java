package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //문자반환
    @GetMapping("hello-string")
    @ResponseBody //http의 body부에 값을 직접 넣어주겠다는 의미
    //템플릿 엔진과의 차이 : view없이 문자가 그대로 나간다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //객체반환
    @GetMapping("hello-api")
    @ResponseBody //ResponseBody를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        //getter, setter 단축키 : alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
