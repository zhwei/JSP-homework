package beans;

public class User {
	private Integer id;
	private String username;	//用户名
	private String password;	// 密码
	private String sex;			// 性别
	
//	public User(Integer id, String Username, String sex){
//		this.setId(id);
//		this.setUsername(username);
//		this.setSex(sex);
//	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
}
