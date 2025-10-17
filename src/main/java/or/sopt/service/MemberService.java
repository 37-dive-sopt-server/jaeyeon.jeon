package or.sopt.service;

import or.sopt.domain.Gender;
import or.sopt.domain.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(String name, Gender gender, LocalDate birthDate, String email);
    Optional<Member> findOne(Long memberId);
    List<Member> findAllMembers();
}
