package com.ctf.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctf.dao.AgendaDAO;
import com.ctf.dao.FinancialControlDAO;
import com.ctf.dao.TasksDAO;
import com.ctf.dao.UserDAO;
import com.ctf.model.Agenda;
import com.ctf.model.FinancialControl;
import com.ctf.model.Tasks;
import com.ctf.model.User;
import com.ctf.sessioncontrol.UserAware;

@Controller
public class UserController {
	
	public UserController() {
		userDAO = new UserDAO();
		tasksDAO = new TasksDAO();
		agendaDAO = new AgendaDAO();
		fcDAO = new FinancialControlDAO();
	}
	
	private static UserDAO userDAO;
	private static TasksDAO tasksDAO;
	private static AgendaDAO agendaDAO;
	private static FinancialControlDAO  fcDAO;
	
	@RequestMapping("/userlogin")
	public String login(String user, String password, Model model, HttpSession session) {
		if(UserAware.isUser(session.getAttribute("user"))){
			model.addAttribute("user", session.getAttribute("user"));
			return "user/home";
		}
		
		else if(user != null && password != null && !password.isEmpty() && !user.isEmpty()){
			User userObj = userDAO.login(user, password);
			
			if(userObj != null){
				session.setAttribute("user", userObj);
				return "user/home";
			}
			else{
				model.addAttribute("erro", "Favor verificar os dados");
				return "userlogin";
			}
		}
		
		else
			return "userlogin";
	}
	
	@RequestMapping("/user/exit")
	public String exit(HttpSession session){
		session.invalidate();
		
		return "userlogin";
	}
	
	@RequestMapping("/user/home")
	public String  home(HttpSession session, Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		return "user/home";
	}
	
	@RequestMapping("/user/edit")
	public String edit(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));		
		return "user/edit";
	}
	
	@RequestMapping("/user/show")
	public String show(Model model, HttpSession session){
		model.addAttribute("user", session.getAttribute("user"));
		return "user/show";
	}
	
	@RequestMapping("/user/update")
	public String update(User user, Model model, HttpSession session, String editar){
		if(editar != null && editar.equals("editar") && user != null){
			userDAO.update(user);
			
			session.setAttribute("user", userDAO.user(user.getId()));
			
			model.addAttribute("sucesso", "Usuário editado com sucesso!");
			
			return show(model, session);
		}
		else{
			model.addAttribute("erro", "favor verificar os dados e tente novamente!");
			return show(model, session);
		}
	}
	
	@RequestMapping("/user/tasks/register")
	public String newTask(Model model, HttpSession session, Tasks tasks, String cadastrar){
		if(cadastrar == null)
			return "user/tasks/new";
		
		else if(cadastrar.equals("cadastrar") && tasks != null){
			tasks.setUser((User) session.getAttribute("user"));
			tasksDAO.insert(tasks);
			
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("sucesso", "Tarefa cadastrada com sucesso!");			
			return "user/tasks/new";
		}
		
		else{
			model.addAttribute("erro", "Favor verificar os dados!");
			model.addAttribute("user", session.getAttribute("user"));
			
			return "user/tasks/new";
		}
	}
	
	@RequestMapping("/user/tasks/edit")
	public String editTask(Integer id, Model model, HttpSession session) {
		return	"user/tasks/edit";
	}
	
	@RequestMapping("/user/tasks/remove")
	public String removeTasks(Integer id, Model model, HttpSession session) {
		if(id != null && id > 0){
			tasksDAO.remove(id);
			
			return listTasks(model, session);
		}
		else{
			model.addAttribute("erro", "Erro ao remover tarefa!");
			return listTasks(model, session);
		}
	}
	
	@RequestMapping("/user/tasks/list")
	public String listTasks(Model model, HttpSession session){
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("tasks", tasksDAO.tasks((User) session.getAttribute("user")));
		
		return "user/tasks/list";
	}
	
	@RequestMapping("user/tasks/finalize_task")
	public String finalizeTasks(Integer id, HttpSession session, Model model){
		return listTasks(model, session);
	}
	
	@RequestMapping("/user/tasks/show")
	public String  showTasks(Model model, HttpSession session, Integer id) {
		return "user/tasks/show";
	}
	
	@RequestMapping("/user/tasks/update")
	public String updateTasks(Model model, HttpSession session, Integer id){
		return listTasks(model, session);
	}
	
	public String  newAgenda(Agenda agenda, Model model, HttpSession session) {
		return "user/agenda/new";
	}
	
	public String editAgenda(Integer id, Model model, HttpSession session) {
		return "user/agenda/edit";
	}
	
	public String removeAgenda(Integer id, Model model, HttpSession session){
		return listAgenda(model, session);
	}
	
	public String listAgenda(Model model, HttpSession session){
		return "user/agenda/list";
	}
	
	public String updateAgenda(Agenda agenda, Model model, HttpSession session){
		return listAgenda(model, session);
	}
	
	public String showAgenda(Integer id, Model model, HttpSession session){
		return "user/agenda/show";
	}
	
	public String newFinancialControl(FinancialControl fc, Model model, HttpSession session) {
		return "user/financialcontrol/new";
	}
	
	public String  editFinancialControl(Integer  id, Model model, HttpSession session) {
		return "user/financialcontrol/edit";
	}
	
	public String removeFinancialControl(Integer id, Model model, HttpSession session){
		return listFinancialControl(model, session);
	}
	
	public String listFinancialControl(Model model, HttpSession session){
		return "user/financialcontrol/list";
	}
	
	public String updateFinancialControl(FinancialControl fc, Model model, HttpSession  session){
		return listFinancialControl(model, session);
	}
	
	public String showFinancialControl(Integer id, Model model, HttpSession session){
		return "user/financialcontrol/show";
	}
	
	public String downFinancialControl(Integer id, Model model, HttpSession session){
		return listFinancialControl(model, session);
	}

}
