package MultiThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectClient extends Thread{
	public Socket client;
	public SeverRoom server;
	private String nickName;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean run;

	public ConnectClient(SeverRoom severRoom, Socket client) {
		try {
			this.server=severRoom;
			this.client=client;
			dos= new DataOutputStream(client.getOutputStream());
			dis= new DataInputStream(client.getInputStream());
			run=true;
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
	}
	
	public static void main(String[] args) {
		

	}

}
