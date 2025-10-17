package or.sopt.domain;

import java.time.LocalDate;

public class Member {

    private Long id;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private String email;

    public Member(Long id, String name, Gender gender, LocalDate birthDate, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }
}
