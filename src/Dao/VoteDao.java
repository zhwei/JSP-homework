package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBUtils;
import beans.Vote;

public class VoteDao {
	
	public static Vote getVoteById(Integer id) throws SQLException{
		
		// ͨ��ID��ȡͶƱ����
		Connection conn = DBUtils.getConnection();
	    	
    	String sql = "select title, description from votes where id='"+String.valueOf(id)+"';";
    	Statement stat = conn.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	Vote vote = new Vote();
    	while(rs.next()){
    		vote.setId(id);
    		vote.setTitle(rs.getString("title"));
    		vote.setDescription(rs.getString("description")); 
    	}
    	return vote;
	}

	public static Integer createVote(String title, String description) throws SQLException{
		// ����ͶƱ
		Connection conn = DBUtils.getConnection();
    	
		String sql = "insert into votes(title, description) values('"+title+"','"+description+"');";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}
	
	public static Integer updateVote(Integer id,String title, String description) throws SQLException{
		// ����ͶƱ��Ϣ
		Connection conn = DBUtils.getConnection();
    	
		String sql = "update votes set title='"+title+"',description='"+description+"' where id="+String.valueOf(id)+";";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}
	
	public static Integer deleteVote(Integer id) throws SQLException{
		// ɾ��
		Connection conn = DBUtils.getConnection();
    	
		String sql = "delete from votes where id="+String.valueOf(id)+";";
		Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        
		return i;
	}

}
