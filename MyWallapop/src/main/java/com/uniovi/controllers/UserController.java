package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddUserFormValidator;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UserController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private AddUserFormValidator addUserFormValidator;

	@RequestMapping("/user/list")
	public String getListado(Model model, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		System.out.println(user.getId());
		model.addAttribute("usersList", usersService.findUsersMinusPrincipal(user));
		return "user/list";
	}

	@RequestMapping(value = "/user/add")
	public String getUser(Model model) {
		model.addAttribute("rolesList", rolesService.getRoles());
		model.addAttribute("user", new User());
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@Validated @ModelAttribute User user, BindingResult result, Model model) {
		addUserFormValidator.validate(user, result);
		if (result.hasErrors()) {
			model.addAttribute("rolesList", rolesService.getRoles());
			return "/user/add";
		}
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(name = "checkbox_ids", required = false) List<Long> ids, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		usersService.deleteUser(ids, user);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
		user.setId(id);
		usersService.addUser(user);
		return "redirect:/user/details/" + id;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated @ModelAttribute("user") User user, Model model, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		model.addAttribute("user", user);
		return "home";
	}

}
