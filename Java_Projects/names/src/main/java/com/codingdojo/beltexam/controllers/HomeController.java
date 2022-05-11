package com.codingdojo.beltexam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.beltexam.models.Join;
import com.codingdojo.beltexam.models.Joy;
import com.codingdojo.beltexam.models.LoginUser;
import com.codingdojo.beltexam.models.User;
import com.codingdojo.beltexam.services.JoyService;
import com.codingdojo.beltexam.services.UserService;


@Controller
public class HomeController {

//	private final UserService userServ;
//	private final JoyService joyServ;
//	
//	public HomeController(UserService userServ, JoyService joyServ) {
//		super();
//		this.userServ = userServ;
//		this.joyServ = joyServ;
//	}
//	
	
	@Autowired 
	UserService userServ;
	@Autowired
	JoyService joyServ;
	
	/***************Login and reg*************
	 * ******************************************/
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	//need route to register
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
		
		userServ.register(newUser, result);
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("user_id", newUser.getId());
        session.setAttribute("user_name", newUser.getFirstName());
        return "redirect:/home";
    }

	//need route to login
	
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    	User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("user_id", user.getId());
        session.setAttribute("user_name", user.getFirstName());
        return "redirect:/home";
    }
	
	/***************Dashboard*************
	 * ******************************************/



		@GetMapping("/home")
		public String dashboard(Model model, HttpSession session) {
			if(session.getAttribute("user_id") != null) {
				
				Long id = (Long) session.getAttribute("user_id");
				
				User user = userServ.oneUser(id);
				
				session.setAttribute("user", user);
				
				model.addAttribute("allJoys", joyServ.allJoys());
				
				return "dashboard.jsp";
				
			} else {
				
				return "redirect:/";
			}
			
			}
	
		/***************Logout*************
		 * ******************************************/

				@GetMapping("/logout")
				public String logout(HttpSession session) {
					
					session.invalidate();
					return "redirect:/";
					
				}
	
		/********************************Join!!!*******************************************************/
			
				@PutMapping("/upvote/{joyid}/{userid}")
				public String creatingJoin(@PathVariable("joyid") Long joyid,@PathVariable("userid") Long id, HttpSession session) {
					

					User user = userServ.oneUser(id);
					
					Joy joy = joyServ.findOne(joyid);
					
					joy.getUsers().add(user);
					
					joyServ.update(joy, null);
				
					return "redirect:/home";
				}


		/**********************************************************************************************/
	
			
			/***************Create*************
			 * ******************************************/
				@GetMapping("/names/new")
				public String newJoy(@ModelAttribute("joy") Joy joy, BindingResult result, Model model, HttpSession session) {
			
				return "newJoy.jsp";
			
				}
			
				@PostMapping("makingJoy")
				public String makingJoy(@Valid @ModelAttribute("joy") Joy joy,
						BindingResult result, Model model, HttpSession session) {
					
					joyServ.create(joy, result);
					
				if(result.hasErrors()) {
					return "newJoy.jsp";
				}
				return "redirect:/home";
				}
				
		
			/***************See one*************
			 * ******************************************/



				@GetMapping("/names/{id}")
				public String oneJoy(@PathVariable("id") Long id, Model model, HttpSession session) {
					
					Long Uid = (Long) session.getAttribute("user_id");
					
					User user = userServ.oneUser(Uid);
					
					session.setAttribute("user", user);
					
					model.addAttribute("joy", joyServ.findOne(id));
					
					return "oneJoy.jsp";
				}
			
				
			/***************Delete*************************
			* ******************************************/
				
				@GetMapping("/delete/{id}")
				public String delete(@PathVariable("id") Long id) {
					
					joyServ.delete(id);
					return "redirect:/home";
					
				}
	
				
				/***************edit*************************
				 * ******************************************/
					
				@GetMapping("/names/{id}/edit")
				public String editJoy(@PathVariable("id") Long id,
						@ModelAttribute("joy") Joy joy, BindingResult result, Model model, HttpSession session) {
						
					model.addAttribute("joy", joyServ.findOne(id));
						
					return "editJoy.jsp";
				}
					
				@PutMapping("/editingJoy/{id}")
				public String editingJoy(@PathVariable("id") Long id,
						@Valid @ModelAttribute("joy") Joy joy,
						BindingResult result, Model  model, HttpSession session) {
						
					
					
					if(result.hasErrors()) {
						return "editJoy.jsp";
					}
					joyServ.update(joy, result);
					return "redirect:/home";
				}
	
	
}
