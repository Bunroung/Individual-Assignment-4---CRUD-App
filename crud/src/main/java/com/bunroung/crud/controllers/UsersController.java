package com.bunroung.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bunroung.crud.models.UserCreate;
import com.bunroung.crud.models.Users;
import com.bunroung.crud.rep.UsersRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersRepository repo;
	
	@GetMapping({"" , "/"})
	public String showUserList(Model model) {
		List<Users> users = repo.findAll();
		model.addAttribute("users", users);
		return "users/index";
	}
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		UserCreate userCreate = new UserCreate();
		model.addAttribute("userCreate", userCreate);
		return "users/Create";
	}
	@PostMapping("/create")
	public String createUser(@Valid @ModelAttribute UserCreate userCreate, BindingResult result) {
		
		if (result.hasErrors()) {
			return"users/Create";
		}
		
		Users users = new Users();
		users.setName(userCreate.getName());
		users.setEmail(userCreate.getEmail());
		users.setPassword(userCreate.getPassword());
		users.setNumber(userCreate.getNumber());

		repo.save(users);
		
		return "redirect:/users";
	}
	@GetMapping("/Edit")
	public String edit(
		Model model,
		@RequestParam int id){
		
		try {
			Users users = repo.findById(id).get();
			model.addAttribute("users", users);
			
			UserCreate userCreate = new UserCreate();
			userCreate.setName(users.getName());
			userCreate.setEmail(users.getEmail());
			userCreate.setPassword(users.getPassword());
			userCreate.setNumber(users.getNumber());
			
			model.addAttribute("userCreate", userCreate);
			
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/users";
		}
			
			return "users/Edit";
		}
	@PostMapping("/Edit")
	public String updateUser(
			Model model, 
			@RequestParam int id, @Valid @ModelAttribute UserCreate userCreate, BindingResult result) {
		
		try {
			Users users = repo.findById(id).get();
			model.addAttribute("users", users);
			
			if (result.hasErrors()) {
				return"users/Edit";
			}
			
			users.setName(userCreate.getName());
			users.setEmail(userCreate.getEmail());
			users.setPassword(userCreate.getPassword());
			users.setNumber(userCreate.getNumber());

			repo.save(users);
			
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/users";
		}
		
		return "redirect:/users";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam int id) {
		
		try {
			Users users = repo.findById(id).get();
			
			repo.delete(users);
			
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/users";
		}
		
		
		return "redirect:/users";
	}


	}


