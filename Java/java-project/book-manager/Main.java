import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();

        System.out.println("=== 도서 관리 시스템 ===");
        System.out.print("아이디: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        if (!userDAO.login(username, password)) {
            System.out.println("로그인 실패. 프로그램 종료.");
            return;
        }

        while (true) {
            System.out.println("\n1. 도서 등록");
            System.out.println("2. 도서 목록 보기");
            System.out.println("3. 도서 검색");
            System.out.println("4. 도서 삭제");
            System.out.println("5. 종료");
            System.out.print("메뉴 선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("제목: ");
                    String title = scanner.nextLine();
                    System.out.print("저자: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    bookDAO.insertBook(new Book(title, author, isbn));
                    break;
                case "2":
                    for (Book book : bookDAO.getAllBooks()) {
                        System.out.println(book);
                    }
                    break;
                case "3":
                    System.out.print("검색할 제목 키워드: ");
                    String keyword = scanner.nextLine();
                    bookDAO.searchBooks(keyword);
                    break;
                case "4":
                    System.out.print("삭제할 도서 ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    bookDAO.deleteBook(id);
                    break;
                case "5":
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
