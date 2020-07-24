package cl.duoc.yuyitos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	
	@RequestMapping(value={"", "/login"}, method = RequestMethod.GET)
	public String index(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "logout", required = false) String logout ) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth.getPrincipal() instanceof UserDetails) {
	        return "redirect:/pedidos/";
	    } 
		
		if (error != null) {
			System.out.println("Login correcto");
		}
		
		if (logout != null) {
			System.out.println("Logout correcto");
		}
		
		return "login";
	}
	
	/*@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
          
        return "redirect:/login?logout";
    }*/
}
