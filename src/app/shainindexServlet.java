package app;

import java.io.IOException;
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

@WebServlet("/api/shainindex")
public class shainindexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// アクセス元のHTMLでｑに設定された値を取得して、String型の変数idに代入
		String s = request.getParameter("s");

		String sql = "select  \n" +
				"SYAIN_NO, \n" +
				"SYAIN_NAME \n" +
				"from TR_SYAIN \n" ;
			//	" select " + " BUSHO_ID , " +" BUSHO_NAME " + " from " + " TR_BUSHO ";

		List<shainindex> list = new ArrayList<>();

		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "app", "app");

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(sql);){


			while (rs1.next()) {
				shainindex shain = new shainindex();

				shain.setshainId(rs1.getString("SYAIN_NO"));
				shain.setshainName(rs1.getString("SYAIN_NAME"));

				list.add(shain);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		doGet(request ,response);
	}

}
