package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CatagoryVo;
import com.douzone.jblog.vo.PostVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value="/main/id={id}", method=RequestMethod.GET)
	public String main(@PathVariable("id") String id ,Model model) {
		BlogVo blogvo = blogService.findAll(id);
		List<PostVo> postlist= blogService.findpost();
		List<CatagoryVo> catagorylist = blogService.categoryfind(id);
		model.addAttribute("blogVo",blogvo);
		model.addAttribute("postlist",postlist);
		model.addAttribute("catagorylist",catagorylist);
		model.addAttribute("id",id);
		return "blog/main";
	}
	//
	@RequestMapping(value="/main/id={id}/title={title}", method=RequestMethod.GET)
	public String mainprint(@PathVariable("id") String id
			,@PathVariable("title") String title ,Model model) {
		List<PostVo> postlist= blogService.findpost();
		System.out.println("postlist"+ postlist);
		model.addAttribute("postlist",postlist);
		model.addAttribute("id",id);
		model.addAttribute("title",title);
		return "blog/main/id="+id +"/title="+title;
	}
	
	@RequestMapping(value="/main/id={id}/title={title}", method=RequestMethod.POST)
	public String main(@PathVariable("id") String id
			,@PathVariable("title") String title ,Model model) {
		List<PostVo> postlist= blogService.findpost();
		System.out.println("postlist"+ postlist);
		model.addAttribute("postlist",postlist);
		model.addAttribute("id",id);
		model.addAttribute("title",title);
		return "redirect:/blog/main/id="+id +"/title="+title;
	}
	
	
	@RequestMapping(value="/admin-basic/id={id}")
	public String basic(@PathVariable("id") String id, Model model) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
		model.addAttribute("id", id);
		return "blog/admin-basic";
	}
	
	@RequestMapping(value="/upload/id={id}", method=RequestMethod.POST)
	public String basic(@PathVariable("id") String id,BlogVo vo,@RequestParam("file") MultipartFile file) {
		String profile = fileuploadService.restore(file);
	
		vo.setId(id);
		
		System.out.println(vo);
		if(profile != null) {
			vo.setProfile(profile);
		}
		blogService.updateprofile(vo);
		System.out.println("after"+vo);
		servletContext.setAttribute("blogVo", vo);
		return "redirect:/blog/admin-basic/id="+id ;
	}
	
	@RequestMapping(value="/admin-write/id={id}",method=RequestMethod.GET)
	public String write(@PathVariable("id") String id,Model model) {
		List<CatagoryVo> list = blogService.categoryfind(id);
		model.addAttribute("list", list);
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin-write/id={id}",method=RequestMethod.POST)
	public String write(@PathVariable("id") String id,Model model,PostVo vo) {
			blogService.wirte(vo);	
		return "redirect:/blog/admin-write/id="+id;
	}
	
	@RequestMapping("/admin-category/id={id}")
	public String category(@PathVariable("id") String id, Model model) {
		List<CatagoryVo> list = blogService.categoryfind(id);
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		return "blog/admin-category";
	}
	
	@RequestMapping(value="/admin-category/id={id}", method=RequestMethod.POST)
	public String categoryinsert(@PathVariable("id") String id, Model model,CatagoryVo vo) {
		blogService.catagoryinsert(vo);
		
		return "redirect:/blog/admin-category/id="+id;
	}
	
	@RequestMapping(value="/admin-category/id={id}/delete={no}")
	public String delete(@PathVariable("id") String id,@PathVariable("no") Long no) {
		
		blogService.delete(no);
		
		return "redirect:/blog/admin-category/id="+id;
	}
	
	
}
