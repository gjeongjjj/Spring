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

@WebServlet("/mupdate")
public class C05_mupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C05_mupdate() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request의 한글(post요청시 필수) & Parameter 처리
		// => 성공 : mdetail로 (memberDetail.jsp)
		// => 실패 : 재수정 유도 (updateForm.jsp)
		// => 출력객체가 필요함. (apple. 필요함. )
		//		1. redirect 또는 전단된 값들을 apple 에 저장. 
		
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = "member/memberDetail.jsp"; // 성공시
		
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
		
		
		// 결과 출력을 위해 전달된 값들을 apple에 보관. 
		request.setAttribute("apple", dto);
		
		
		// 2. 서비스 처리 
		// => Service, DTO 객체 생성
		// DTO 위에서 해버림. 
		MemberService service = new MemberService();
		// 0보다 크면 성공
		// 0이거나 마이너스면 실패
		if (service.update(dto) > 0) {
			// 성공 : session에 보관한 Name 수정
			request.getSession().setAttribute("loginName", dto.getName());
			request.setAttribute("message", " (｡･∀･)ﾉﾞ 회원정보 수정 성공 >0<");
			uri = "member/memberDetail.jsp";
		} else {
			// 실패
			request.setAttribute("message", " (′д｀ ) 회원정보 수정 실패. 다시 하세요. ");
			uri = "member/updateForm.jsp";
		}
		
		// 3. View (Response)
		request.getRequestDispatcher(uri).forward(request, response);
		
		
		
		
	} // doPost
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


} //
