import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.println("====== ATM 시뮬레이터 ======");

        while (true) {
            if (!atm.isLoggedIn()) {
                System.out.print("ID 입력: ");
                String id = scanner.nextLine();
                System.out.print("PIN 입력: ");
                String pin = scanner.nextLine();

                if (atm.login(id, pin)) {
                    System.out.println("로그인 성공!");
                } else {
                    System.out.println("로그인 실패. 다시 시도하세요.");
                    continue;
                }
            }

            System.out.println("\n1. 잔액 조회");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 로그아웃");
            System.out.println("5. 종료");
            System.out.print("메뉴 선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.printf("현재 잔액: %.2f원\n", atm.checkBalance());
                    break;
                case "2":
                    System.out.print("입금할 금액: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    atm.deposit(depositAmount);
                    System.out.println("입금 완료!");
                    break;
                case "3":
                    System.out.print("출금할 금액: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (atm.withdraw(withdrawAmount)) {
                        System.out.println("출금 완료!");
                    } else {
                        System.out.println("잔액 부족 또는 잘못된 금액입니다.");
                    }
                    break;
                case "4":
                    atm.logout();
                    System.out.println("로그아웃되었습니다.");
                    break;
                case "5":
                    System.out.println("프로그램 종료.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
