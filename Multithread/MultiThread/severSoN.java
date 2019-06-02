package MultiThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class severSoN {

	
	private static ServerSocket SeverSk;
	private static Socket s;

	public static void main(String[] args) 
	{
		try {
			SeverSk = new ServerSocket(6501);
			while(true) {
			Socket s = SeverSk.accept();
			System.out.println("connection succecfull");
			DataInputStream Input = new DataInputStream(s.getInputStream());
			String InPut = Input.readLine();
			DataOutputStream outsever = new DataOutputStream(s.getOutputStream());
			outsever.writeBytes(InPut+" Cat");
			s.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}