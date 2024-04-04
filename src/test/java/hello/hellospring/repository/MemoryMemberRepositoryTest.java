package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test코드가 시작되고 끝날때마다 저장소를 clear해주는 것
    //테스트코드는 서로 의존관계가 없어야한다. -> 이코드가 없으면 순서가 틀렸을 경우 에러가 뜰 수 있음
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //oprional에서 값을 꺼낼때는 get()

        System.out.println("result = "+(result == member));
        //Assertions.assertThat(member).isEqualTo(result);
        //Assertions.assertThat(member).isEqualTo(null);
        //같지 않으면 에러코드발생
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
