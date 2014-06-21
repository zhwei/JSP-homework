package beans;

import java.sql.SQLException;

import beans.Vote;
import Dao.VoteDao;

public class Choice {

	private Integer id;
	private Vote vote;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Vote getVote() {
		return vote;
	}
	
	public void setVote(Integer vote_id) throws SQLException {
		Vote vote = VoteDao.getVoteById(vote_id);
		this.vote = vote;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
