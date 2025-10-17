package or.sopt.controller;

import or.sopt.domain.Gender;
import or.sopt.domain.Member;
import or.sopt.service.MemberService;
import or.sopt.service.MemberServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MemberController {

    private MemberService memberService = new MemberServiceImpl();

    public Long createMember(String name, Gender gender, LocalDate birthDate, String email) {
        return memberService.join(name, gender, birthDate, email);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}
