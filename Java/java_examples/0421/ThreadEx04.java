/*
 * 자바 GUI 중 하나인 AWT로 프레임윈도우 창을 만든 다음 멀티쓰레드 왼쪽에서 오른쪽으로
 * 노란 글자가 항상 지나가는 것을 만드는 스레드 응용 예제 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;	// java.awt 패키지의 AWT 프레임윈도우 API 클래스 임포트
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GraphicFrame04 extends Frame implements Runnable {

	int x = 0;	// 프레임 윈도우 x 좌표
	
	public GraphicFrame04() {
		this.setBackground(new Color(0,0,0));	// this.는 생략 가능, AWT 프레임 윈도우 배경색을 검정으로
		setSize(370, 150);	// 프레임 윈도우 폭과 높이를 지정
		setVisible(true);	// 프레임 윈도우를 항상 나오게
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();	// 자원 해제
				System.exit(0);		// 현재 프로그램(열려진 AWT 프레임 윈도우 창)을 정상적 종료
			}	// 현재 프레임 윈도우를 닫으면 호출
			
		});	// 프레임윈도우 이벤트 등록 메서드 => 익명클래스, 즉 내부 무명클래스로 컴파일됨
			// 외부클래스명$번호.class => GraphicFrame04$1.class
	} // 생성자
	@Override
	public void run() {		// 스레드 문장 구현
		while(true) {
			try {
				Thread.sleep(100);	// 스레드를 일정 시간동안 쉬게 한다. (1000 = 1초)
			} catch(InterruptedException e) {}
			repaint();	// 자바의 GUI 프로그래밍(AWT, Swing)에서 사용되는 메서드로 컴포넌트를 다시 그리고자 호출
						// repaint() : 시스템에게 나중에 적절한 시점에 다시 그려달라고 요청하는 방식
						//			 : paint() 메서드를 간접적으로 호출 요청
			x += 5;		// x 좌표 5씩 증가
		} // while
	} // run()
	@Override
	public void paint(Graphics g) {
		Dimension d;	// Dimension : 폭과 높이를 가지는 클래스
		d = getSize();	// 프레임 창의 크기를 구하여 Dimension 객체로 반환
		g.setColor(Color.orange);	// 글자색을 오렌지색으로 설정
		g.drawString("JAVA", x, d.height/2);	// 스레드에 의해 x좌표는 항상 변경됨
		
		if (x > d.width) {	// x 좌표가 프레임 폭을 벗어났을 경우
			x = 0;	// 다시 왼쪽 처음ㅇ부터 반복
		}
	} // 무언가를 그리고자 이 메서드를 오버라이딩
	
}

public class ThreadEx04 {

	public static void main(String[] args) {

		GraphicFrame04 f = new GraphicFrame04();
		Thread th = new Thread(f);
		th.start();		// 스레드 시작
		
	}

}
