package spms.dao;

// MemberDao 인터페이스 정의 
import java.util.HashMap;
import java.util.List;

import spms.vo.Member;

public interface MemberDao {
  List<Member> selectList(HashMap<String, Object> paramMap) throws Exception;
  int insert(Member member) throws Exception;
  int delete(int no) throws Exception;
  Member selectOne(int no) throws Exception;
  int update(Member member) throws Exception;
  Member exist(Member member) throws Exception;
}
