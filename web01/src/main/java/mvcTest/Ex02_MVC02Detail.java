package mvcTest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/detail")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_MVC02Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentService service = new StudentService();
		
		HttpSession session = request.getSession();
		int mysno = (int)session.getAttribute("loginID");
		StudentDTO listOne = service.selectOne(mysno);
		
		request.setAttribute("myInfo", listOne);
		
		request.getRequestDispatcher("mvcTestJsp/ex03_MVC02Detail.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
