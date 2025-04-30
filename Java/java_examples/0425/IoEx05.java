/*
 * java.io 패키지의 FileInputStream 특징
 * 		생성자 인자값으로 주어진 File 객체나 파일 경로의 파일로부터 바이트 단위의 데이터를 읽어들이는 API
 */

import java.io.FileInputStream;
import java.io.IOException;

public class IoEx05 {

	public static void main(String[] args) {

		int data = 0;
		String path = ".src/IoEx05.java";
		
		try {
			FileInputStream fis = new FileInputStream(path);
			
			while((data = fis.read()) != -1) {
				System.out.write((char)data);
			}
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		
	}

}
