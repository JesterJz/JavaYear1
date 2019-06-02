package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Chat_Server {
	public static Vector<Socket> ListConnec;
	public static Vector<String> CurrentUsers;
	private ServerSocket ServerSk;

	public static void main(String[] args) {
		try {
			ServerSocket ServerSk = new ServerSocket(6501);
			System.out.println("waiting for clients");
			while(true)
			{
				Socket s = ServerSk.accept();
				ListConnec.add(s);
				System.out.println("Client connected from: "+s.getLocalAddress().getHostName());;
				AddUserName(s);
				Chat_Server_Return chat = new Chat_Server_Return(s);
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void AddUserName(Socket s) throws IOException {
		Scanner InPut = new Scanner(s.getInputStream());
		String UserName = InPut.nextLine();
		CurrentUsers.add(UserName);
		for (int i = 1; i <= ListConnec.size(); i++) {
			Socket Temp_s = (Socket) ListConnec.get(i-1);
			PrintWriter out = new PrintWriter(Temp_s.getOutputStream());
			out.println("#?!"+ CurrentUsers);
			out.flush();
		}
		
	}

}
