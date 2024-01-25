package Jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * ** 순서 
 * 1) JDBC API 에 정의된 필요한 객체들을 전역변수 정의
 * 2) CRUD 기능 메서드
 * 3) main 에서 사용
 */
 
 

public class DBStart {
	
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** StudentList
	// => MySql Command
	//    -> Login -> DB선택 -> sql 구문 실행 -> 결과
	// => JDBC 
	//    -> Connection 객체 생성 -> sql구문 실행 : Statement 또는 PreparedStatement 로.
	// 	  -> 결과 : ResultSet에 전달됨.
	// (Connection 객체 중심으로 이루어짐)
	
	// (편하게 하기 위해서 static으로)
	public static void selectList() {
		// 기본적으로 try, catch에 들어가야 함
		
		sql = "select * from student"; // 구문만. 세미콜론 안들어가도 됨. 
		
		try {
			// 모든 객체는 생성을 해야하지만 JDBC 애들은 Connection이 순차적으로, 단계적으로 생성해줌. 
			st = cn.createStatement();
			// A는 abstract 추상. 리턴이 A.
			rs = st.executeQuery(sql);
			// 실행. rs 안에는 (select * from student)가 온거임. 
			
			// ** 결과 출력
			// => 먼저 결과가 존재하는지 확인 (존재하지 않으면 없다라고 알려줘야 함)
			// => ResultSet 객체는 이를 위한 메서드 제공 
			// => next() : 다음에 Data가 존재하면 true, 현재Data를 제공. 
			
			System.out.println("                  ** Student **                         ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("| sno | name   | age  | jno  | info  | point  |");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			if (rs.next()) {
				// => selectList 결과 존재
				// resultSet에서 컬럼 단위로 꺼낼 수 있다. > do_while (일단 찍고 확인. / for랑 while은 확인하고 들어가므로)
				do {
					// 컬럼 인덱스 이용
					System.out.print(rs.getInt(1) + " ");
					// 컬럼라벨 이용
					System.out.print(rs.getString("name") + " ");
					
					System.out.print(rs.getInt(3) + " ");
					System.out.print(rs.getInt(4) + " ");
					System.out.print(rs.getString(5) + " ");
					System.out.print(rs.getDouble(6) + "\n");
					
					// 0번째는 무시하고. 1번째부터.
					// 오라클은 제일 앞에가 Rownum.
				} while (rs.next());
				
			} else {
				// => selectList 결과가 1건도 없음을 의미
				System.out.println("** selectList 결과가 1건도 없음 **");
			} // if_else
			
		} catch (Exception e) {
			// 오류가 났다면 여기로. 
			System.out.println("** selectList Exception => " + e.toString());

		} // try
		
	} // selectList
	
	// ** 조별 List 1 
	// => 매개변수로 조건문 만들기
	// => Statement 활용 : 매개변수를 활용한 조건문 추가
	public static void joListPS(int jno) {
		sql = " select * from student where jno = " + jno;
		// 주문. 
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			System.out.println("  ** joList => " + jno);
			if (rs.next()) {
				// next 데이터가 있다면
				do {
					// 컬럼 인덱스 이용
					System.out.print(rs.getInt(1) + " ");
					// 컬럼라벨 이용
					System.out.print(rs.getString("name") + " ");
					
					System.out.print(rs.getInt(3) + " ");
					System.out.print(rs.getInt(4) + " ");
					System.out.print(rs.getString(5) + " ");
					System.out.print(rs.getDouble(6) + "\n");
				} while (rs.next());
				
			} else {
				// next 데이터 없음
				// => 
				System.out.println("** selectList 결과가 1건도 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** joList Exceltion => " + e.toString());
			
		}
	} // joList
	
