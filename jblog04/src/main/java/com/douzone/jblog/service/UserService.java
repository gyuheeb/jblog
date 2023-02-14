package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CatagoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CatagoryRepository catagoryRepository;

	public void insert(UserVo vo) {
		userRepository.insert(vo);
		blogRepository.insert(vo);
		catagoryRepository.insert(vo);
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo.getId(),vo.getPassword());
	}

}
