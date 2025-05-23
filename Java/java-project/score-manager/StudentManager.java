import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("학생 정보가 추가되었습니다.");
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }

        System.out.println("====== 전체 학생 목록 ======");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void searchStudent(String name) {
        boolean found = false;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                System.out.println("검색 결과:");
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("해당 이름의 학생을 찾을 수 없습니다.");
        }
    }

    public void deleteStudent(String name) {
        Iterator<Student> iterator = students.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            if (iterator.next().getName().equalsIgnoreCase(name)) {
                iterator.remove();
                removed = true;
                System.out.println("학생 정보가 삭제되었습니다.");
            }
        }

        if (!removed) {
            System.out.println("해당 이름의 학생을 찾을 수 없습니다.");
        }
    }
}