	// ** 조별 List 2
	// PreparedStatement 활용
	public static void joList(int jno) {
		sql = " select * from student where jno = ?";
		// 주문. 
		try {
//			st = cn.createStatement();
//			rs = st.executeQuery(sql);
			
			pst = cn.prepareStatement(sql);									
			// sql 문을 미리 정해줘야함. 
			// ?를 실행해야만 
			pst.setInt(1, jno);
			// 파라미터인덱스는 ?의 순서
			// 위 방법보다 더 쉽기 때문에 pst를 많이 쓴다.
			rs = pst.executeQuery(); 
			// sql을 전달하면 안됨. 
			
			System.out.println("  ** joList => " + jno);
			if (rs.next()) {
				// next 데이터가 있다면
				do {
					// 컬럼 인덱스 이용
					System.out.print(rs.getInt(1) + " ");
					// 컬럼라벨 이용
					System.out.print(rs.getString("name") + " ");
					
					System.out.print(rs.getInt(3) + " ");
					System.out.print(rs.getInt(4) + " ");
					System.out.print(rs.getString(5) + " ");
					System.out.print(rs.getDouble(6) + "\n");
				} while (rs.next());
				
			} else {
				// next 데이터 없음
				// => 
				System.out.println("** selectList 결과가 1건도 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** joList Exceltion => " + e.toString());
			
		}
	} // joList
	
	// ** insert 
	// => 입력에 필요한 컬럼을 모두 매개변수로 전달받아야 한다. 
	//    많으면 처리 불편. 처리하는 데이터를 하나의 객체로. ->객체화
	//    -> 엔티티(Table) -> Java Class 로 객체화
	//    -> DTO (Data Transform Object), VO, Entity(JPA)
	// => sql 구문을 만들기 위해서 문자열 연산을 작성해야함 
	//    insert into student ( name, age, jno, info) values ('홍길동', 22, 9, '관리자입니다.');
	//    "insert into student ( name, age, jno, info) values ('" 
	//                           + name +"', "age.....
	// => 이 점을 보완하기 위해서 제공된 객체가 PreparedStatement
	//    변수의 위치에 ?(바인딩 변수)를 사용할 수 있음. 
	//    insert into student ( name, age, jno, info) values (?, ?, ?, ?);
	//    ? 에 대응값은 JavaCode 로 처리 (PreparedStatement 제공 메서드)
	
	public static void insert(String name, int age, int jno, String info) {
		sql = "insert into student ( name, age, jno, info) values (?, ?, ?, ?);";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
			
			// executeUpdate : dml. 성공했는지를 알 수 있음. 몇 개 성공 알수 있는거 아님. ??????????? 
			// insert, update, delete > 실행된 개수를 리턴함. 
			//---------------
//			int cnt = pst.executeUpdate();
//			if (cnt == 1) System.out.println("** insert 성공 => " + cnt);
//			else System.out.println("** insert 실패 => " + cnt);
			//--------------- 위에 세문장을 아래 두 문장으로 줄일 수 있음.
			
			if (pst.executeUpdate() > 0) System.out.println("** insert 성공 => ");
			else System.out.println("** insert 실패 => ");
			//---------------
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			
		} // try_catch
	}

	public static void main(String[] args) {
		// 1) Connection 확인
		// => toString() 은 생략 가능
		//    즉, 출력문에서 인스턴스명을 사용하면 toString()을 호출하는 것임. 
//		System.out.println("** Connection 확인 => " + cn.toString());
		System.out.println("** Connection 확인 => " + cn);
		
		// 2) Student List
//		selectList();
		
		// 3) 조별 List 출력
		joList(3); 
		// 하기위해 메서드를 만든다.
		
		// 혼자 insert해봄. *****
//		insert("이지현", 33, 3, "코린이");
		
	} // main

} // class



// ** Error Message
// => 드라이버 오류 : java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver1
// => portNO 오류 : Communications link failure
// => DBName 오류 : java.sql.SQLSyntaxErrorException: Unknown database 'mydb1'
// => 계정,PW 오류 : java.sql.SQLException: Access denied for user 'root1'@'localhost' (using password: YES)
