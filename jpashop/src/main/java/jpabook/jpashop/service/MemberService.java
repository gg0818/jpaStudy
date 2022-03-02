package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //여기에는 읽기전용 메서드가 더 많으므로 읽기전용 아닌것을 예외처리
@RequiredArgsConstructor //final만 가지고 생성자를 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

//    @RequiredArgsConstructor 를 사용하면 자동으로 final만 가지고 생성자를 만들어줌- 테스트시 편리함
//    @Autowired
//    public MemberService(MemberRepository memberRepository) { //생성자 injection
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional //예외처리
    public Long join(Member member) {
        validateDuplicateMember(member); //종복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
