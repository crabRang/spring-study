package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //private final MemberRepository memberRepository = new MemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        //직접 new해서 생성하는게 아니라 외부에서 넣어주도록 생성
    }

    /**
     * 회원가입
     */
    public long join(Member member){
        //같은 이름이 있는 중복 회원X
        //과거에는 if(result != null) 이런 조건문을 사용했지만, 이제는 null이 올 가능성이 있으면 Optional을 사용해 감싸준다.
/*
        // 1번코드
        Optional<Member> result = memberRepository.findByName(member.getName());
        // m에 값이 있으면 IllegalStateException
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        //위 코드의 memberRepository.findByName(member.getName());가 optional을 반환하므로 바로 ifPresent를 사용할 수 있다.
       
        // 2번코드
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        
        //2번코드에서 리펙토링을 하여 메소드를 따로 빼줄것, 단축키 : ctrl + alt + shift + t 
*/
        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
