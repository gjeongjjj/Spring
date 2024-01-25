package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** 톰켓10 사용시 주의사항
//=> Java 8 까지 지원
//=> 문제가 많아 실무에서 잘 안쓰임
// => javax.servlet을 지원안함.
//    그러므로 javax -> jakarta 로 변경
//=> 실습: 톰켓9

//** Servlet 계층도
//=> Object -> interface : Servlet, ServletConfig, java.io.Serializable 
//       -> GenericServlet (A) -> HttpServlet (A) 

//public abstract class HttpServlet extends GenericServlet {...}
//public abstract class GenericServlet implements Servlet, ServletConfig,
//                               java.io.Serializable {...}

//** Servlet 의 실행방식
//=> 클라이언트의 요청에 자동반응하는 이벤트 드리븐 방식으로 작동
//=> 요청방식 이 Get -> doGet , Post -> doPost

//** url 매핑 네임
//=> url 배열 형태로 복수 선언가능
//=> 그러나 프로젝트 전체 기준 중복되면 안됨 : server Start 안됨. 
//=> 숫자, 한글 사용시 오류는 없으나 비추
//=> xml 로 설정가능 (web.xml 설정화일)
//=> @ , xml 모두 사용가능하지만 매핑네임 중복은 허용하지않음  


 @WebServlet("/getpost")
// @WebServlet(urlPatterns = {"/hello", "/안녕", "/123", "/7seven", "/seven7"})

public class Ex03_GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Ex03_GetPost() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) request 의 Parameter 처리
		// 	=> 한글처리, getParameter 전에 해야함

		//   - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함 
		//   ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
		//     단, post 방식에서는 반드시 처리해야함 ) 
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		// => name 이 id인 input Tag 의 value값을 return
		String name = request.getParameter("name");
		
		
		//  => 해당하는 Parameter가 없을 때
		// 	 - null 을 리턴
		// => Parameter 은 존재하지만 값이 없는 경우 : null 이 아님
		
		
		String password = request.getParameter("password");
		if (password != null && password.length()>0) {
			System.out.println("** password=> " +password.toUpperCase());
		} else {
			System.out.println("** password is null");
		}
		
//		System.out.println("** password => " + password);
		
		
		
		
		
//		response.getWriter().append("Served at: ")
//							.append(request.getContextPath())
//							.append("여기는 doGet 메서드 입니다. ");
		
		// ** 출력문 (response 객체에 html 문서를 담아줌)
		// => 출력객체 생성 -> html 문서작성
		// => 한글처리 해야함 (출력객체 생성전에 해야함)
		response.setContentType("text/html; charset=UTF-8");
		// => 웹브라우져에게 처리할 데이터의 MIME 타입을 알려줌
		// => MIME : Multipurpose Internet Mail Extensions
		// => 데이터 송.수신시 자료의 형식에 대한 정보 
		// => Jsp 의 page 디렉티브의 contentType 속성값과 동일
		
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h2 style = 'color : blue;'>** Get/Post Test **</h2>");
		out.print("<h3> => 전달된 Parameter 확인</h3>");
		out.print("<h3> => id : " + id +"</h3>");
		out.print("<h3> => Name : " + name + "</h3>");
		out.print("</html></body>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//		response.getWriter().append("Served at: ")
//							.append(request.getContextPath())
//							.append("여기는 doPost 메서드 입니다. ");
		
		doGet(request, response);


	}

} // class
