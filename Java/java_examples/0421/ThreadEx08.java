// 멀티스레드의 스케줄링 메서드 중  sleep() 와 interrupt() 등에 대해서 살펴본다.
// 이 스케줄링 메서드를 잘 활용해서 catch(){} 블록에서 다시 interrupt() 메서드를 호출해서
// 스레드의 인터럽트된 상태를 다시 true로 초기화시키면 카운터가 중단된다.

import javax.swing.JOptionPane;

class Thread08 extends Thread {
	@Override
	public void run() {
		int i = 10;
		
		while(i!= 0 && !isInterrupted()) {
			// isInterrupted() 메서드는 쓰레드의 interrupted 된 상태를 boolean 타입으로 반환
			System.out.println(i--);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				
			} // try-catch
		} // while
		System.out.println("카운터가 종료되었습니다.");
	} // run()
} // Thread08

public class ThreadEx08 {

	public static void main(String[] args) {
		
		Thread08 th = new Thread08();
		th.start();
		
		String name = JOptionPane.showInputDialog("이름 입력 : ");
		System.out.println("입력한 이름 : " + name);
		th.interrupt();		// 스레드의 interrupted 상태를 false에서 true로 초기화
		System.out.println("isInterrupted() boolean 타입의 상태 : " + th.isInterrupted());

	}

}
