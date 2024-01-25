package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex03_Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		//   => request 처리 : 한글, Parameter
		request.setCharacterEncoding("UTF-8");
		
		//   => 하나의 name에 여러 values
		//      배열로 처리.
		
		String[] gift = request.getParameterValues("gift");		
		
		// 2) Service & 결과 처리 
		//   => response 한글처리 , 출력객체 생성 & response 에 담기 
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 출력객체
		out.print("<h2> ** Check Box ** </h2>");
		
		
		// 방법 1
		if (gift == null) {
			out.print("<h2>원하는게 없나요? </h2>");
						
		} else {
			for (int i = 0; i < gift.length; i++) {
				out.print("<h2> 당신이 원하는 것은 " + gift[i] + "</h2>");
				
			}			
		}
		
		// 방법 2
//		if (gift != null && gift.length > 0) {
//			for (String s : gift) {
//				out.print("<h2> 당신이 원하는 것은 " + s + "</h2>");
//			}
//		} else {
//			out.print("<h2>원하는게 없나요? </h2>");
//		}
		
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기</a></h2><br>");
		
		
	} // doGet


} // class
