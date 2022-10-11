package c_info2;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InfoModel {

	/*
	 * 사용자 입력값을 받아서 DB에 저장하는 역할 
	 */
	void insertInfo(InfoVO vo) throws SQLException;// end of insertInfo()

	/*
	 * 전체 Info_tab 의 레코드 검색
	 */
	ArrayList<InfoVO> selectAll() throws SQLException;

	/*
	 * 전화번호를 넘겨받아서 해당하는 사람의 정보를 검색
	 */
	InfoVO selectByTel(String tel) throws SQLException;
	
	/*
	 * 전화번호를 넘겨받아서 해당하는 사람의 정보를 삭제
	 */
	int delete(String tel) throws SQLException;
	
	/*
	 * 수정
	 */
	void modifyByAll(InfoVO vo) throws SQLException;// end of insertInfo()

	
}