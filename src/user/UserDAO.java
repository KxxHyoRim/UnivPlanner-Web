package user;

import java.sql.*;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimeZone=UTC";
			String id = "root";
			String pw = "1234";  
			
			Class.forName("com.mysql.jdbc.Driver");

			
			 conn = DriverManager.getConnection(dbURL, id, pw);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�α��� ����
	public int login(String userID, String userPassword) {
		String sql = "select userPassword from user where userID = ?";
		try {
			pstmt = conn.prepareStatement(sql); //sql�������� ��� ��Ų��
			pstmt.setString(1, userID); //ù��° '?'�� �Ű������� �޾ƿ� 'userID'�� ����
			rs = pstmt.executeQuery(); //������ ������ ����� rs�� ����
			
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1; //�α��� ����
				}
				
				else
					return 0; //��й�ȣ Ʋ��
			}
			
			return -1; //���̵� ����
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return -2; //����
	}
	
	public int join(User user) {
		  String sql = "insert into user values(?, ?, ?, ?)";
		  try {
			 if (conn == null) {
					System.out.println("NULL");
			 }
		  
			 else {
					System.out.println("Not NULL");
			 }
			
		    pstmt = conn.prepareStatement(sql);

			 if (conn == null) {
					System.out.println("NULL");
			 }
		  
			 else {
					System.out.println("Not NULL");
			 }
			 
		    pstmt.setString(1, user.getUserID());
		    pstmt.setString(2, user.getUserPassword());
		    pstmt.setString(3, user.getUserName());
		    pstmt.setString(4, user.getUserSchool());
		    return pstmt.executeUpdate();
		  }
		  
		  catch (Exception e) {
		 	e.printStackTrace();
		  }
		  
		  return -1;
	}
}
