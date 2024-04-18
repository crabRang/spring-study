package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장
    Optional<Member> findById(Long id); //아이디로 회원찾기
    Optional<Member> findByName(String name); //이름으로 회원찾기
    //반환되는 값이 null일 경우 Optinal로 감싸서 반환하는것을 선호
    List<Member> findAll(); //지금까지 저장된 모든회원 list 반환
}
