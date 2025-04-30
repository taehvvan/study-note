// 데몬 스레드 : 주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드

public class AutoSaveThread extends Thread {
	
	public void save() {
		System.out.println("작업 내용을 저장함");
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				break;
			}
			save();
		} // while
	} // run()
} // AutoSaveThread
