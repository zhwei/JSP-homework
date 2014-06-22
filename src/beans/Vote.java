package beans;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.ChoiceDao;

public class Vote {
	/*
	 * CREATE TABLE votes(id INTEGER PRIMARY KEY AUTOINCREMENT, title varchar not null, description varchar not null);
	 * 
	 * * */
	private Integer id;
	private String title;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<Choice> getChoiceList() throws SQLException{
		
		ArrayList<Choice> choicelist = ChoiceDao.getChoiceListForVote(this.id);
		
		return choicelist;
	}

}
