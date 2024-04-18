package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //회원을 저장할 Map
    private static long sequence = 0L; // key값을 생성해 줄 용도
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //시퀀스 값을 올려서 그걸 id값으로 셋팅 후 store에 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //요즘에는 null이 반환될 가능성이 있는 경우 Optional을 사용
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
        //member.getName() 이 넘어온 파라미터 name값과 같은 경우에만 filter가 됨
        //findAny() : 하나라도 찾는 것
        //끝까지 찾아도 없으면 Optional에 null이 담겨져 반환
    }

    @Override
    public List<Member> findAll() {
        //sotre에 있는 values : member들
        return new ArrayList<>(store.values());
        //store에 있는 values() : Member
    }

    public void clearStore(){
        //테스트코드 구동 후 repository를 전부 지우기 위함
        store.clear();
    }
}
