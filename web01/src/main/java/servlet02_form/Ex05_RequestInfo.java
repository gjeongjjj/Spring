package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reqinfo")
public class Ex05_RequestInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex05_RequestInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ** 화면 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		out.print("<h2> ** Request Information ** </h2>");
		out.print("<h3> => 주요 메서드 </h3>");
		out.print("<h3> 1) Request Header Names & Values</h3>");
		out.print("<h3> 2) ContextPath: 웹애플리케이션의 최상위 경로 </h3>");
		out.print("<h3> 3) RealPath: 웹애플리케이션의 실행위치</h3>");
		out.print("<h3> 4) 기타등등 </h3>");
		out.print("<h3> => Console 창에서 확인하세요 ~~~</h3>");
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기</a></h2><br>");
				
		// ** Console 출력
		System.out.println("** 1) Request Header Names & Values **");
		
		Enumeration<String> hNames = request.getHeaderNames();
		// headernames를 이용해서 하나씩 꺼내서 출력. 
		// while문 이용해서 데이터 없을 때까지.
		while (hNames.hasMoreElements()) {
			// hNames 데이터가 있다는 뜻.
			String hName = hNames.nextElement();
			// 값을 전달받고 넘어감.
			System.out.printf("%s = %s \n", hName, request.getHeader(hName));
			
		} // while
		
		System.out.println("** 2) ContextPath => "+request.getContextPath());
		System.out.println("** 3) RealPath => "+request.getRealPath("/")); 
		// 더이상 지원하지 않겠다. 사용이 안되는 것은 아님. 
		System.out.println("** 4) 기타등등 **");
		System.out.println("=> RemoteAddress: "+request.getRemoteAddr());
		System.out.println("=> Method: "+request.getMethod());
		System.out.println("=> RequestURL: "+request.getRequestURL());
		// 프로젝트를 실행시켜준 full 주소
		System.out.println("=> RequestURI: "+request.getRequestURI());
		// 어플리케이션에 해당하는 주소 URL보다 넓은 의미.
	    System.out.println("=> ServerName: "+request.getServerName());
	    System.out.println("=> ServerPort: "+request.getServerPort());
	    System.out.println("=> ServletPath: "+request.getServletPath());
		
	} // doGet


}
