package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/deletebushokanri")
public class DeleteBushokanriServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// アクセス元のHTMLでｑに設定された値を取得して、String型の変数idに代入
		String d = request.getParameter("d");

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "app";
		String pass = "app";

		String sql = " delete from TR_BUSHO " + " where 1=1 " + " and BUSHO_ID = '" + d + "'  \n " ;

		System.out.println(sql);

		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "app", "app");

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();
				){
			// SQLの命令文を実行し、その件数をint型のresultCountに代入します
			int resultCount = stmt.executeUpdate(sql);
			} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細:[%s] ", e.getMessage()), e);
			}

			// アクセスした人に応答するためのJSONを用意する
			PrintWriter pw = response.getWriter();

			// JSONで出力する
			pw.append(new ObjectMapper().writeValueAsString("削除しました"));

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		doPost(request ,response);
	}

}
