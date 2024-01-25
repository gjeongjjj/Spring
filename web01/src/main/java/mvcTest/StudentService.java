package mvcTest;

import java.util.List;

// 컨트롤러와 DTO사이에서 처리를 담당하는 역할. 

// ** Service
// => Controller 요청에 해당하는 DAO의 메서드를 실행. 
// => Controller와 DAO의 중간에 위치하면서 ( 직접적으로 연결되지않고 )
//    이 둘의 의존성을 낮추어줌. ( 의존성 : 필요성 ) dependency
/*
 * 컨트롤러는 계속 요청읇 받아야함. 그래서 다른 코드의 영향을 받으면 불편함. 
 * 수정해야하는 상황이 자주 일어나는데 유저의 요청을 받는 컨트롤러랑 이어놓으면 힘들어. 
 * 의존성 : 의존성이 높으면 다른 클래스를 수정하면 영향을 받는 정도. 
 * 영향은 안받아야 좋은거임. 영향은 직접적ㅇ츠로 최대한 안받게 하기 위해서 service를 놓아야함. 
 * 서비스는 DAO에게 일을 시켜야한다. 
*/


public class StudentService {
	
	// ** 전역변수 정의
	StudentDAO dao = new StudentDAO();
	
	// join
	public List<StudentDTO> StuJo() {
		return dao.StuJo();
	}
	
	// ** selectList
	public List<StudentDTO> selectList() {
		return dao.selectList();
	}
	
	// ** selectOne
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	
	// ** selectOne2
	public void selectOne2(StudentDTO dto) {
		dao.selectOne2(dto);
		// void. 실행만 시켜줄 뿐.
	}
	
	// ** insert 
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}
	
	// ** update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}
	
	// ** delete
	public int delete(StudentDTO dto) {
		return dao.delete(dto);
	}
	
	public void TransactionTest() {
		dao.transactionTest();
	}
	
	
	
}
