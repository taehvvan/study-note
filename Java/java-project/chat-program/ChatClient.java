import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;

        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("채팅에 연결되었습니다.");

            // 메시지 수신용 스레드
            new Thread(() -> {
                String msgFromServer;
                try {
                    while ((msgFromServer = in.readLine()) != null) {
                        System.out.println("상대: " + msgFromServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // 메시지 전송 루프
            String userMsg;
            while ((userMsg = userInput.readLine()) != null) {
                out.println(userMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

