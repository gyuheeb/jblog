package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/{id:(?!assets).*}/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	// 블로그 메인 페이지 출력
	@RequestMapping({"/main","/main/category={cno}","/main/category={cno}/post={pno}"})
	public String main(@PathVariable("id") String id, @PathVariable("cno") Optional<Long> cno, 
			@PathVariable("pno") Optional<Long> pno ,Model model) {
			BlogVo blogvo = blogService.findAll(id);  // 프로필 구현

		if(pno.isPresent()) {
			
			List<CatagoryVo> catagorylist = blogService.categoryfind(id);//카테고리 구현
			Long no = cno.get();
			List<PostVo> postcatagorylist= blogService.findCatagorypost(no); //카테고리No에 따른 Post list출력
			Long no2 = pno.get();
			System.out.println(no2+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			PostVo vo =blogService.findpostNo(no2);
			
			System.out.println(vo);
			model.addAttribute("vo",vo);
			model.addAttribute("blogVo",blogvo);
			model.addAttribute("postcatagorylist",postcatagorylist);
			model.addAttribute("catagorylist",catagorylist);
			model.addAttribute("id",id);
			
			
			
			
		}else if(cno.isPresent()) {
			
			List<CatagoryVo> catagorylist = blogService.categoryfind(id);//카테고리 구현
			Long no = cno.get();
			List<PostVo> postcatagorylist= blogService.findCatagorypost(no); //카테고리No에 따른 Post list출력
			model.addAttribute("blogVo",blogvo);
			model.addAttribute("postcatagorylist",postcatagorylist);
			model.addAttribute("catagorylist",catagorylist);
			model.addAttribute("id",id);
			
			
			
		}else {
			List<CatagoryVo> catagorylist = blogService.categoryfind(id); //카테고리 구현
			List<PostVo> postlist= blogService.findpost(); //게시글 구현
	
			model.addAttribute("blogVo",blogvo);
			model.addAttribute("postlist",postlist);
			model.addAttribute("catagorylist",catagorylist);
			model.addAttribute("id",id);
			
		}
		
		return "blog/main";
	}

	
	// 관리페이지
	@RequestMapping(value="/admin-basic")
	public String basic(@PathVariable("id") String id, Model model) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
		model.addAttribute("id", id);
		return "blog/admin-basic";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
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
		return "redirect:/"+id+"/blog/admin-basic";
	}
	
	@RequestMapping(value="/admin-write",method=RequestMethod.GET)
	public String write(@PathVariable("id") String id,Model model) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
		List<CatagoryVo> list = blogService.categoryfind(id);
		model.addAttribute("list", list);
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin-write",method=RequestMethod.POST)
	public String write(@PathVariable("id") String id,Model model,PostVo vo) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
			blogService.wirte(vo);	
		return "redirect:/"+id+"/blog/admin-write";
	}
	
	@RequestMapping("/admin-category")
	public String category(@PathVariable("id") String id, Model model) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
		List<CatagoryVo> list = blogService.categoryfind(id);
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		return "blog/admin-category";
	}
	
	@RequestMapping(value="/admin-category", method=RequestMethod.POST)
	public String categoryinsert(@PathVariable("id") String id, Model model,CatagoryVo vo) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
		
		blogService.catagoryinsert(vo);
		return "redirect:/"+id+"/blog/admin-category";
	}
	
	@RequestMapping(value="/admin-category/delete={no}")
	public String delete(@PathVariable("id") String id,@PathVariable("no") Long no,Model model) {
		BlogVo blogvo = blogService.findAll(id);
		model.addAttribute("blogVo",blogvo);
		System.out.println(no);
		blogService.delete(no);
		
		return "redirect:/"+id+"/blog/admin-category";
	}
	
	
}
