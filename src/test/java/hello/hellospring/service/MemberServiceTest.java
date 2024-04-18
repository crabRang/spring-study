package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//테스트코드 껍데기 자동으로 만들기
//테스트 코드를 만들 클래스 이름에(여기서는 서비스클래스에서) 단축키 ctrl + shift + t 를 하면 create Test가 나온다
//Jnuit5 선택, 테스트코드를 만들 메소드들 선택
class MemberServiceTest {

    // MemberService memberService = new MemberService();
    MemberService memberService;

    // MemoryMemberRepository memberRepository = new  MemoryMemberRepository();
    MemoryMemberRepository memberRepository;
    //같은 repository로 테스트를 해야하는데 new을 사용하면 새로운 인스턴스가 생성되는 것이므로 지워준다
    //MemberService에서 선언한 repository도 변경

    // 각 테스트 실행 전에 호출
    // 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트를 할 때마다 메모리에 데이터가 쌓이므로 지워주는 작업이 필요하다.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    //테스트코드 이름은 한글로 해도 된다. -> build될 때 실제코드에 포함되지 않기 때문
    @Test
    void join() {
        //given : 기반이 될 데이터, 이런상황이 주어져서
        Member member = new Member();
        member.setName("hello");

        //when : 검증할 것, 이것을 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트코드는 예외상황이 더 중요함
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //memberService.join(member2); //중복값이 들어갔으므로 예외가 잡혀야함
        //방법1 : try - catch를 사용해도 되지만 간소화해서 사용가능
/*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        // memberService.join(member2) 로직을 실행했을 때 "이미 존재하는 회원입니다." 이 예외가 발생해야 함
        // memberService.join(member2) 자체가 중복을 보기위한 로직이기 때문에 예외처리가 제대로 되는지 확인하는 부분
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findeOne() {
    }
}