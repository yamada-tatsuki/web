
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ItemSearchServlet
 */
@WebServlet("/api/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html;charset=UTF-8");
	HttpSession session = request.getSession(true);

	String id = request.getParameter("id");
	String passcode = request.getParameter("pass");
	PrintWriter pw = response.getWriter();

	// JDBCドライバの準備
			try {

			    // JDBCドライバのロード
			    Class.forName("oracle.jdbc.driver.OracleDriver");

			} catch (ClassNotFoundException e) {
			    // ドライバが設定されていない場合はエラーになります
			    throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
			}

			// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
			String dbUser = "app";
			String dbPass = "app";

			String sql = "select " +
					"SYAIN_NO, " +
					"PASSWORD, " +
					"ROLE " +
					"from TR_PASS " +
					"where 1=1 " +
					"and SYAIN_NO = '" + id +"' ";


			try (
					// データベースへ接続します
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "app", "app");

					// SQLの命令文を実行するための準備をおこないます
					Statement stmt = con.createStatement();

					// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
					ResultSet rs1 = stmt.executeQuery(sql);){


				if(rs1.next()) {
					Login user = new Login();

					user.setshainId(rs1.getString("SYAIN_NO"));
					user.setpassword(rs1.getString("PASSWORD"));
					user.setrole(rs1.getString("ROLE"));

					 if(passcode.equals(user.getpassword())){
							session.setAttribute("login", "ok");//キーとバリューを保管
							session.setAttribute("shainId", user.getshainId());
							session.setAttribute("role", user.getrole());
							pw.append(new ObjectMapper().writeValueAsString("ログイン完了。"));
							}else{
								pw.append(new ObjectMapper().writeValueAsString("ログインできませんでした。"));
							}
				}
}
		 catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	//doGet(request ,response);

	}
}
