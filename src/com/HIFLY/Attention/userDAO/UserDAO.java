package com.HIFLY.Attention.userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.HIFLY.Attention.client.User;
import com.HIFLY.Attention.debuger.Debuger;

public class UserDAO {
	private Connection connection = null;
	private Statement st = null;
	public UserDAO() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/attention", "root", "q1w2e3");
			st = connection.createStatement();

			String sql;
			sql = "select * FROM user;";

			ResultSet rs = st.executeQuery(sql);
			String val[] = new String[4];
			while (rs.next()) {
				System.out.println(rs.getFetchSize() + "!!");
				val[0] = rs.getString(1);//id
				val[1] = rs.getString(2);//name
				val[2] = rs.getString(3);//uuid
				val[3] = rs.getString(4);//tel
				for(int i=0; i<val.length; i++) {
					System.out.print(val[i] + " ");
				}
				System.out.println();

			}

			rs.close();
			st.close();
			connection.close();
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException se2) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public void putUser(User user) {
		String sqlStatement = "insert user(name, uuid, tel) values ? ? ?";
		try {
			if(st.executeUpdate(sqlStatement)>0){
				System.out.println("Insert Success! " + user.toString());
			}
		} catch (SQLException e) {
			Debuger.printError(e);
		}
		
	}

	public static void main(String[] args) {
		new UserDAO();
	}
}
