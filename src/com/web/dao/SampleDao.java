package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.web.dto.SampleDto;
import com.web.util.DBConnectorPool;

public class SampleDao {
	
	private int perPage = 10;
	
	public ArrayList<SampleDto> sampleList(int curPage){
		Connection con = DBConnectorPool.getCon();
		int startNum = (curPage-1)*perPage+1;
		int lastNum = curPage*perPage;
		String sql ="select * from "
				+ "(select @rownum:=@rownum+1 R, A.* from "
				+ "(select * from sampleboard order by regDate desc) A, (SELECT @rownum:=0) tmp) B"
				+ " where R between ? and ?";
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SampleDto> ar = new ArrayList<>();
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, startNum);
			st.setInt(2, lastNum);
			rs = st.executeQuery();
			while (rs.next()) {
				SampleDto dto = new SampleDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setCounts(rs.getInt("counts"));
				dto.setRegDate(rs.getString("regDate"));
				
				ar.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectorPool.disCon(rs, st, con);
		}
		return ar;
	}
	
	// 페이징 처리
	public HashMap<String, Integer> samplePage(int curPage){
		int totalList=getTotalList(); //전체 리스트 
		int totalPage=0;
		if(totalList%perPage==0){
			totalPage= totalList/perPage;
		}else {
			totalPage= totalList/perPage+1;
		}
		int perBlock=10;//블럭당 페이지수
		int curblock=0;//curPage 속해있는 블럭
		if(curPage%perBlock==0){
			curblock = curPage/perBlock;
		}else {
			curblock = curPage/perBlock+1;
		}
		int start=(curblock-1)*perBlock+1;
		int last=curblock*perBlock;
		
		int totalBolck=0;
		if(totalPage%perBlock==0){
			totalBolck= totalPage/perBlock;
		}else {
			totalBolck= totalPage/perBlock+1;
		}
		
		if(curblock==totalBolck){
			last=totalPage;
		}
		HashMap<String, Integer> hs = new HashMap<>();
		hs.put("start", start);
		hs.put("last", last);
		hs.put("curBlock", curblock);
		hs.put("totalBlock", totalBolck);
		hs.put("curPage", curPage);
		return hs;
	}
	
	// 전체리스트수 가져오기
	private int getTotalList(){
		Connection con = DBConnectorPool.getCon();
		String sql = "select count(num) from sampleboard";
		PreparedStatement st = null;
		ResultSet rs = null;
		int result=0;
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectorPool.disCon(rs, st, con);
		}
		return result;
	}
}
