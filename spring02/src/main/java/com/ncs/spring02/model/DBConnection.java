package com.ncs.spring02.model;

import java.sql.Connection; 
import java.sql.DriverManager;
// java.sql로 고르기

public class DBConnection {
	
	// ** DB 연결
	//=> Connection 객체가 DB 연결및 연결정보를 관리함
	//   즉, Connection 객체를 생성해야함

	//** Connection 객체 생성
	// => 일반적인 생성문 
	//	    Connection cn = new Connection_구현클래스() -> XXX
	// => DB 연결정보를이용해서 생성후 그 생성값을 전달받음   

	//** Connection 생성과정
	// => Class.forName : JDBC 드라이버 로딩 (실질적인 드라이버 데려옴) 
	// => DriverManager
	//	     getConnection() 메서드로 해당 JDBC 드라이버를 찾아 필요한 기본값으로 컨넥션을 생성해서 제공

	// ** JDBC 드라이버
	// => DBMS와 통신을 담당하는 자바 클래스
	//	     DBMS 별로 알맞은 JDBC 드라이버 필요
	//	     보통 jar 파일로 제공

	// ** DriverManager
	// => JDK(Java SE Development Kit)의 정적 클래스이며,
	//	    사용할 애플리케이션에 대해 사용가능한 JDBC(Java Database Connectivity) 드라이버 세트를 관리함.
	
	public static Connection getConnection() {
		
		/* 
		 * CheckedException, UncheckException > try.catich로 해주기
		 * IO 
		 * Exception 안해주면 계속 기다림있을 수 있다. .
		 * 
		 * 각 DB들마다 드라이버를 찾고 계정 정보 넘겨주면 처리됨.
		 * 찾아주는 객체 > Class
		 * 소문자 class는 예약어임. 대문자로 시작하는 Class는 래퍼 클래스 이다. 
		 */
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 해당하는 클래스를 찾아서. 
			
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			// => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
			// => localhost -> 동일값(ip주소) @127.0.0.1
			// ( localhost : IP address / db주소를 찾는 주소 )
			// 파라미터와 파라미터 &로 구분. 위에는 파라미터 4개 전달.
			// allowPublicKeyRetrieval 페이지가 열리지 않아도 connection 할 수 있게. 
			
			Connection cn = DriverManager.getConnection(url, "root", "mysql"); 
			// root 계정 mysql 비번.
			
			System.out.println("** DB Connection 성공 **" );
			return cn;
			
		} catch (Exception e) {
			System.out.println("** DB Connection Exception => " + e.toString());
			return null;			
		} // try
		
		// 어떠한 db를 이용하던 이 try 문은 똑같다. com.mysql.cj.jdbc.Driver 이 부분만 다름. 
		
		
	} // getConnection
	
	
} //class