package servlet01;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_MVC01List() {
        super();
    }
    
    // ** MVC 패턴1 StudentList 출력하기
    // => 요청 Service 처리
    // => 결과 출력
    
//    Ex02_MVC01List list = new Ex02_MVC01List();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// => 요청 Service 처리
		// => 결과 출력
		StudentService service = new StudentService();
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		// => 결과 출력 : 출력 내용을 Response 객체의 Body 영역에 Write
		// - 한글처리
		// - 출력객체 생성 & 출력
		response.setContentType("text/html; charset=UTF-8"); // 한글로
		PrintWriter out = response.getWriter(); // 출력객체
		list = service.selectList();
		
		out.print("<html><body>");
		out.print("<h2 style = 'color : blue;'>** Student List **</h2>");
		// 테이블 만들기
		out.print("<table border = 1><tr>"
				+ "<th>SNO</th>"
				+ "<th>NAME</th>"
				+ "<th>AGE</th>"
				+ "<th>JNO</th>"
				+ "<th>POINT</th>"
				+ "<th>INFO</th>");
		
		if (list != null) {
			for (StudentDTO s : list) {
//				out.print(s + "<br>"); // 한줄씩 출력
				out.print("<tr><td>" + s.getSno() + "</td>");
				out.print("<td>" + s.getName() + "</td>");
				out.print("<td>" + s.getAge() + "</td>");
				out.print("<td>" + s.getJno() + "</td>");
				out.print("<td>" + s.getPoint() + "</td>");
				out.print("<td>" + s.getInfo() + "</td></tr>");
			}
		} else {
			out.print("<h2>데이터 없음</h2>");
		}
		
		out.print("</table></body></html>");
		
		// 개난잡
		out.print("<br>");
		
		out.print("<tr>");
		
		
		for (int i = 0; i < 20; i++) {
			out.print("<td>");
			out.print(list.get(i));
			out.print("</td>");
		}
		out.print("</tr>");
		
		out.print("</html></body>");
		
		
		
		
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class 
