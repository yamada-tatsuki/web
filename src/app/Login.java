package app;

/**
 * 社員情報を保持するクラス
 *
 * @author mano
 *
 */
public class Login {

	public Login() {
		super();
	}

	/** 部署ID */
	private String shainid;

	/** 部署名 */
	private String password;

	private String role;


	public String getshainId() {
		return shainid;
	}

	public void setshainId(String shainid) {
		this.shainid = shainid;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getrole() {
		return role;
	}

	public void setrole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Login [shainid=" + shainid + ", password=" + password +  ", role=" + role + "]";
	}


}
