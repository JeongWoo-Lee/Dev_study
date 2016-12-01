package spms.dao;

// 애노테이션 적용 
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionfactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<Member> selectList(HashMap<String, Object> paramMap)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.MemberDao.selectList",
					paramMap);
		} finally {
			sqlSession.close();
		}
	}

	public int insert(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.MemberDao.insert", member);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("spms.dao.MemberDao.delete", no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	public Member selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}

	public int update(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Member original = sqlSession.selectOne(
					"spms.dao.MemberDao.selectOne", member.getNo());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if (!member.getEmail().equals(original.getEmail())) {
				paramMap.put("email", member.getEmail());
			}
			if (!member.getName().equals(original.getName())) {
				paramMap.put("name", member.getName());
			}

			if (paramMap.size() > 0) {
				paramMap.put("no", member.getNo());
				int count = sqlSession.update("spms.dao.MemberDao.update",
						paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
		} finally {
			sqlSession.close();
		}
	}

	public Member exist(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Member original = sqlSession.selectOne(
					"spms.dao.MemberDao.selectOne", member.getNo());
			if(original.getName().equals(member.getName()) 
					&& original.getPassword().equals(member.getPassword())){
				return member;
			}
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
