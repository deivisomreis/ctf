package com.ctf.view;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctf.dao.AdminDAO;
import com.ctf.dao.UserDAO;
import com.ctf.model.Admin;
import com.ctf.model.User;
import com.ctf.sessioncontrol.AdminAware;

@Controller
public class AdminController {
	
	public AdminController() {
		adminDAO = new AdminDAO();
		userDAO = new UserDAO();
	}
	private static AdminDAO adminDAO;
	private static UserDAO userDAO;
	
	@RequestMapping("/adminlogin")
	public String login(String admin, String password, Model model, HttpSession session) {
		if(AdminAware.isAdmin(session.getAttribute("user"))){
			session.setAttribute("user", session.getAttribute("user"));
			return "admin/home";
		}
		
		else if(admin == null && password == null)
			return "adminlogin";
		
		else{
			Admin a = adminDAO.login(admin, password);
			
			if(a != null){
				session.setAttribute("user", a);
				model.addAttribute("user", a);
				return "admin/home";
			}
			else{
				model.addAttribute("erro", "Favor verificar os dados informados!");
				return "adminlogin";
			}
		}
	}
	
	@RequestMapping("/admin/home")
	public String home(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		return "admin/home";
	}
	
	@RequestMapping("/admin/show")
	public String show(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		
		return "admin/show";
	}
	
	@RequestMapping("/admin/edit")
	public String edit(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		
		return "admin/edit";
	}
	
	@RequestMapping("/admin/update")
	public String update(Admin admin, Model model, HttpSession session){
		if(admin != null && admin.getId() != null && admin.getId() > 0){
			adminDAO.update(admin);
			session.setAttribute("user", adminDAO.admin(admin.getId()));
			model.addAttribute("sucesso", "Dados atualizados com sucesso!");
			
			return show(model, session);
		}
		
		else{
			model.addAttribute("erro", "Erro ao atualizar os dados! Tente novamente.");
			return edit(model, session);
		}
	}

	@RequestMapping("/admin/exit")
	public String exit(HttpSession session, Model model){
		session.invalidate();
		
		return "adminlogin";
	}

	@RequestMapping("/admin/user/register")
	public String userRegister(User user, HttpSession session, Model model, String cadastrar){		
		model.addAttribute("user", session.getAttribute("user"));
		
		if(cadastrar == null)
			return "admin/user/new";
		
		else {
			if(cadastrar.equals("cadastrar") && user != null && !user.getName().isEmpty() && !user.getCpf().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()){
				Date dateRegister = new Date();
				user.setRegistered(dateRegister); // data de registro!
				userDAO.insert(user);
				model.addAttribute("sucesso", user.getName() + " cadastrado com sucesso!");

				return "admin/user/new"; 
			}
			else{
				model.addAttribute("erro", "favor verificar os dados e tente novamente");
				return "admin/user/new";
			}
		}				
	}
	
	@RequestMapping("/admin/user/list")
	public String userList(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("usuarios", userDAO.users());
		
		return "admin/user/list";
	}
	
	@RequestMapping("/admin/user/remove")
	public String userRemove(Integer id, Model model, HttpSession session) {
		if(id != null && id > 0){
			userDAO.remove(id);
			model.addAttribute("sucesso", "usuario removido com sucesso!");
			return userList(model, session);
		}
		else{
			model.addAttribute("erro", "erro ao remover usuario");
			return userList(model, session);
		}
	}
	
	@RequestMapping("/admin/user/edit")
	public String userEdit(Integer id, Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		
		if(id != null && id > 0){
			model.addAttribute("usuario", userDAO.user(id));
			return "admin/user/edit";
		}
		
		else{
			model.addAttribute("erro", "erro ao editar o usuario, tente novamente!");
			return userList(model, session);
		}
	}

	@RequestMapping("/admin/user/update")
	public String update(Model model, HttpSession session, User user) {
		if(user != null && !user.getCpf().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()){
			User userB = userDAO.user(user.getId());
			
			userB.setCpf(user.getCpf());
			userB.setEmail(user.getEmail());
			userB.setName(user.getName());
			userB.setPassword(user.getPassword());
			
			userDAO.update(userB);
			
			model.addAttribute("sucesso", "usuario atualizado!");
			
			return userList(model, session);
		}
		else{
			model.addAttribute("erro", "erro ao atualizar usuario");
			return userList(model, session);
		}
	}
}
