package beans;

import java.sql.SQLException;

import beans.*;
import Dao.*;

public class Answer {
	
	private Integer id;
	private User user;
	private Vote vote;
	private Choice choice;
	private String ip;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(Integer user_id) throws SQLException {
		User user = UserDao.getUserById(user_id);
		this.user = user;
	}
	
	public Vote getVote() {
		return vote;
	}
	public void setVote(Integer vote_id) throws SQLException {
		Vote vote = VoteDao.getVoteById(vote_id);
		this.vote = vote;
	}
	
	public Choice getChoice() {
		return choice;
	}
	public void setChoice(Integer choice_id) throws SQLException {
		Choice choice = ChoiceDao.getChoiceById(choice_id);
		this.choice = choice;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}

}
