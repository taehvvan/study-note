/*
 * 스레드의 스케줄링 메서드의 suspend(), resume(), stop() 메서드는 교착상태가 발생해서
 * 앞으로는 사용하지 않을 것을 권장하는 @Deprecated 애노테이션으로 설정되어 있다.
 */

class Thread09 implements Runnable {
	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName()); // 현재 실행 중인 스레드 이름 반환
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {}
		} // while
	} // run()
}

public class ThreadEx09 {

	public static void main(String[] args) {
		
		Thread09 t = new Thread09();
		Thread th01 = new Thread(t, "#");
		Thread th02 = new Thread(t, "##");
		Thread th03 = new Thread(t, "###");

	}

}
