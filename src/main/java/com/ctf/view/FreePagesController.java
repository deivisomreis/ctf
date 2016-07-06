package com.ctf.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreePagesController {

	@RequestMapping({"/home", "/"})
	public String home(Model model, HttpSession session){
		return "index";
	}
	
	
	
	@RequestMapping("/sobre")
	public String sobre(){
		return "sobre";
	}
	
}
