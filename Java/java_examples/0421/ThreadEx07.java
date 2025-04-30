// 주 스레드 main, 3초 후에 메인 주 스레드가 종료되면 데몬 스레드인 AutoSaveThread도 자동 종료된다.
public class ThreadEx07 {

	public static void main(String[] args) {
		
		AutoSaveThread autoSaveThread = new AutoSaveThread();
		autoSaveThread.setDaemon(true);	// 데몬 스레드 지정, start() 메서드 호출 전 먼저 실행해야 에러 안남
		autoSaveThread.start();			// 데몬 스레드 시작
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ie) {}
		
		System.out.println("메인 스레드 종료");

	}

}
