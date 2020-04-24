package app;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/bushokanri")
public class BushokanriServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		// アクセス元のHTMLでｑに設定された値を取得して、String型の変数idに代入
		String id = request.getParameter("q");

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定します
		// ※SQLのログを出力するため変数urlの値は基本的な形式から少し変更を加えています。
		// 　そのためシステム構築2で使い回すときは注意下さい！
		String url = "jdbc:log4jdbc:oracle:thin:@localhost:1521:XE";
		String user = "app";
		String pass = "app";

		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "app", "app");

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(
						"select  \n" +
								"BUSHO_ID \n" +
								",BUSHO_NAME \n" +
								" \n" +
								"from  \n" +
								"TR_SYAIN , \n" +
								"TR_BUSHO  \n" +
								" \n" +
								"where 1=1 \n" +
								"and BUSHO_ID = BUSHO_ID \n" +
								"order by \n" +
								"BUSHO_ID \n");
				){

			List<Bushokanri> list = new ArrayList<>();

			while (rs1.next()) {
				Bushokanri busho = new Bushokanri();

				busho.setBushoId(rs1.getString("BUSHO_ID"));
				busho.setBushoName(rs1.getString("BUSHO_NAME"));

				list.add(busho);
			}

			// アクセスした人に応答するためのJSONを用意する
			PrintWriter pw = response.getWriter();

			// JSONで出力する
			pw.append(new ObjectMapper().writeValueAsString(list));

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		doGet(request ,response);
	}

}
