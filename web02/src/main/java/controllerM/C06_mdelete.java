package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/mdelete")
public class C06_mdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C06_mdelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberService service = new MemberService();
		
		
		if (service.delete((String)request.getSession().getAttribute("loginID")) > 0) {
			request.setAttribute("message", "탈퇴 성공, 1개월 후 재가입 가능합니다. ");
			
			request.getSession().invalidate();
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "탈퇴 실패, 관리자에게 문의하셈. ");
			System.out.println("실패다 이놈아~~~");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
