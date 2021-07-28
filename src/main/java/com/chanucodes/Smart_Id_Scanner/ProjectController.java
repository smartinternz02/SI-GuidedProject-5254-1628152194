package com.chanucodes.Smart_Id_Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	UserRepositry perUser;
	@Autowired
	ExtractComponent ec;
	
	@GetMapping("/")
	public String loginPage()
	{
		return "login";
	}
	
	@GetMapping("/Register")
	public String registerPage()
	{
		return "Register";
	}
	
	@PostMapping("/registersubmit")
	public String registerSubmit(User user)
	{
		perUser.save(user);
		return "login";
	}
	@GetMapping("/index")
	public String parameters(Model model) {
		model.addAttribute("directoryName", "C:/Users/DELL/Documents/chanu");

		return "index";
	}
	
	@PostMapping("/loginsubmit")
	public String loginSubmit(@RequestParam String email,@RequestParam String password)
	{
		User tempuser;
		tempuser=perUser.findByEmail(email);
		if(tempuser!=null && tempuser.getPassword().equals(password))
		{
			return "index";
		}
		else
		{
			return "failure";
		}
	}
	@PostMapping("/extractText")
	public String extractText(String directory, Model model) {
		model.addAttribute("directoryName", directory);
		model.addAttribute("extractedText", ec.extractFromPng(directory));

		return "index";
	}
}
