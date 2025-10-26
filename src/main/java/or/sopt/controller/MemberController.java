package or.sopt.controller;

import or.sopt.domain.Gender;
import or.sopt.domain.Member;
import or.sopt.service.MemberService;
import or.sopt.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/users")
    public Long createMember(String name, Gender gender, LocalDate birthDate, String email) {
        return memberService.join(name, gender, birthDate, email);
    }

    @GetMapping("/users")
    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    @GetMapping("/users/all")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}
