package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 테스트
@Transactional
class MemberServiceIntegrationTest {

    //원래는 생성자에 @Autowired를 해줬지만 테스트코드는 그냥 편한방법으로 해도된다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

/*
    // 아래 부분들은 DB가 없었던 경우였기 때문에 계속 데이터를 지워주기 위해 필요했던 것
    // DB를 사용하므로 이제 필요없다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
*/

    @Test
    void join() {
        Member member = new Member();
        member.setName("spring"); // DB에 해당하는 값이 있는경우 에러 - 가입실패
        // 보통 local PC에 있는 DB에서 테스트하거나, 테스트용 DB가 존재
        // 테스트는 반복할 수 있어야하는데 이 경우 한번 실행 시 db에 name:spring이 들어가기 때문에 다음실행 시 에러발생
        // 데이터베이스의 트랜잭션을 이용 -> 테스트 데이터를 넣고 검증 후 DB를 rollback해줌
        // -> @Transactional 어노테이션 사용

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}