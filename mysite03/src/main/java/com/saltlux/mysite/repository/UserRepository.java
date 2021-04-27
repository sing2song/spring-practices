package com.saltlux.mysite.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mysite.exception.UserRepositoryException;
import com.saltlux.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;	
	

	public boolean insert(UserVo vo) {
		System.out.println("insert하기 전 no : "+vo.getNo());
		int count = sqlSession.insert("user.insert",vo);
		System.out.println("insert한 후의 no : "+vo.getNo());
		return count==1;
	}

	
	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.find",no);
	}

	public UserVo findByEmailAndPassword(UserVo vo) throws UserRepositoryException {
		return sqlSession.selectOne("user.findByEmailandPassword",vo);
	}
	
	public boolean update(UserVo vo){
		int count = sqlSession.update("user.update",vo);	
		return count==1;
	}


	public UserVo findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}


}
