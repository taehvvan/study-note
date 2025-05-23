import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n=== 학생 성적 관리 프로그램 ===");
            System.out.println("1. 학생 성적 등록");
            System.out.println("2. 전체 학생 목록 보기");
            System.out.println("3. 학생 검색");
            System.out.println("4. 학생 삭제");
            System.out.println("5. 종료");
            System.out.print("메뉴 선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("이름: ");
                    String name = scanner.nextLine();
                    System.out.print("국어 점수: ");
                    int kor = Integer.parseInt(scanner.nextLine());
                    System.out.print("영어 점수: ");
                    int eng = Integer.parseInt(scanner.nextLine());
                    System.out.print("수학 점수: ");
                    int math = Integer.parseInt(scanner.nextLine());
                    manager.addStudent(new Student(name, kor, eng, math));
                    break;
                case "2":
                    manager.viewAllStudents();
                    break;
                case "3":
                    System.out.print("검색할 학생 이름: ");
                    String searchName = scanner.nextLine();
                    manager.searchStudent(searchName);
                    break;
                case "4":
                    System.out.print("삭제할 학생 이름: ");
                    String deleteName = scanner.nextLine();
                    manager.deleteStudent(deleteName);
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
