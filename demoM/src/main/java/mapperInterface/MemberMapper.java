package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.MemberDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface MemberMapper {
	
	
	// ** JUnit Test
	// => selectDTO
	@Select("select * from member where id = #{id}")
	MemberDTO selectDTO(MemberDTO dto);
	// selectParam
	@Select("select * from member where id = #{ii} AND jno = #{jno}")
	MemberDTO selectParam(@Param("ii") String id, @Param("jno") int jno);
	
	// ** Member Check_List
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
	
	// ** mPageList : Member search Paging
	public List<MemberDTO> mSearchList(SearchCriteria cri);
	public int mSearchRowsCount(SearchCriteria cri);
	
	// ** Member_Paging
	public List<MemberDTO> mPageList(Criteria cri);
	public int totalRowsCount(Criteria cri);
	
	// ** selectList
	List<MemberDTO> selectList();

	// selectJoList
	List<MemberDTO> selectJoList(int jno);

	// ** selectOne
	MemberDTO selectOne(String id);

	// ** insert
	int insert(MemberDTO dto);

	// ** update
	int update(MemberDTO dto);
	
	// ** passwordUpdate
	int pwUpdate(MemberDTO dto);

	// ** delete
	int delete(String id);
	
	
}
