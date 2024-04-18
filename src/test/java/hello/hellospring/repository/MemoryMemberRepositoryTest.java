package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//다른데에서 사용할게 아니므로 public을 하지않아도 된다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 모든 테스트코드는 순서에 의존적으로 설계해선 X
    // 테스트코드가 시작되고 끝날때마다 저장소를 clear해줘야 함
    // 테스트코드는 서로 의존관계가 없어야한다. -> 이코드가 없으면 순서가 틀렸을 경우 에러가 뜰 수 있음
    // 저장소를 한번 비워주는 행위가 없으면 테스트a에서 넣은 데이터값 때문에 테스트b에서 에러가 발생할 수 있음
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){ //save기능이 동작하는지 확인하기 위한 TEST
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //findById로 값이 잘 들어갔는지 확인
        Member result = repository.findById(member.getId()).get(); //oprional에서 값을 꺼낼때는 get()으로 꺼낼 수 있다.

        System.out.println("result = "+(result == member));

        //Assertions.assertEquals(member, result);
        //Assertions.assertThat(member).isEqualTo(result);
        //Assertions.assertThat(member).isEqualTo(null);
        //같지 않으면 에러코드발생 -> 기대값과 나온값 확인가능
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        //Assertions.assertThat(member1).isEqualTo(result);
        assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
