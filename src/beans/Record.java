package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Books;
import beans.Bill;
import beans.*;

public class Record {
	
	private Integer id;
	private Integer uid;	// ���������¼�����û�
	private String date;	// ��������
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public ArrayList<Bill> getMyBills(){	// ��ȡ���ڱ��������¼������ͼ����Ŀ 
		String dbUrl = "jdbc:sqlite:d:/db.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			Statement stat = conn.createStatement();
			String sql1 = "select id, bid, count from bills where id='"+String.valueOf(this.id)+"'";
			ResultSet rs = stat.executeQuery(sql1);
			
			ArrayList<Bill> billlist = new ArrayList<Bill>();
			while(rs.next()){
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setBid(rs.getInt("bid"));
				bill.setCount(rs.getInt("count"));
				billlist.add(bill);
			}
			stat.close();
			conn.close();
			return billlist;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUser(){		// ��ȡ���������¼�������û�
		
		String dbUrl = "jdbc:sqlite:d:/db.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			Statement stat = conn.createStatement();
			String sql1 = "select id, username, sex from users where id='"+String.valueOf(this.uid)+"'";
			ResultSet rs = stat.executeQuery(sql1);
			User user = new User();
			while(rs.next()){
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setSex(rs.getString("sex"));
			}
			stat.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
