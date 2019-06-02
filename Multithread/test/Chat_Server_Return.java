package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

public class Chat_Server_Return implements Runnable {

	Socket s;
	private Scanner Input;
	private PrintWriter Out;
	String Message = "";

	public Chat_Server_Return(Socket s) {
		this.s = s;
	}

	public void CheckConnect() throws IOException {
		if (!s.isConnected()) {
			for (int i = 1; i <= Chat_Server.ListConnec.size(); i++) {
				if (Chat_Server.ListConnec.get(i) == s) {
					Chat_Server.ListConnec.remove(i);
				}
			}
			for (int i = 1; i <= Chat_Server.ListConnec.size(); i++) {
				Socket Temp_sock = (Socket) Chat_Server.ListConnec.get(i - 1);
				PrintWriter Temp_Out = new PrintWriter(Temp_sock.getOutputStream());
				Temp_Out.println(Temp_sock.getLocalAddress().getHostName() + " Disconneted");
				Temp_Out.flush();
				System.out.println(Temp_sock.getLocalAddress().getHostName() + " Disconneted");

			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		try {
			try {
				Input = new Scanner(s.getInputStream());
				Out = new PrintWriter(s.getOutputStream());
				while(true)
				{
					CheckConnect();
					if(!Input.hasNext())
					{
						return;
					}
					Message = Input.nextLine();
					System.out.println("Client said: "+ Message);
					for (int i = 1; i <= Chat_Server.ListConnec.size(); i++) 
					{
						Socket Temp_sock = (Socket) Chat_Server.ListConnec.get(i-1);
						PrintWriter Temp_Out = new PrintWriter(Temp_sock.getOutputStream());
						Temp_Out.println(Temp_sock.getLocalAddress().getHostName()+" Disconneted");
						Temp_Out.flush();
						System.out.println("Sent to: " + Temp_sock.getLocalAddress().getHostName());
					}
				}
			}
			finally 
			{
				s.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
