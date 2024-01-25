package jdbc02;

import java.util.List;

// ** Controller
// => 요청 : 요청분석 -> 담당 Service -> Service 는 DAO 
// => 결과 : DAO -> Service -> Controller
//                Service_완충지대
// => View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React) 

public class StudentController {
	
	// ** 전역변수
	StudentService service = new StudentService();
	// 서비스 호출
	
	// -------------------------
	
	// ** View 역할 메서드
	// => selectList 
	public void printList(List<StudentDTO> list) {
		// 출력할 내용을 매개변수로. 리스트 타입.
		System.out.println("** Student List **");
		// => 출력자료의 존재 확인
		if (list != null) {
			// ** List 출력
			for (StudentDTO s : list) {
				System.out.println(s);
			}
		} else { 
			System.out.println("** selectList 결과가 1건도 없음 **");
		}
	} // printList
	// ==================================================================== 경우 2
	// 컨트롤러가 요청. 다오가 실행.
	
	public void selectOne(StudentDTO sno) {
		System.out.println("** Student List **");
		
		System.out.println(sno);
		
	} // selectOne
	
	// ==================================================================== 경우 3

	public void insert(int n) {
		System.out.println("** Student List **");
		
		if (n > 0) {
			System.out.println("insert complete");
		} else {
			System.out.println("insert fail");
		}
	}
	
	public void update(int n) {
		System.out.println("** Student List **");
		
		if (n > 0) {
			System.out.println("Update complete");
		} else {
			System.out.println("Update fail");
		}
	}
	
	public void delete(int n) {
		System.out.println("** Student List **");
		
		if (n > 0) {
			System.out.println("Delete complete");
		} else {
			System.out.println("Delete fail");
		}
	}
	
	// 참조자료형 Test
//	StudentDTO dto2 = new StudentDTO();
//	dto2.setSno(18);
//	sc.
//	
	
	
	public static void main (String[] args) {
		
		StudentController sc = new StudentController();
		// 컨트롤러에 대한 인스턴스 하나 만든다. main이 static이기 때문에 인스턴스가 있어야만 쓸 수 있다. **
		
		// ** selectList
		// => 요청에 해당하는 Service의 메서드 호출.
		//    -> 처리결과를 View에게 전달해서 출력하도록 함.

//		sc.printList(sc.service.StuJo());
		
		// printList 전체 목록.
//		sc.printList(sc.service.selectList());
		
		// selectOne 하나만 뽑기. 
//		sc.selectOne(sc.service.selectOne(8));
		
		// insert 자료 넣기 
//		sc.insert(sc.service.insert(new StudentDTO("도라에몽", 20, 10, "대나무헬리콥터")));
		
		// update 
//		sc.update(sc.service.update(new StudentDTO("스폰지밥", "네모네모", 56)));
//		sc.update(sc.service.update(new StudentDTO("도라에몽", "대나무 헬리콜터", 56)));
//		sc.update(sc.service.update(new StudentDTO(3, "이지현")));
		
		// delete
//		sc.delete(sc.service.delete(new StudentDTO("도라에몽", 20)));
//		sc.delete(sc.service.delete(new StudentDTO("이지현", 18)));
		
		// Join Test
//		StudentDTO dto2 = new StudentDTO();
//		dto2.setSno(18);
//		sc.service.selectOne2(dto2);
//		sc.selectOne(dto2);
		
		// Transaction Test
		sc.service.TransactionTest();
		
		
	} // main
	
} // class
