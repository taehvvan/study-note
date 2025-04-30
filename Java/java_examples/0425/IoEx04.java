/*
 * java.io 패키지의 File 클래스 API 특징
 * 		1. File 클래스를 활용하면 일반 게시판에 파일 첨부 기능이 들어간 자료실을 만들 수 있다.
 * 		   여기서는 이 클래스 하위의 내장메서드인 list()를 활용해서 디렉토리에 있는 파일 목록을 반환한다.
 */

import java.io.File;

public class IoEx04 {

	public static void main(String[] args) {
		
		File dirFile = new File("./src");
		String[] fileList = dirFile.list();
		
		for(int i = 0; i < fileList.length; i++) {
			System.out.println(fileList[i]);
		}
		System.out.println("\n=======================\n");
		
		for(String fileName : fileList) {
			System.out.println(fileName);
		}
		System.out.println("\n=======================\n");

	}

}
