package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/main/id={id}")
	public String main(@PathVariable("id") String id ,Model model) {
		model.addAttribute("id",id);
		return "blog/main";
	}
	@RequestMapping("/admin-basic")
	public String basic() {
		return "blog/admin-basic";
	}
	@RequestMapping("/admin-write")
	public String write() {
		return "blog/admin-write";
	}
	@RequestMapping("/admin-category")
	public String category() {
		return "blog/admin-category";
	}

}
