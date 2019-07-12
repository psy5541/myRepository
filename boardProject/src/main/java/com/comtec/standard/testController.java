package com.comtec.standard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {
	@RequestMapping(value="/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="world") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
