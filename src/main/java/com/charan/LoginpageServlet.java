package com.charan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LoginpageServlet
 */
//@WebServlet("/LoginpageServlet")
public class LoginpageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginpageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/charan","root",
					"Naniking41412=$");
			Statement st = conn.createStatement();
			String query = "select * from user where userid='"+ userId + "' and password='" + password + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				out.print("<h1>" + userId + " :Welcome to home page</h1><br>");
				out.print("<h1>Login Successfull</h1><br>");
			} else {
				out.print("<h1>" + userId + " :please enter correct username and password</h1><br>");
				out.print("<h1>Login Failed...!</h1><br>");
			}
			rs.close();
			st.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			out.print("<h1>Login Failed...! due to server Exception</h1><br>");
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("<h1>Login Failed...! due to server Exception</h1><br>");
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
