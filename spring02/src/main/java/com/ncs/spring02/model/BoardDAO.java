package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

// ** 게시판
// => CRUD 구현

@Repository
public class BoardDAO {
	
	// ** 전역변수 정의 
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst; 
	private static ResultSet rs;
	private static String sql;
	
	// ** selectList
	public List<BoardDTO> selectList() {
		sql = "select * from board order by seq desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(7));
					dto.setIndent(rs.getInt(8));
					
					list.add(dto);
					
				} while (rs.next());
				return list;
			} else {
				System.out.println("** Board selectList => 출력 자료가 없습니다. ");
				return null;
			} // if
			
		} catch (Exception e) {
			System.out.println("** Board selectList => " + e.toString());
			return null;			
		
		} // try
		
	} // selectList
	
	// ** selectOne
	public BoardDTO selectOne(int seq) {
		sql = "select * from board where seq = ?";
		BoardDTO dto = new BoardDTO();
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, seq);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(7));
				dto.setIndent(rs.getInt(8));
			}
			return dto;
		} catch (Exception e) {
			System.out.println("** BoardSelectOne Exception => "+ e.toString());
			return null;
		}
	} // selectOne
	
	// ** insert
	public int insert(BoardDTO dto) {
		sql = "insert into board values ( "
				+ "	(select * from (select ifNull(max(seq),0)+1 from board) as temp), "
				+ "	?, ?, ?, Current_TimeStamp, 0, "
				+ "	(select * from (select ifNull(max(seq),0)+1 from board) as temp), 0, 0 );";
		
		try {
			pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** Board Exception => " + e.toString());
			return 0;
		}
	} // insert
	
	// ** update
	public int update(BoardDTO dto) {
		sql = "update board set title = ?, content=? where id = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setString(3, dto.getId());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** update Exception >" + e.toString());
			return 0;
		}
		
	}
	
	// ** delete
	public int delete(int seq) {
		sql = "delete from board where seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, seq);
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception > " + e.toString());
			return 0;
		}
	} // delete
	
	
}// class
