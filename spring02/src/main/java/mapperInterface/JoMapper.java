package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoMapper {
	
	// joCaptain
	List<JoDTO> joCaptain();

	// joList
	List<JoDTO> joList();

	// joDetail
	JoDTO joDetail(int jno);

	// joInsert
	int joInsert(JoDTO dto);

	// joUpdate
	int joUpdate(JoDTO dto);

	// joDelete
	int joDelete(int jno);

	
}
