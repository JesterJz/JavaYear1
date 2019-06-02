package ATM_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class ServerATM {

	private static ServerSocket severSk;
	private static Socket s;
	private static DataInputStream inPut;
	private static DataOutputStream output;
	private Connection conn;
	private Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsm;

	private void connecDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountatm", "root", "");
			System.out.println("Connect SQL Success");
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
	}

	public ServerATM() {
		try {
			connecDB();
			stmt = conn.createStatement();
			severSk = new ServerSocket(9110);
			s = severSk.accept();
			System.out.println("Connection Server Succecfull");
			inPut = new DataInputStream(s.getInputStream());
			output = new DataOutputStream(s.getOutputStream());

			String ID = null;
			String Name = null, tmp;
			int PassServer = 0;
			Long Money = null;

			while (true) {
				tmp = inPut.readLine();

				if (tmp.equals("ID")) {
					ID = inPut.readLine();
					rs = stmt.executeQuery(" SELECT * FROM account WHERE ID='" + ID + "'");
					rsm = rs.getMetaData();
					// lay du lieu cua cac cot ve
					while (rs.next()) {
						Name = rs.getString("Name");
						PassServer = rs.getInt("Pass");
						Money = rs.getLong("Money");
					}
					output.writeBytes(Name + "\n");
				}

				if (tmp.equals("Password")) {
					int Passinput;
					Passinput = Integer.parseInt(inPut.readLine());
					if (Passinput != PassServer) {
						output.writeBytes(0 + "\n");
					} else {
						output.writeBytes(1 + "\n");
					}
				}

				if (tmp.equals("Transfer")) {
					String IDTransfer;
					Long MoneyTransfer;
					IDTransfer = inPut.readLine();
					MoneyTransfer = Long.valueOf(inPut.readLine());
					if (Money < MoneyTransfer) {
						output.writeBytes(0 + "\n");
					} else {
						output.writeBytes(1 + "\n");
						Money = Money - MoneyTransfer;
						int n = stmt.executeUpdate("UPDATE Account SET Money='" + Money + "' WHERE ID = '" + ID + "'");
						int m = stmt.executeUpdate("UPDATE Account SET Money = Money +'" + MoneyTransfer
								+ "' WHERE ID = '" + IDTransfer + "'");
						if (m > 0 && n > 0) {
							output.writeBytes("succes" + "\n");
						} else {
							output.writeBytes("error" + "\n");
						}
					}
				}

				if (tmp.equals("Withdrawal")) {
					Long MoneyWithdrawal;
					MoneyWithdrawal = Long.valueOf(inPut.readLine());
					if (Money < MoneyWithdrawal) {
						output.writeBytes(0 + "\n");
					} else {
						output.writeBytes(1 + "\n");
						System.out.println(LocalDateTime.now());
						LocalDateTime d= LocalDateTime.now();
						int m = stmt.executeUpdate("UPDATE Account SET Ngay='" + d + "' WHERE  ID ='" + ID + "'");
						Money = Money - MoneyWithdrawal;
						int n = stmt.executeUpdate("UPDATE Account SET Money='" + Money + "' WHERE  ID ='" + ID + "'");
						if (n > 0)
							output.writeBytes("succes" + "\n");
						else
							output.writeBytes("error" + "\n");
					}
				}

				if (tmp.equals("ChangePIN")) {
					int MKC, MKM;
					MKC = Integer.parseInt(inPut.readLine());
					MKM = Integer.parseInt(inPut.readLine());
					if (MKC != PassServer) {
						output.writeBytes(1 + "\n");
					} else {
						output.writeBytes(0 + "\n");
						int n = stmt.executeUpdate("UPDATE Account SET Pass='" + MKM + "' WHERE  ID ='" + ID + "'");
						if (n > 0)
							output.writeBytes("succes" + "\n");
						else
							output.writeBytes("error" + "\n");
					}
				}

				if (tmp.equals("Current money")) {
					output.writeBytes(Money + "\n");
				}

				if (tmp.equals("ADMIN")) {
					String User, Password, UserSV = null, PassSV = null;
					User = inPut.readLine();
					Password = inPut.readLine();
					rs = stmt.executeQuery(" SELECT * FROM ADMIN WHERE User='" + User + "'");
					rsm = rs.getMetaData();
					// lay du lieu cua cac cot ve
					while (rs.next()) {
						UserSV = rs.getString("User");
						PassSV = rs.getString("Password");
					}
					if (User.equals(UserSV) && Password.equals(PassSV)) {
						output.writeBytes("Right" + "\n");
					} else {
						output.writeBytes("Wrong" + "\n");
					}
				}

				if (tmp.equals("Update")) {
					String IDUp = inPut.readLine();
					String NameUp = inPut.readLine();
					String MoneyUp = inPut.readLine();
					String PassUp = inPut.readLine();
					int n = stmt.executeUpdate("Update account set Name='" + NameUp + "',Money='" + MoneyUp + ",Pass ="
							+ PassUp + "', WHERE ID='" + IDUp + "'");
					if (n > 0)
						output.writeBytes("succes" + "\n");
					else
						output.writeBytes("error" + "\n");
				}
				if (tmp.equals("Creat")) {
					rs = stmt.executeQuery("select COUNT(*) AS ID FROM account");
					rsm = rs.getMetaData();
					int IDCr = 0;
					while (rs.next()) {
						IDCr = rs.getInt("ID");
					}
					IDCr++;
					String NameCr = inPut.readLine();
					Long MoneyCr = Long.valueOf(inPut.readLine());
					int PassCr = Integer.parseInt(inPut.readLine());
					output.writeBytes(IDCr + "\n");
					int n = stmt.executeUpdate("INSERT account ( ID, Name, Money,Pass ) VALUES ('" + IDCr + "','"
							+ NameCr + "','" + MoneyCr + "','" + PassCr + "')");
					if (n > 0)
						output.writeBytes("succes" + "\n");
					else
						output.writeBytes("error" + "\n");
				}
				if (tmp.equals("Delete")) {
					String IDDe = inPut.readLine();
					int n = stmt.executeUpdate("Delete from QL WHERE MaNV='" + IDDe + "'");
					if (n > 0)
						output.writeBytes("succes" + "\n");
					else
						output.writeBytes("error" + "\n");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new ServerATM();
	}

}
