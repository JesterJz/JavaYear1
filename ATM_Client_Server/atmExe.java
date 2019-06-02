import java.io.File;
import java.sql.*;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.tree.ExpandVetoException;


public class atmExe {
    private static Connection conn;
    private static String mkh;
    private static String today;
    private static String thoiGian;
    public static int LOI = 0;
    atmExe(String path){
    	try
    	{
    	   conn =  DriverManager.getConnection("jdbc:ucanaccess://"+path);
    	   Calendar cal = Calendar.getInstance();
   		   today = cal.get(Calendar.DATE) +"/"+ (cal.get(Calendar.MONTH)+1)+ "/" +cal.get(Calendar.YEAR);
   		   thoiGian = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    atmExe(){
    	try
    	{
    	//	File file = new File(".");
		//	String absolutePath = file.getAbsolutePath();
		//	String temp= "";
		//	char[] k = absolutePath.toCharArray();
		//	for(int i=0;i<absolutePath.length();i++)
		//		 if (k[i] == '.') temp+="ATM.accdb";
		//		 else temp+=k[i];
    	    conn =  DriverManager.getConnection("jdbc:ucanaccess://ATM.accdb");
    	    Calendar cal = Calendar.getInstance();
    	    today = cal.get(Calendar.DATE) +"/"+ (cal.get(Calendar.MONTH)+1)+ "/" +cal.get(Calendar.YEAR);
    	    thoiGian = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		
    	}
    }
    public static void closeConn()
    {
    	try
    	{
    		conn.close();
    	}
    	catch(Exception e){
    		
    	}
    }
    public static ResultSet getDataAcc()
    {
    	try
    	{
    	Statement cmd = conn.createStatement();
    	ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG where MKH = '"+mkh+"'");
    	return kq;
    	}
    	catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    public static ResultSet getDataAccFull()
    {
    	try
    	{
    	Statement cmd = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    	ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG");
    	return kq;
    	}
    	catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    public static ResultSet getDataHis()
    {
    	try
    	{
    	Statement cmd = conn.createStatement();
    	ResultSet kq = cmd.executeQuery("SELECT * FROM LICHSU where MKH = '"+mkh+"' and NGAYGD ='"+today+"'");
    	return kq;
    	}
    	catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
    public static boolean changePin(String pin1, String pin2)
	{
		try
		{   
		 if ((pin1.length() > 4) || (pin2.length() > 4)) { LOI =1; return false; }
		 else
		 {
			Statement cmd = conn.createStatement();
			ResultSet kq = cmd.executeQuery("Select * from KHACHHANG where PIN ='"+pin1+"'");
			kq.next();
			if (kq.getRow() == 0) {
				LOI = 2;
				return false;
			}
			else 
			{
			    cmd.executeUpdate("UPDATE KHACHHANG SET PIN = '"+pin2+"' WHERE MKH = '"+mkh+"'");
			    return true;
			}		
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOI = 3;
			return false;
		}
	}
    
    public static boolean sendMoney(long tien)
	{
		try
		{
			Calendar cal = Calendar.getInstance();
			today = cal.get(Calendar.DATE) +"/"+ (cal.get(Calendar.MONTH)+1)+ "/" +cal.get(Calendar.YEAR);
	   		thoiGian = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
			if (tien%50000!=0) {
				LOI = 4;
				return false;
			}
			else
			{
			long temp = 0;						
			Statement cmd = conn.createStatement();
			ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH='"+mkh+"'");
			kq.next(); temp = Long.parseLong(kq.getString("TIEN").toString());
			temp+=tien;
			cmd.executeUpdate("UPDATE KHACHHANG SET TIEN = '"+temp+"' WHERE MKH='"+mkh+"'");
			cmd.executeUpdate("UPDATE LICHSU SET NGAYGD = '"+today+"', TIENGUI = '"+(tien+" Thời gian: "+thoiGian)+"', THOIGIAN = '"+thoiGian+"' WHERE MKH='"+mkh+"' and NGAYGD ='"+today+"'");		
			return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOI = 3;
			return false;
		}
	}
    
    public static boolean moveMoney(String machuyen, long tien)
	{
		try
		{
			Calendar cal = Calendar.getInstance();
			today = cal.get(Calendar.DATE) +"/"+ (cal.get(Calendar.MONTH)+1)+ "/" +cal.get(Calendar.YEAR);
	   		thoiGian = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
			long temp = 0;
			Statement cmd = conn.createStatement();
			ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH='"+mkh+"'");
			kq.next(); temp = Long.parseLong(kq.getString("TIEN").toString());
			if ((temp < 50000) || (temp < tien)) {
				LOI = 5;
				return false;
			}
			else
			{
				if (temp - tien < 50000) {
					LOI = 6;
					return false;
				}
				else
				{
					long kt = 0;
					kq = cmd.executeQuery("SELECT * FROM CHOPHEP WHERE MKH='"+mkh+"' and NGAYGD = '"+today+"'");
					kq.next(); kt = Long.parseLong(kq.getString("TIENCHUYEN").toString());
					if (tien > kt) {
						LOI = 7;
						return false;
					}
					else
					{
				     kt = kt - tien;
			 	     kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH='"+machuyen+"'");
			 	     long temp2 = 0;
				     kq.next(); temp2 = Long.parseLong(kq.getString("TIEN").toString());
				     temp2 = temp2 + tien;
				     cmd.executeUpdate("UPDATE KHACHHANG SET TIEN = '"+temp2+"' WHERE MKH='"+machuyen+"'");
				     temp = temp - tien;
				     cmd.executeUpdate("UPDATE KHACHHANG SET TIEN = '"+temp+"' WHERE MKH='"+mkh+"'");
				     cmd.executeUpdate("UPDATE LICHSU SET NGAYGD = '"+today+"', TIENCHUYEN = '"+(tien+" Thời gian: "+thoiGian)+"', THOIGIAN = '"+thoiGian+"' WHERE MKH='"+mkh+"' and NGAYGD ='"+today+"'" );		
				     cmd.executeUpdate("UPDATE CHOPHEP SET TIENCHUYEN = '"+kt+"' WHERE MKH='"+mkh+"'  ");		
				     return true;
					}
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOI = 9;
			return false;
		}
	}
    
    public static boolean checkPin(String pin,String mathe)
    {
    	try
    	{
    		Calendar cal = Calendar.getInstance();
			today = cal.get(Calendar.DATE) +"/"+ (cal.get(Calendar.MONTH)+1)+ "/" +cal.get(Calendar.YEAR);
	   		thoiGian = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
    		Statement cmd = conn.createStatement();
    		ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE PIN ='"+pin+"' AND MATHE = '"+mathe+"'");    		
    		kq.next();
    		if (kq.getRow() == 0) return false;
    		else 
    			{
    			 mkh = kq.getString("MKH");
    			 kq = cmd.executeQuery("SELECT * FROM CHOPHEP WHERE MKH ='"+mkh+"' and NGAYGD = '"+today+"'");
    			 kq.next();
    			 if (kq.getRow() == 0) 
    			 {
    				 cmd.executeUpdate("INSERT INTO CHOPHEP(NGAYGD,MKH,TIENRUT,TIENCHUYEN) VALUES('"+today+"','"+mkh+"','20000000','20000000')");
    				 ResultSet kq2 = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH ='"+mkh+"'");
    				 kq2.next();
    				 String hoten = kq2.getString("HOTEN").toString();
    				 kq = cmd.executeQuery("SELECT * FROM LICHSU WHERE MKH ='"+mkh+"' and NGAYGD = '"+today+"'");
    				 kq.next();
    				 if (kq.getRow() == 0)
    					 cmd.executeUpdate("INSERT INTO LICHSU(MKH,HOTEN,NGAYGD,TIENRUT,TIENGUI,TIENCHUYEN,THOIGIAN) VALUES('"+mkh+"','"+hoten+"','"+today+"','0','0','0','0')");		 
    				 return true;
    			 }
    			 else
    			 {
    				 ResultSet kq2 = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH ='"+mkh+"'");
    				 kq2.next();
    				 String hoten = kq2.getString("HOTEN").toString();
    				 kq = cmd.executeQuery("SELECT * FROM LICHSU WHERE MKH ='"+mkh+"' and NGAYGD = '"+today+"'");
    				 kq.next();
    				 if (kq.getRow() == 0)
    					 cmd.executeUpdate("INSERT INTO LICHSU(MKH,HOTEN,NGAYGD,TIENRUT,TIENGUI,TIENCHUYEN,THOIGIAN) VALUES('"+mkh+"','"+hoten+"','"+today+"','0','0','0','"+thoiGian+"')");		 
    				 return true;	 
    			 }
    			}
    	}
    	catch(Exception e)
    	{
    		LOI = 3;
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public static boolean getMonney(long tien)
    {
    	try
    	{
    		Calendar cal = Calendar.getInstance();
			today = cal.get(Calendar.DATE) +"/"+ (cal.get(Calendar.MONTH)+1)+ "/" +cal.get(Calendar.YEAR);
	   		thoiGian = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
    		if (tien%50000!=0) {
    			LOI = 4;
    			return false; 
    		}
    		else
    		{
    	  	 Statement cmd = conn.createStatement();
    		 ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH = '"+mkh+"'");
    		 kq.next();
    		 long temp = 0;
    		 temp = Long.parseLong(kq.getString("TIEN"));
    		 if (tien > temp) {
    			 LOI = 5;
    			 return false;
    		 }
    		 else if (temp - tien < 50000) 
    		 {
    			 LOI = 6;
    			 return false;
    		 }
    		 else
    		 {
    			long kt = 0;
			    kq = cmd.executeQuery("SELECT * FROM CHOPHEP WHERE MKH='"+mkh+"' and NGAYGD = '"+today+"'");
				kq.next(); kt = Long.parseLong(kq.getString("TIENRUT"));
				if (tien > kt) {
					LOI = 8;
					return false;
				}
				else
				{
    			 temp-=tien;
    			 kt-=tien;
    			 cmd.executeUpdate("UPDATE KHACHHANG SET TIEN = '"+temp+"' WHERE MKH = '"+mkh+"'");
    			 cmd.executeUpdate("UPDATE LICHSU SET NGAYGD = '"+today+"', TIENRUT = '"+(tien+" Thời gian: "+thoiGian)+"', THOIGIAN = '"+thoiGian+"' WHERE MKH='"+mkh+"' and NGAYGD ='"+today+"'");		
				 cmd.executeUpdate("UPDATE CHOPHEP SET TIENRUT = '"+kt+"' WHERE MKH='"+mkh+"' and NGAYGD ='"+today+"'");		
    			 return true;
				}
    		 }
    		}
    	}
    	catch(Exception e)
    	{
    		LOI = 3;
    		e.printStackTrace();
    		return false;
    	}
      }
    
    public static String getLoi()
    {
    	//123456789
    	switch(LOI)
    	{
    	case 1: return "Mã PIN tối đa 4 kí tự "; 
    	case 2: return "Mã PIN sai "; 
    	case 3: return "<html><p>Bạn không thể thực hiện giao dịch trong lúc này<p><html>"; 
    	case 4: return "Số tiền nhập vào không hợp lệ."; 
    	case 5: return "<html><p>Số tiền quý khách không đủ để thực hiện giao dịch<p><html>"; 
    	case 6: return "<html><p>Số tiền còn lại sau khi gửi/rút phải tối thiểu 50000<p><html>"; 
    	case 7: return "<html><p>Hôm nay quý khách chỉ có thể chuyển tối đa 20 triệu<p><html>"; 
    	case 8: return "<html><p>Hôm nay quý khách chỉ có thể rút tối đa 20 triệu<p><html>"; 
    	case 9: return "<html><p>Quý khách vui lòng kiểm tra: Mệnh giá gửi, Tài khoản nhận<p><html>"; 
    	case 10: return "Đã tồn tại"; 
    	case 11: return "Đã tồn tại"; 
    	case 12: return "Tối đa 3 số"; 
    	case 13: return "Nhập số"; 
    	case 14: return "Nhập số"; 
    	default: return "";
    	}
    	
    }
    
    public static boolean ThemKH(String ma, String ten, String gt, String p, String mathe)
    {
    	try
    	{
    	
    		if (ma.length() > 3 || ma.length() < 3) 
    			{
    			  LOI = 12;
    			  return false;
    			}
    		else {
    			try
    	    	{
    				int i = Integer.parseInt(ma);
    				try
    				{
    					int j = Integer.parseInt(p);
    				}
    				catch (Exception loi) {
    					  LOI = 13;
    	    			  return false;
					}
    	    	}
    			catch (Exception loi) {
    				 LOI = 14;
       			     return false;
				}
    		}	
    	Statement cmd = conn.createStatement();
    	ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MATHE ='"+mathe+"'");
    	kq.next();
    	if (kq.getRow() == 0)
    	 {
    		kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH ='"+ma+"'");
        	kq.next();
        	if (kq.getRow() == 0)
        	{
    	    cmd.executeUpdate("INSERT INTO KHACHHANG(MKH,HOTEN,GIOITINH,PIN,TIEN,MATHE) VALUES('"+ma+"','"+ten+"','"+gt+"','"+p+"','50000','"+mathe+"')");
    	    return true;
        	} else
        	{
        	 LOI = 11;
       		 return false; 
        	}
    	 }else {
    		 LOI = 10;
    		 return false; 
    	    }
    	}
    	catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public static boolean XoaKH(String ma)
    {
    	try
    	{
    	Statement cmd = conn.createStatement();
    	ResultSet kq = cmd.executeQuery("SELECT * FROM KHACHHANG WHERE MKH = '"+ma+"'");
    	kq.next();
    	if (kq.getRow() == 0) return false;
    	else
    	 {
    	 cmd.executeUpdate("DELETE FROM KHACHHANG WHERE MKH = '"+ma+"'");
    	 return true;
         }
    	}
	    catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }
    }
    
}
