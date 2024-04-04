package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//테스트코드 껍데기 자동으로 만들기
//테스트 코드를 만들 클래스 이름에 단축키 ctrl + shift + t 를 하면 create Test가 나온다
//Jnuit5 선택, 테스트코드를 만들 메소드들 선택
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //같은걸로 테스트를 해야하는데 new을 사용하면 새로운 인스턴스가 생성되는 것이므로 지워준다
    //MemberService에서 선언한 repository도 변경

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    //테스트코드 이름은 한글로 해도 괜찮음 -> build될 때 실제코드에 포함되지 않기 때문
    @Test
    void join() {
        //given : 기반이 될 데이터
        Member member = new Member();
        member.setName("hello");

        //when : 검증할 것
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //test코드는 예외상황이 더 중요함
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
        //try - catch를 사용해도 되지만 간소화해서 사용가능
        /*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
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