package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Select() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String job = request.getParameter("job");
		String[] interest = request.getParameterValues("interest");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 출력객체
		out.print("<h2> ** Select Box ** </h2>");
		
		// => 선택여부를 확인하고 출력
		//   -> 선택하지 않아도 Parameter job은 존재, 그러나 Value는 없음.
		
		if (job == null || job == "") {
			out.print("<h2>되고싶은게 없니? </h2><br><br>");
		} else {
			out.print("<h2>당신이 원하는 직업은 " + job + "</h2><br><br>");
		} 
		
		if (interest == null) {
			out.print("<h2>관심분야 없니? </h2><br>");
			
		}else {
			out.print("<h2>디저트 관심은 이거군. => </h2>");
			
			for (String s : interest) {
				out.print(s + ", ");				
				
			}
			
		}
		
		// 방법 2
//		if (job != null && job.length() > 0) {
//			out.print("<h2>당신이 원하는 직업은 " + job + "</h2><br><br>");
//		} else {
//			out.print("<h2>되고싶은게 없니? </h2><br><br>");
//		}			
		
	} // doGet

} // class
