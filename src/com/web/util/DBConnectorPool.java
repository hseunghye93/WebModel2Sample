package com.web.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnectorPool {
	
	public static Connection getCon(){
		Connection con = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/myMariaDB");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void disCon(PreparedStatement st, Connection con){
		try {
			if(st != null){
				st.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void disCon(ResultSet rs, PreparedStatement st, Connection con){
		try {
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
