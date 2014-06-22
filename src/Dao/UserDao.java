package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBUtils;
import beans.User;

public class UserDao {
	
	public static User getUserById(Integer id) throws SQLException{
		
		// ͨ��ID��ȡ�û�����
		Connection conn = DBUtils.getConnection();
	    	
    	String sql = "select username, password, sex from users where id='"+String.valueOf(id)+"';";
    	Statement stat = conn.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	User user = new User();
    	while(rs.next()){
    		user.setId(id);
    		user.setUsername(rs.getString("username"));
    		user.setPassword(rs.getString("password")); 
    		user.setSex(rs.getString("sex"));
    	}
    	return user;
	}
	
	public static User getUserByUsername(String username) throws SQLException{
		
		// ͨ���û�����ȡ�û�����
		Connection conn = DBUtils.getConnection();
	    	
    	String sql = "select id, password, sex from users where username='"+username+"';";
    	Statement stat = conn.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	User user = new User();
    	while(rs.next()){
    		user.setId(rs.getInt("id"));
    		user.setUsername(username);
    		user.setPassword(rs.getString("password"));    		
    		user.setSex(rs.getString("sex"));
    	}
    	return user;
	}	
	
	public static Integer createUser(String username, String password, String sex) throws SQLException{
		// �����û�
		Connection conn = DBUtils.getConnection();
    	
		String sql = "insert into users(username, password, sex) values('"+username+"','"+password+"','"+ sex +"');";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}
	
	public static Integer updateUser(Integer id, String username, String sex) throws SQLException{
		// �����û���Ϣ
		Connection conn = DBUtils.getConnection();
    	
		String sql = "update users set username='"+username+"',sex='"+sex+"' where id="+String.valueOf(id)+";";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}
	
	public static Integer deleteUser(Integer id) throws SQLException{
		// ɾ��
		Connection conn = DBUtils.getConnection();
    	
		String sql = "delete from users where id="+String.valueOf(id)+";";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}

}
