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

@WebServlet("/api/hobby")
public class HobbyServlet extends HttpServlet {

	/********************************************************************************
	 * 以下のdoGet/doPostを実装して下さい。
	 * importなどは自由に行って下さい。
	 ********************************************************************************/

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
		// TODO 必須機能「趣味参照機能」
		String syainid = request.getParameter("syainid");


		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "wt2";
		String pass = "wt2";

		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection("jdbc:log4jdbc:oracle:thin:@localhost:1521:XE","wt2","wt2");

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(
				"select  \n" +
				"msh.SYAINID, \n" +
				"mh.HOBBY_ID, \n" +
				"mh.HOBBY_NAME, \n" +
				"mc.CATEGORY_ID, \n" +
				"mc.CATEGORY_NAME \n" +
				" \n" +
				"from \n" +
				"MS_SYAIN_HOBBY msh, \n" +
				"MS_HOBBY mh, \n" +
				"MS_CATEGORY mc \n" +
				" \n" +
				"where 1=1 \n" +
				"and SYANID = '"+ syainid + "' \n "+
				"and msh.HOBBY_ID = mh.HOBBY_ID \n" +
				"and mh.CATEGORY_ID = mc.CATEGORY_ID \n"
				);

		List<Hobby>hobbyList = new ArrayList<>();

			// SQL実行結果を保持している変数rsから商品情報を取得
			// rs.nextは取得した商品情報表に次の行があるとき、trueになります
			// 次の行がないときはfalseになります
			while(rs1.next()) {
				Hobby hobby = new Hobby();

				hobby.setHobby(rs1.getString("mh.HOBBY_NAME")); // Item型の変数itemに商品コードをセット
				hobby.setHobbyCategory(rs1.getString("mc.CATEGORY_NAME"));// Item型の変数itemに商品名をセット

				hobbyList.add(hobby);
			}

			// アクセスした人に応答するためのJSONを用意する
			PrintWriter pw = response.getWriter();

			// JSONで出力する
			pw.append(new ObjectMapper().writeValueAsString(hobbyList));

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}







	}
		// -- ここまで --

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO 任意機能「趣味投稿機能に挑戦する場合はこちらを利用して下さい」

		// -- ここまで --
	}

}
