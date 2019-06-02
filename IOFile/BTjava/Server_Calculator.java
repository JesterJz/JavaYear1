package BTjava;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Calculator {

	public static void main(String[] args) {

		try {
			ServerSocket SeverSk = new ServerSocket(6501);
			Socket s = SeverSk.accept();
			System.out.println("connection succecfull");
			DataInputStream InPut = new DataInputStream(s.getInputStream());
			DataOutputStream Output = new DataOutputStream(s.getOutputStream());
			String ToanTu;
			double ToanHang1;
			double ToanHang2;
			double KetQua = 0;
			while (true) {
				ToanHang1 = Double.parseDouble(InPut.readLine());
				System.out.println(ToanHang1);
				ToanTu = InPut.readLine();
				System.out.println(ToanTu);
				ToanHang2 = Double.parseDouble(InPut.readLine());
				System.out.println(ToanHang2);
				if (ToanTu.equals("+"))
					KetQua = ToanHang1 + ToanHang2;
				if (ToanTu.equals("-"))
					KetQua = ToanHang1 - ToanHang2;
				if (ToanTu.equals("*"))
					KetQua = ToanHang1 * ToanHang2;
				if (ToanTu.equals("/"))
					KetQua = ToanHang1 / ToanHang2;
				Output.writeBytes(String.valueOf(KetQua) + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
