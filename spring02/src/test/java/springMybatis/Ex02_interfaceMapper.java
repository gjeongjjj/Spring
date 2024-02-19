package springMybatis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

import mapperInterface.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_interfaceMapper {
	
	// ** interface Mapper 설정
	// => Controller -> Service -> (DAO) -> interface Mapper : xml의 sql 구문을 이용해서 DB처리
	@Autowired
	MemberMapper mapper;
	// => 성공: MemberMapper mapper = new MemberMapper구현객체 ;
	//    -> 구현객체 생성 부터는 Spring과 Mybatis가 규칙에 의해 처리해줌 
	//    -> 규칙: 패키지 명과 클래스명을 interface , mapper xml, xml의 namespace 모두 동일하게 해줌.
	//           이를 위한 경로 설정 
	//           <mybatis-spring:scan base-package="mapperInterface"/>  
	
	@Autowired
	MemberDAO dao;
	
	@Autowired
	MemberDTO dto;
	
	
	// ** mapper 동작 Test
	public void mapperTest() {
		assertNotNull(mapper);
		System.out.println("** MeㄴmberMapper 구현 객체 => " +mapper.getClass().getName());
		System.out.println("** dto 인스턴스의 동작하는 클래스명 => " +dto.getClass().getName());
	}
	
	// ** mapper 의 메서드 Test
	// => Mybatis 사용시 주의사항
	//    -> 참조형 매개변수 사용시 매개변수 주소를 공유하지 않음 주의
	// 	     selectDTO(MemberDTO dto) 형식
	//    -> 매개변수를 하나만 사용 가능함. 
	//       그러므로 주로 객체형으로 사용하지만, 
	//       복수의 매개변수를 사용하려면 @Param을 이용할 수 있음. 
	//    -> xml 대신 @으로 Sql 구현 가능.
	
	// 1) selectOne
	
	public void selectOne() {
		String id = "jjang9";
		dto = mapper.selectOne(id);
		System.out.println("** selectOne => " +dto);
		assertNotNull(dto);
	}
	
	// 2) selectDTO(MemeberDTO dto ) 형식
	// => MemberDTO 와 Mybatis 비교
	//    참조형 매개변수 사용시 Mybatis는 매개변수 주소를 공유하지 않음 주의
	
	
	public void selectDTO() {
		dto.setId("tttt");
		
		// 2.1) MemberDAO 적용시
		MemberDTO dto1 = new MemberDTO();
		dto1.setId("tttt");
		dao.selectDTO(dto1);
		System.out.println("** MemberDAO selectDTO() => " +dto1);
		
		
		// 2.2) Mybatis 적용시
		MemberDTO dto2 = new MemberDTO();
		dto2.setId("tttt");
		// mapper.selectDTO(dto);
		dto = mapper.selectDTO(dto2);
		System.out.println("** Mybatis selectDTO() => " +dto2);
	}
	
	// 3) 복수의 매개변수 사용 test
	// => Mybatis 에서 2개 이상의 매개변수 처리
	// => Mapper interface 에서 @Param 적용가능
	// => selectParam(ID, Jno)
	@Test
	public void paramTest() {
		
		dto = mapper.selectParam("tttt", 7);
		System.out.println("** Mybatis @Param Test => " +dto);
		assertNotNull(dto);
	}
	
}
