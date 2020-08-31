package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.web.dto.LoginDto;
import com.web.util.DBConnectorPool;

public class ActionLoginDao {

	public LoginDto actionLogin(LoginDto dto){
		Connection con = DBConnectorPool.getCon();
		String sql = "select id, name from member where id = ? and pw = ?";
		
		PreparedStatement st = null;
		ResultSet rs = null;
		LoginDto result = null;		
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, dto.getId());
			st.setString(2, dto.getPassword());
			rs = st.executeQuery();
			
			if(rs.next()){
				result = new LoginDto();
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectorPool.disCon(rs, st, con);
		}
		
		return result;
	}
	
}
