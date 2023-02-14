package com.douzone.jblog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CatagoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	
	public void updateprofile(BlogVo vo) {
		blogRepository.updateprofile(vo);
		
	}


	public BlogVo findAll(String id) {
		return blogRepository.findAll(id);
	}


	public List<CatagoryVo> categoryfind(String id) {
		
		return blogRepository.categoryfind(id);
	}

	public void catagoryinsert(CatagoryVo vo) {
		blogRepository.catagoryinsert(vo);
	}


	public void delete(Long no) {
		blogRepository.delete(no);
		
	}


	public void wirte(PostVo vo) {
		blogRepository.write(vo);
		
	}


	public List<PostVo> findpost() {
		return blogRepository.findpost();
	}


	public List<PostVo> findCatagorypost(Long no){
		return blogRepository.findCatagorypost(no);
	}


	public PostVo findpostNo(Long no2) {
		return blogRepository.findpostNo(no2);
		
	}



	






}
