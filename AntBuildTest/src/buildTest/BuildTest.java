package buildTest;

import com.ncs.green.domain.UserDTO;
import com.ncs.green.service.DTOService;

public class BuildTest {
	
	public static void main(String[] args) {
		// 1) UserDTO 생성
		UserDTO dto = new UserDTO();
		dto.setId("bear");
		dto.setName("곰은베어");
		dto.setLoginTime("2023/02/22 AM 10:04:04");
		
		
		// 2) 직접 출력
		System.out.println("** 직접 출력 => " + dto);
		
		
		// 3) DTOService 로 출력 
		DTOService service = new DTOService();
		service.setUserDTO(dto);
		System.out.println("** AntBuild Test ** ");
		System.out.println("** DTOService => " + service.getUserDTO());
		
	} //main

	
}
