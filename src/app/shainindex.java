package app;

import java.io.Serializable;

/**
 * 社員情報を保持するクラス
 *
 * @author mano
 *
 */
public class shainindex implements Serializable {

	public shainindex() {
		super();
	}

	/** 社員ID */
	private String shainid;

	/** 名前 */
	private String shainname;


	public String getshainId() {
		return shainid;
	}

	public void setshainId(String shainid) {
		this.shainid = shainid;
	}

	public String getshainName() {
		return shainname;
	}

	public void setshainName(String shainname) {
		this.shainname = shainname;
	}

	@Override
	public String toString() {
		return "shainindex [shainid=" + shainid + ", shainname=" + shainname +  "]";
	}

}
