/*
 * 특정 은행 ATM 계좌에서 입금, 출금과 같은 특정한 작업이 이루어질 때 한 번에 하나의 스레드에 의해서만
 * 입/출금 작업이 이루어지도록 synchronized 키워드로 동기화, 즉 임계영역을 만든다.
 */

class Atm {		// Atm 계좌
	private int money;	// 은행 계좌 잔액
	
	public Atm(int money) {
		this.money = money;		// 계좌 개설 시 입금할 금액
	}
	
	// 입금 동기화 처리
	synchronized void deposit(int amount, String name) {
		money += amount;
		System.out.println(name + "님의 입금 금액 = " + amount);
	}
	
	// 출금 동기화 처리
	synchronized void withdraw(int amount, String name) {
		if((money - amount) > 0) {
			money -= amount;
			System.out.println(name + "님의 출금 금액 = " + amount);
		} else {
			System.out.println(name + "님 잔액부족으로 출금할 수 없습니다.");
		}
	}
	
	public void getMoney() {
		System.out.println("계좌 잔액 = " + money);
	}
} // Atm

class AtmUser extends Thread {
	boolean flag = false;	// 입금, 출금 분기
	
	Atm obj;
	
	public AtmUser(Atm obj, String name) {	// 생성자 오버로딩
		super(name);		// 부모클래스 오버로딩된 생성자를 호출해서 스레드 이름 반환
		this.obj = obj;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			try {
				sleep(500);
			} catch(InterruptedException ie) {}
			
			if(flag) {
				obj.deposit((int)(Math.random()*10+2)*100, getName());
				obj.getMoney();
			} else {
				obj.withdraw((int)(Math.random()*10+2)*100, getName());
				obj.getMoney();
			} // if-else
			flag = !flag;
		} // for
	} // 쓰레드 문장 구현
} // AtmUser

public class ThreadEx05 {

	public static void main(String[] args) {
		
		Atm obj = new Atm(1000);
		AtmUser user01 = new AtmUser(obj, "홍길동");
		AtmUser user02 = new AtmUser(obj, "이순신");
		AtmUser user03 = new AtmUser(obj, "강감찬");
		
		user01.start();	// 스레드 시작
		user02.start();
		user03.start();

	}

}
