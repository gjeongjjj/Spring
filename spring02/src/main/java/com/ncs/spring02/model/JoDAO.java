
package com.ncs.spring02.model;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;

@Repository
public class JoDAO {
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst; 
	private static ResultSet rs;
	private static String sql;
	
	// joCaptain
	public List<JoDTO> joCaptain() {
		sql = "select j.jno, j.jname, j.captain, m.name, j.project, j.slogan "
				+ "from member m "
				+ "join jo j "
				+ "on j.captain = m.id";
		List<JoDTO> list = new ArrayList<JoDTO>();
		
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				do {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setCname(rs.getString(4));
					dto.setProject(rs.getString(5));
					dto.setSlogan(rs.getString(6));
					
					list.add(dto);
					
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** joCaptain Exception => " + e.toString());
			return null;
		}
	} // joCaptain

	
	
	// joList
	public List<JoDTO> joList() {
		sql = "select jno, jname from jo";
		List<JoDTO> list = new ArrayList<JoDTO>();
		
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				do {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
//					dto.setCaptain(rs.getString(3));
//					dto.setProject(rs.getString(4));
//					dto.setSlogan(rs.getString(5));
					
					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	} // joList
	
	// joDetail
	public JoDTO joDetail(int jno) {
		sql = "select * from jo where jno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				JoDTO dto = new JoDTO();
				dto.setJno(rs.getInt(1));
				dto.setJname(rs.getString(2));
				dto.setCaptain(rs.getString(3));
				dto.setProject(rs.getString(4));
				dto.setSlogan(rs.getString(5));
				
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	} // joDetail
	
	// insert
	public int insert(JoDTO dto) {
		sql = "insert into jo values (?, ?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getJname());
			pst.setString(3, dto.getCaptain());
			pst.setString(4, dto.getProject());
			pst.setString(5, dto.getSlogan());
			
			return pst.executeUpdate(); 
		} catch (Exception e) { 
			System.out.println("** insert Exception > " + e.toString());
			return 0;
		}
	} // insert
	
	// update
	public int update(JoDTO dto) {
		sql = "update jo set jname = ?, captain = ?, "
				+ "project = ?, slogan = ? where jno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getJname());
			pst.setString(2, dto.getCaptain());
			pst.setString(3, dto.getProject());
			pst.setString(4, dto.getSlogan());
			pst.setInt(5, dto.getJno());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** update Exception > " + e.toString());
			return 0;
		}
	} // update
	
	// ** delete
	public int delete(int jno ) {
		sql = "delete from jo where jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** delete Exception > " + e.toString());
			return 0;
		}
	} // delete
	
} //class
