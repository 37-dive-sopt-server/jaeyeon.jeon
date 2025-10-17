package or.sopt.service;

import or.sopt.domain.Gender;
import or.sopt.domain.Member;
import or.sopt.repository.MemoryMemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService{

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    private static long sequence = 1L;

    public Long join(String name, Gender gender, LocalDate birthDate, String email) {
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
