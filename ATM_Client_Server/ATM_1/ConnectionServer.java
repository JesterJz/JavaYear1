package ATM_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ConnectionServer {
	public static Socket skClient;
	public static DataOutputStream OutGui;
	public static DataInputStream Input;

	public void Connec(int i) {
		try {
			skClient = new Socket("localhost", i);
			System.out.println("Server Ok Connect");
			OutGui = new DataOutputStream(skClient.getOutputStream());
			Input = new DataInputStream(skClient.getInputStream());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
