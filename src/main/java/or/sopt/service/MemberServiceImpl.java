package or.sopt.service;

import or.sopt.domain.Gender;
import or.sopt.domain.Member;
import or.sopt.repository.MemberRepository;
import or.sopt.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private static long sequence = 1L;

    public Long join(String name, Gender gender, LocalDate birthDate, String email) {

        /**
         * Stack *10배
         * Heap *10배
         * */
        Member member = new Member(sequence++, name, gender, birthDate, email);
        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
