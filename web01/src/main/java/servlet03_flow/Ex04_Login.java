package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => 한글, request 의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		
		int sno = 0;
		if (request.getParameter("sno") != null && request.getParameter("sno").length()>0) {
			sno = Integer.parseInt(request.getParameter("sno"));
		}
		// integer 형변환은 nullpointException 이 나올 수 있다. 그러니 처음부터 int로 선언.
		
//		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String uri = "home.jsp"; // index.html
		
		// 2. Service 처리
		// => Service, DTO 의 인스턴스 
		// => Service 의 selectOne : sno 확인 
		//    확인결과에 따라서 성공-> name 확인 
		// => 성공 : index.html 처음으로.
		// => 실패 : LoginForm.jsp 다시 로그인폼으로. 재로그인 유도
		
		StudentService service = new StudentService();
		StudentDTO dto = service.selectOne(sno);
		
		if(dto != null && dto.getName().equals(name)) {
			request.getSession().setAttribute("loginName", name);
//			request.getSession().setAttribute("loginID", sno);
			// 풀어서 쓰면
			HttpSession session = request.getSession();
			session.setAttribute("loginID", sno);
			
			
			System.out.println("로그인 성공");
			System.out.println("로그인 student => "+ dto);
			response.sendRedirect(uri);
		} else {
			System.out.println("로그인 실패");
			request.setAttribute("message", "로그인 실패. 다시하세요.");
			uri ="servletTestForm/flowEx04_LoginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
			
		}
		// forward / redirect 
		// get / post 
		
		
		
		
//		if ((list.getName()).equals(name)) {
//			response.sendRedirect(uri);
//		} else {
//			uri ="/web01/servletTestForm/flowEx04_LoginForm.jsp";
//			response.sendRedirect(uri);
//		} 하나씩 비교해라. 
		
		// 비밀번호 암호화때문에 직접비교 불가능. 
//		if (list.getSno() == Integer.parseInt(sno)) {
//			if ((list.getName()).equals(name)) {
//				response.sendRedirect(uri);
//				System.out.println("로그인 성공");
//			} else {
//				uri ="/web01/servletTestForm/flowEx04_LoginForm.jsp";
//				response.sendRedirect(uri);				
//			}
//		} else {
//			uri ="/web01/servletTestForm/flowEx04_LoginForm.jsp";
//			response.sendRedirect(uri);
//		}
				
		// 3. View (Response) : forward
		
		
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
