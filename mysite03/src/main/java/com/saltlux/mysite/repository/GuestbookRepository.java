package com.saltlux.mysite.repository;



import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mysite.vo.Guestbook02Vo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public List<Guestbook02Vo> findAll(){		
		return sqlSession.selectList("guestbook.findAll");		
	}
	
	public boolean insert(Guestbook02Vo vo) {
		int count = sqlSession.insert("guestbook.insert",vo);
		return count==1;
	}
	
	public boolean delete(Guestbook02Vo vo) {		
		int count = sqlSession.delete("guestbook.delete",vo);
		return count==1;
	}
}
