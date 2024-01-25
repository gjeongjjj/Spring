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

@WebServlet("/mjoin")
public class C04_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C04_mJoin() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리
		// => 성공 : 로그인 유도 (loginForm.jsp)
		// => 실패 : 재가입 유도 (joinForm.jsp)
		request.setCharacterEncoding("UTF-8");
		
		String uri = "member/loginForm.jsp"; // 성공시
		
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Double.parseDouble(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));
		
		
		// 2. 서비스 처리 
		// => Service, DTO 객체 생성
		// DTO 위에서 해버림. 
		MemberService service = new MemberService();
		// 0보다 크면 성공
		// 0이거나 마이너스면 실패
		if (service.insert(dto) > 0) {
			request.setAttribute("message", " (｡･∀･)ﾉﾞ 회원가입 되었습니다. 로그인 해보셔요 >0<");
			uri = "/member/loginForm.jsp";
			// 성공
		} else {
			// 실패
			request.setAttribute("message", " (′д｀ ) 회원가입 실패.. 다시 하세요. ");
			uri = "/member/joinForm.jsp";
		}
		
		// 3. View (Response)
		request.getRequestDispatcher(uri).forward(request, response);
		
		
		
		
	} // doPost
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


} //
