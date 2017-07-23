package co.almaccenture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorMain {
	
	@RequestMapping(value="/")
	public String irVenta(){
		return "/main";
	}
	
}
