package demo;

public class User {

	private String id;
	private String username;
	private String pwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public User(String id, String username, String pwd) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
	}
	
}
