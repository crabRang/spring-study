package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller 어노테이션을 사용하면 스프링 컨테이너에 MemberController라는 컨트롤러 객체를 생성해서 넣어둔다.
// -> 빈을 관리한다고 함

//실행하는 main()이 있는 패키지를 포함한 하위 패키지만 컴포넌트 스캔
@Controller
public class MemberController {

    // 이전에는 new로 생성해서 사용 -> 스프링이 관리를 하게되면 스프링 컨테이너에 등록하고 받아서 쓰도록 변경해야함
    // new해서 사용하면 memberController말고 다른 controller에서도 가져다 쓸 수 있음
    // 여러개 생성할 필요가 없고, 하나만 사용하면 되기 때문에 스프링 컨테이너에 등록하고 사용.
    private final MemberService memberService;

    //필드 주입
    //@Autowired private MemberService memberService;

    //생성자 주입
    // @Autowired 가 있으면, 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    // 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI, 의존성 주입이라 한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
