package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adder")
public class Ex01_Adder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex01_Adder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		//   => request 처리 : 한글, Parameter
		
//		request.setCharacterEncoding("text/html; charset=UTF-8"); // 한글로
//		String num1 = request.getParameter("num1");
		int N1 = Integer.parseInt(request.getParameter("num1"));
//		String num2 = request.getParameter("num2");
		int N2 = Integer.parseInt(request.getParameter("num2"));
		
//		int add = N1 + N2;
		
		
		// 2) Service & 결과 처리 
		//   => response 한글처리 , 출력객체 생성 & response 에 담기 
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 출력객체
		
//		out.print(add);
		out.printf("<h3>=> %d + %d = %d</h3>", N1, N2, N1+N2);
		
		
		
	} // doGet


} // class
