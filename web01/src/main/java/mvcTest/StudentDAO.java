package mvcTest;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// ** DAO (Data Access Object)
// => SQL 구문 처리
// => CRUD 구현
// Create(Insert), Read(selectList, selectOne), Update, Detete

// ** 첫번째 예제 DBStart 와 ~~~DAO 의 차이점
// => 결과를 직접 처리하지 않고 제공해야됨.
// => 즉, 메서드 역할별로 처리결과를 return 해야함. 
// => 그러므로 특히 select 결과를 잘 전달하기 위해 결과를 객체화해야함.

public class StudentDAO {
	// ** 전역변수 정의 
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst; 
	private static ResultSet rs;
	private static String sql;
	
	// 240109 과제 
	// ** Join Test
	// => sno, name, age, jno, jname, project, 조장이름 출력하기
	// => JoDTO 작성, joinList() 메서드 작성(Controller, Service, DAO)
	
	//-==========================================================================================	
	// Jo랑 합성
	public List<StudentDTO> StuJo() {
		sql = "select s1.sno, s1.name, s1.age, s1.jno, jname, project, captain,"
				+ "(select name from student where sno = captain) cname " 
				+ "from student s1 left outer join jo j on s1.jno = j.jno";
		// 띄어쓰기 주의***** ( . )콤마 주의. 
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// => 결과의 존재여부
			// => 존재 : list 에 담기 
			// => 없음 : return null
			if (rs.next()) {
				do {
					StudentDTO dto = new StudentDTO();
					// dto 객체 생성을 밖에 하면 안됨. 덮어씌워서 마지막 것만 계속 출력됨. 
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setJname(rs.getString(5));
					dto.setProject(rs.getString(6));
					dto.setCaptain(rs.getInt(7));
					dto.setCname(rs.getString(8));
					// 얘네만 있는 생성자가 없기 때문에 하나하나 써줘야함.
					
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

	
	//-==========================================================================================
	
	// ** selectList
	public List<StudentDTO> selectList() {
		sql = "select * from student";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		// List 는 인터페이스. 조상. new List 불가능. 인터페이스는 생성자가 없음.
		// 오른쪽 ArrayList 는 후손. 후손에 의해서 만들어지는 개념.
		// -- 후손 아무나 올 수 있음. 
		
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// => 결과의 존재여부
			// => 존재 : list 에 담기 
			// => 없음 : return null
			if (rs.next()) {
				do {
//					StudentDTO dto = new StudentDTO();
					// dto 객체 생성을 밖에 하면 안됨. 덮어씌워서 마지막 것만 계속 출력됨. 
//					dto.setSno(rs.getInt(1));
//					dto.setName(rs.getString(2));
//					dto.setAge(rs.getInt(3));
//					dto.setJno(rs.getInt(4));
//					dto.setInfo(rs.getString(5));
//					dto.setPoint(rs.getDouble(6));
					
					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), 
							rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
					
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
	public StudentDTO selectOne(int sno) {
		// 몇 번 학생 나와라! int sno
		
		sql = "select * from student where sno = ?";
		StudentDTO list = new StudentDTO();
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				do {
					list = new StudentDTO(rs.getInt(1), rs.getString(2), 
							rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
					
				} while (rs.next());
				
			} else { 
				System.out.println("검색 결과가 없습니다.");
				return null;
			}
			
		} catch (Exception e) {
			System.out.println("** selectOne Exception > " + e.toString());
			return null;
		}
		return list;
				
	} // selectOne
	
	//============================
	
	// ** selectOne 비교 테스트
	// => 참조자료형 매개변수 Test( Call By Reference)
	//    (비교 : 기본자료형 매개변수 _Call By Value)
	public void selectOne2(StudentDTO dto) {
		sql = "select * from student where sno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSno());
			rs = pst.executeQuery();
			
			if (rs.next()) {
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setInfo(rs.getString(5));
				dto.setPoint(rs.getDouble(6));
				
			} else { 
				System.out.println("** Student 없음 **");
			}
			
		} catch (Exception e) {
			System.out.println("** selectOne2 Exception > " + e.toString());
		}
				
	} // selectOne2 
	
	//============================
	
	// ** insert 
	public int insert(StudentDTO dto) {
		sql = "insert into student (name, age, jno, info) values (?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			
			System.out.println(dto);
			
			return pst.executeUpdate();
						
		} catch (Exception e) {
			
			System.out.println("** insert Exception > " + e.toString());
			return 0;
		}
		
	} // insert
	
	// ** update
	public int update(StudentDTO dto) {
		sql = "update Student set jno = ? where name = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getName());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception > " + e.toString());
			return 0;
			
		}
		
	} // update
	
	// ** delete
	public int delete(StudentDTO dto) {
		sql = "delete from student where name = ? and sno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getSno());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** delete Exception > " + e.toString());
			return 0;
		}
		
	} // delete
	

	// ** Transaction Test
	// => Connection 객체가 관리
	// => 기본값은 AutoCommit  true 임.
	// => setAutoCommit(false) -> commit 또는 rollback 
	// 		(setAutoCommit(false) : command모드의 start Transaction이랑 같다. )
	// => Test 사항
	//   - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

	// 1) Transaction 적용전
	// => 동일자료를 2번 입력
	//   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
	//   - Rollback 불가능
	//   - MySql Command 로 1번째 입력 확인 가능 
   
	// 2) Transaction 적용후 
	// => 동일자료를 2번 입력 
	//   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	//   - Rollback 가능 -> 둘다 취소됨
	
	public void transactionTest() {
		sql = "insert into student values(20, '김길동', 99, 9, 'Transaction 적용전', 123.45)";
		
		// 1) Transaction 적용전
//		try {
//			pst = cn.prepareStatement(sql);
//			pst.executeUpdate(); // 1번째는 테이블에 입력 완료
//			pst.executeUpdate(); // 2번째는 p.key 중복오류 발생. -> catch블록으로 넘어감. 예외 발생.
//			
//		} catch (Exception e) {
//			System.out.println("** Transaction 적용전 => " + e.toString());
//		}
		// =====================================================================
		
		// 2) Transaction 적용후 
		try {
			cn.setAutoCommit(false); // Start Transaction 이랑 같다.  
			pst = cn.prepareStatement(sql);
			pst.executeUpdate(); // 1번째는 입력 완료 되었지만 Buffer에 보관.
			pst.executeUpdate(); // 2번째는 p.key 중복오류 발생. -> catch블록으로 -> Rollback;
			cn.commit();
			
		} catch (Exception e) {
			System.out.println("** Transaction 적용후 => " + e.toString());
			// => Rollback;
			try {
				cn.rollback();
				System.out.println("** Rollback 성공 ** ");
			} catch (Exception e2) {
				System.out.println("** Transaction 적용전 => " + e.toString());
			}
		}

		
		
		
	} // transactionTest
	
	
	
	
} // class
