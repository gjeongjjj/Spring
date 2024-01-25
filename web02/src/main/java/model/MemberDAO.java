package model;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.MemberDTO;

// ** DAO (Data Access Object)
// => SQL 구문 처리
// => CRUD 구현
// Create(Insert), Read(selectList, selectOne), Update, Detete

// ** 첫번째 예제 DBStart 와 ~~~DAO 의 차이점
// => 결과를 직접 처리하지 않고 제공해야됨.
// => 즉, 메서드 역할별로 처리결과를 return 해야함. 
// => 그러므로 특히 select 결과를 잘 전달하기 위해 결과를 객체화해야함.

public class MemberDAO {
	// ** 전역변수 정의 
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst; 
	private static ResultSet rs;
	private static String sql;
	
	
	// ** selectList
	public List<MemberDTO> selectList() {
		sql = "select * from member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
				
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// => 결과의 존재여부
			// => 존재 : list 에 담기 
			// => 없음 : return null
			if (rs.next()) {
				do {
					MemberDTO dto = new MemberDTO();
//					=>  setter 사용
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					
					list.add(dto);
				} while (rs.next());
				return list;
				// 여기서 return있어야 selectList 빨간줄 없어짐. 
			} else {
				return null;
				
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
			
		}
	}
	// 컬렉션
	// List 순서를 유지. 순차적으로 처리. 중복 허용함. 
	// 		ArrayList = 배열 (연속된 공간 할당) ,순차처리 빠름 / LinkedList = 데이터 기차묶듯이. 
	// Map 순서 개념 없음. 키를 이용해서 담아넣는다. 키가 다르면 중복을 허용함.(value) // 키_유효성
	// Set 순서 없다. 중복 허용하지 않는다. 
	
	// ** selectOne   _Call By Value
	public MemberDTO selectOne(String id) {
		sql = "select * from member where id = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
				
				return dto;
			} else { 
				System.out.println("검색 결과가 없습니다.");
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception > " + e.toString());
			return null;
		}
	} // selectOne
	
	//============================
	
	// ** insert 
	// => 모든 컬럼 입력
	public int insert(MemberDTO dto) {
		sql = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception > " + e.toString());
			return 0;
		}
	} // insert
	
	// ** update
	// id(P.Key) 제외한 모든 컬럼 수정
	public int update(MemberDTO dto) {
		sql = "update member set password = ?, name = ?, age = ?, jno = ?, info = ?"
				+ ", point = ?, birthday = ?, rid = ? where id = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getPassword());
			pst.setString(2, dto.getName());
			pst.setInt(3, dto.getAge());
			pst.setInt(4, dto.getJno());
			pst.setString(5, dto.getInfo());
			pst.setDouble(6, dto.getPoint());
			pst.setString(7, dto.getBirthday());
			pst.setString(8, dto.getRid());
			pst.setString(9, dto.getId());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** update Exception > " + e.toString());
			return 0;
		}
	} // update
	
	// ** delete
	public int delete(String id ) {
		sql = "delete from member where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** delete Exception > " + e.toString());
			return 0;
		}
		
	} // delete
	

} // class