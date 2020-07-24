package cl.duoc.yuyitos.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("admin")
@Controller
public class AdminController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String panel(Model model) {
		return "admin/panel";
	}
}
