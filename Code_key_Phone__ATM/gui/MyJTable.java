package gui;
 
import java.sql.Statement;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class MyJTable extends JFrame {
 
    //private static final long serialVersionUID = -6464587060272247354L;
    private Connection connect = null;
    private JTable jtable = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
	private Connection conn;
 
     
    public MyJTable(){
        String []colsName = {"Mã hàng", "Tên hàng"};
        tableModel.setColumnIdentifiers(colsName);  // đặt tiêu đề cột cho tableModel
        jtable.setModel(tableModel);    // kết nối jtable với tableModel
         
        initComponent();    // Khởi tạo các components trên JFrame
        connectSQL();       // kết nối cơ sở dữ liệu
        updateData(view()); // gọi hàm view để truy suất dữ liệu sau đó truyền cho hàm updateData để đưa dữ liệu vào tableModel và hiện lên Jtable trong Frame
    }
     
    public void updateData(ResultSet result){
        String []colsName = {"Mã hàng", "Tên hàng"};
        tableModel.setColumnIdentifiers(colsName); // Đặt tiêu đề cho bảng theo các giá trị của mảng colsName
 
        try {
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[2];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                tableModel.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
     
    public void initComponent(){
        this.setSize(400, 200);
        //jtable = new JTable();
        // Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
        JScrollPane scroll = JTable.createScrollPaneForTable(jtable);   
        this.add(scroll); // Đưa thanh cuộn vào Frame (hiện thanh cuộn trên frame)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().add(jtable, BorderLayout.SOUTH);
        this.setVisible(true);
    }
     
    public void connectSQL(){
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://SCD040220\\SQLEXPRESS;databaseName=ATM;user=sa;password=jester");
			System.out.println("Connect Sucess");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
         
    }
     
    public ResultSet view(){
        ResultSet result = null;
        String sql = "SELECT * FROM DaTaATM";
        try {
            Statement statement = (Statement) conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
     
    public static void main(String[] args) {
        new MyJTable();
    }
 
}