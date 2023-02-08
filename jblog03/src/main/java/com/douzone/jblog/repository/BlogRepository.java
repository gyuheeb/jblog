package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	 @Autowired
	    private SqlSession sqlSession;


	public void insert(UserVo vo) {
		sqlSession.selectOne("blog.insert",vo);
	}

}
