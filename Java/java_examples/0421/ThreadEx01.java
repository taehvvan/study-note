/*
 * 멀티스레드 구현 방법
 * 		1. Thread 클래스를 상속
 * 		2. run() 메서드를 오버라이딩해서 스레드 문장 구현
 * 		3. Thread 클래스를 상속받아서 자손에서 스레드 프로그램을 만들면 단순한 것을 만들 수 있고,
 * 			단점으로는 단일상속만 가능하다.
 */

class Thread01 extends Thread {
	Thread01(String name) {		// 생성자 오버로딩
		super(name);
	}

	@Override
	public void run() {
		for(int num = 1; num <= 5; num++) {
			for(int k = 1; k < 100000000; k++);		// 스레드가 구현될 시간적 여유를 준다.
			System.out.println(getName() + " : " + num);	// getName() : 스레드 이름 반환
		}
	}
}

public class ThreadEx01 {

	public static void main(String[] args) {

		Thread01 th01 = new Thread01("첫번째 스레드");
		Thread01 th02 = new Thread01("두번째 스레드");
		th01.start();		// 멀티스레드 시작 후 실행대기 상태에 있다가 자기 차례가 되면 
							// run() 메소드를 자동 호출하여 스레드 실행상태가 됨
		th02.start();		// 두 번째 스레드 시작
		
	}

}
