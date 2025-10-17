package or.sopt;

import or.sopt.controller.MemberController;
import or.sopt.domain.Gender;
import or.sopt.domain.Member;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MemberController memberController = new MemberController();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 종료 🚪");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("등록할 회원 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("⚠️ 이름을 입력해주세요.");
                        continue;
                    }

                    Gender gender = null;
                    while (gender == null) {
                        System.out.print("성별을 입력하세요 (M/F/O): ");
                        String g = scanner.nextLine().trim().toUpperCase();
                        switch (g) {
                            case "M":
                                gender = Gender.MALE;
                                break;
                            case "F":
                                gender = Gender.FEMALE;
                                break;
                            case "O":
                                gender = Gender.OTHER;
                                break;
                            default:
                                System.out.println("❌ 유효하지 않은 성별입니다. M/F/O 중 선택해주세요.");
                        }
                    }

                    LocalDate birthDate = null;
                    while (birthDate == null) {
                        System.out.print("생일을 입력하세요 (YYYY-MM-DD): ");
                        String birth = scanner.nextLine().trim();
                        try {
                            birthDate = LocalDate.parse(birth);
                        } catch (DateTimeParseException e) {
                            System.out.println("❌ 유효하지 않은 날짜 형식입니다. 예: 2000-01-31");
                        }
                    }

                    String email = null;
                    while (email == null || email.trim().isEmpty() || !email.contains("@")) {
                        System.out.print("이메일을 입력하세요: ");
                        String inputEmail = scanner.nextLine().trim();
                        if (inputEmail.isEmpty() || !inputEmail.contains("@")) {
                            System.out.println("❌ 유효하지 않은 이메일입니다. 다시 입력해주세요.");
                        } else {
                            email = inputEmail;
                        }
                    }

                    Long createdId = memberController.createMember(name, gender, birthDate, email);
                    if (createdId != null) {
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                    } else {
                        System.out.println("❌ 회원 등록 실패");
                    }
                    break;
                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        if (foundMember.isPresent()) {
                            Member m = foundMember.get();
                            System.out.println(
                                    "✅ 조회된 회원: ID=" + m.getId() +
                                    ", 이름=" + m.getName() +
                                    ", 성별=" + m.getGender() +
                                    ", 생일=" + m.getBirthDate() +
                                    ", 이메일=" + m.getEmail()
                            );
                        } else {
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;
                case "3":
                    List<Member> allMembers = memberController.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    }
                    else {
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println(
                                    "👤 ID=" + member.getId() +
                                    ", 이름=" + member.getName() +
                                    ", 성별=" + member.getGender() +
                                    ", 생일=" + member.getBirthDate() +
                                    ", 이메일=" + member.getEmail()
                            );
                        }
                        System.out.println("--------------------------");
                    }
                    break;
                case "4":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;
                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}
