package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C02_Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리
		// => id, password 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		// => id 확인 : Service 의 selectOne
		// => id 확인되면 password 일치하는지 확인. 
		// => 성공이면 id와 name을 세션에 보관하고 home으로 이동.
		//    실패이면 다시 loginForm으로 가서 메세지 출력, 재로그인 유도. 
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);
		
		// 3. View (Response) : Forward
		if (dto != null && dto.getPassword().equals(password)) {
//			request.getSession().setAttribute("id", id);
			HttpSession session = request.getSession();
			session.setAttribute("loginID", id);
			session.setAttribute("loginName", dto.getName());
//			id를 비교해서 일치하는 정보를 셀렉트 원을 dto에다가 저장 dto이름 가져와
			
			System.out.println("로그인 성공");
			response.sendRedirect("home.jsp");
			
		} else {
			System.out.println("로그인 실패");
			request.getRequestDispatcher("/member/loginForm.jsp").forward(request, response);
		}
		
	} // doPost

} //
