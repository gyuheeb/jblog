package com.douzone.jblog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CatagoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	 @Autowired
	    private SqlSession sqlSession;


	public void insert(UserVo vo) {
		sqlSession.selectOne("blog.insert",vo);
	}


	public void updateprofile(BlogVo vo) {
		sqlSession.update("blog.updateprofile",vo);
	}


	public BlogVo findAll(String id) {
		 return sqlSession.selectOne("blog.findAll",id);
	}


	public List<CatagoryVo> categoryfind(String id) {
		
		List<CatagoryVo> list = sqlSession.selectList("catagory.categoryfind",id);
		return list;
	}


	public void catagoryinsert(CatagoryVo vo) {
		sqlSession.insert("catagory.catagoryinsert",vo);
	}


	public void delete(Long no) {
		sqlSession.delete("catagory.delete",no);
		
	}


	public void write(PostVo vo) {
		sqlSession.insert("post.write",vo);
	}


	public List<PostVo> findpost() {
		List<PostVo> list = sqlSession.selectList("post.findpost");
		return list;
	}





	public List<PostVo> findCatagorypost(Long no) {
		List<PostVo> list = sqlSession.selectList("post.findCatagorypost",no);
		return list;
	}


	public PostVo findpostNo(Long no2) {
		return sqlSession.selectOne("post.findpostNo",no2);
		
	}


	

}
