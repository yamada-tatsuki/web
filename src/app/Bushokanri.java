package app;

import java.io.Serializable;

/**
 * 社員情報を保持するクラス
 *
 * @author mano
 *
 */
public class Bushokanri implements Serializable {

	public Bushokanri() {
		super();
	}

	/** 部署ID */
	private String bushoid;

	/** 部署名 */
	private String bushoname;


	public String getBushoId() {
		return bushoid;
	}

	public void setBushoId(String bushoid) {
		this.bushoid = bushoid;
	}

	public String getBushoName() {
		return bushoname;
	}

	public void setBushoName(String bushoname) {
		this.bushoname = bushoname;
	}

	@Override
	public String toString() {
		return "Bushokanri [bushoid=" + bushoid + ", bushoname=" + bushoname +  "]";
	}

}
