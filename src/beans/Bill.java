package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Books;

public class Bill {
	
	private Integer id;
//	private Integer uid;	// 用户id
	private Integer bid;	// 图书id
	private Integer count;	// 数目
	private Integer rid;	// 消费记录id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Integer getUid() {
//		return uid;
//	}
//	public void setUid(Integer uid) {
//		this.uid = uid;
//	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	public Books getBook(Integer bid){
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
			String sql1 = "select name, author, price from books where id='"+String.valueOf(bid)+"'";
			ResultSet rs = stat.executeQuery(sql1);
			
			Books book = new Books();
			while(rs.next()){
				book.setId(bid);
				book.setName(rs.getString("name")); 
				book.setAuthor(rs.getString("author"));
				book.setPrice(Double.valueOf(rs.getString("price")));
				book.setCount(1);
			}
			stat.close();
			conn.close();
			return book;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCount() {
		return count;
	}
	public void setBillid(Integer recordid) {
		this.rid = recordid;
	}
	public Integer getBillid() {
		return rid;
	}
	
	
}
