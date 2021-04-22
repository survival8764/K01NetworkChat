package chat7;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// 클라이언트가 입력한 메세지를 전송해주는 쓰레드 클래스
public class Sender extends Thread{
	
	Socket socket;
	PrintWriter out = null;
	String name;
	
	// 클라이언트가 접속시 사용했던 Socket객체를 기반으로 출력스트림 생성.
	public Sender(Socket socket, String name) {
		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			this.name = name;
		}
		catch(Exception e) {
			System.out.println("예외>Sender>생성자:"+ e);
		}
	}
	
	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		
		try {
			out.println(name);
			
			while(out != null) {
				try {
					String s2 = s.nextLine();
					if(s2.equalsIgnoreCase("O")) {
						break;
					}
					else {
						out.println(s2);
					}
				}
				catch (Exception e) {
					System.out.println("예외>Sender>ru1:"+ e);
				}
			}
			out.close();
			socket.close();
		}
		catch(Exception e) {
			System.out.println("예외>Sender>run2: "+ e);
		}
	}

}
