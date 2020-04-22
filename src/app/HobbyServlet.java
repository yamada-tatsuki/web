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

		// -- ここまで --

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO 任意機能「趣味投稿機能に挑戦する場合はこちらを利用して下さい」

		// -- ここまで --
	}

}
