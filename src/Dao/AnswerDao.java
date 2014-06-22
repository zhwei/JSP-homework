package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBUtils;
import beans.Vote;
import beans.Choice;

public class AnswerDao {
	
	public static Choice getChoiceById(Integer id) throws SQLException{
		
		// 通过ID获取选项
		Connection conn = DBUtils.getConnection();
	    	
    	String sql = "select vote_id, description from choices where id='"+String.valueOf(id)+"';";
    	Statement stat = conn.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	Choice choice = new Choice();
    	while(rs.next()){
    		choice.setId(id);
    		choice.setVote(Integer.parseInt(rs.getString("vote_id")));
    		choice.setDescription(rs.getString("description")); 
    	}
    	return choice;
	}

	public static Integer createChoice(Integer vote_id, String description) throws SQLException{
		// 创建选项
		Connection conn = DBUtils.getConnection();
    	
		String sql = "insert into choices(vote_id, description) values('"+String.valueOf(vote_id)+"','"+description+"');";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}
	
	public static Integer updateChoice(Integer id, Integer vote_id, String description) throws SQLException{
		// 更新选项
		Connection conn = DBUtils.getConnection();
    	
		String sql = "update choices set vote_id='"+String.valueOf(vote_id)+"',description='"+description+"' where id="+String.valueOf(id)+";";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}
	
	public static Integer deleteChoice(Integer id) throws SQLException{
		// 删除
		Connection conn = DBUtils.getConnection();
    	
		String sql = "delete from choices where id="+String.valueOf(id)+";";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}

}
