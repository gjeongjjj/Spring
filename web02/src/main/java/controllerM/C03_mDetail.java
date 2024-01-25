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

@WebServlet("/mdetail")
public class C03_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C03_mDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => id 처리, session 에서 getAttribute
		String id = (String)request.getSession().getAttribute("loginID");
		String uri = "member/memberDetail.jsp"; // 성공시
		
		// => Update 요청시에는 uri값을 updateForm.jsp로 변경. 
		// parameter의 값으로 update를 알 수 있다. 
//		request.getParameter("jCode").equals("U");
		if ("U".equals(request.getParameter("jCode"))) {
			uri = "/member/updateForm.jsp";
		}
				
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		// => 성공 실패 따질 필요 없음. 걍 detail로 가면 됨. 
		// => 결과를 view가 출력하도록 Attribute에 저장
		MemberService service = new MemberService();
		request.setAttribute("apple", service.selectOne(id));
		System.out.println(service.selectOne(id));
		
		// 3. View (Response) : Forward
		
		request.getRequestDispatcher(uri).forward(request, response);
		
	} // doPost

} //
