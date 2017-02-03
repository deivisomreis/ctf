package com.ctf.view;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctf.criptografia.CriptografarSenha;
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
	public String login(String user, String password, Model model, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(UserAware.isUser(session.getAttribute("user"))){
			model.addAttribute("user", session.getAttribute("user"));
			return "user/home";
		}
		
		else if(user != null && password != null && !password.isEmpty() && !user.isEmpty()){
			String senhaDecodificada =  CriptografarSenha.criptografarSenha(password); // gerar senha criptografada
			User userObj = userDAO.login(user, senhaDecodificada);
			
			if(userObj != null){
				if(userObj.isStatus()){
					session.setAttribute("user", userObj);
					return "user/home";
				}
				else{
					model.addAttribute("erro", userObj.getName() + " seu acesso esta bloqueado!");
					return "userlogin";
				}
			}
			else{
				model.addAttribute("erro", "");
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
			
			model.addAttribute("sucesso", "Usu�rio editado com sucesso!");
			
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
		if(id != null && id  > 0){
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("task", tasksDAO.tasks(id));
			
			return "user/tasks/edit";
		}
		else{
			model.addAttribute("erro", "erro ao redirecionar tarefa!");
			return listTasks(model, session);
		}
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
	
	@RequestMapping("/user/tasks/finalize_tasks")
	public String finalizeTasks(Integer id, HttpSession session, Model model){
		if(id != null && id > 0){
			tasksDAO.finalizeTask(id);
			
			model.addAttribute("sucesso", "tarefa finalizada com sucesso!");
		}
		else{
			model.addAttribute("erro", "erro ao finalizar a tarefa, tente novamente");
		}
		
		
		return listTasks(model, session);
	}
	
	@RequestMapping("/user/tasks/show")
	public String  showTasks(Model model, HttpSession session, Integer id) {
		if(id != null && id >0){
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("task", tasksDAO.tasks(id));
			
			return "user/tasks/show";
		}
		else{
			model.addAttribute("erro", "Erro ao redirecionar, tente novamente!");
			
			return listTasks(model, session);
		}
		
	}
	
	@RequestMapping("/user/tasks/update")
	public String updateTasks(Model model, HttpSession session, Tasks task, String option){
		if(option != null && !option.isEmpty() && option.equals("editar") && task != null){							
			tasksDAO.update(task);
			model.addAttribute("sucesso", task.getName()   + " editada com sucesso!");
		}
		else
			model.addAttribute("erro", "Erro ao atualizar a tarefa, tente novamente");
		
		return listTasks(model, session);
	}
	
	@RequestMapping("/user/agenda/register")
	public String  newAgenda(Agenda agenda, Model model, HttpSession session, String option) {
		
		model.addAttribute("user", session.getAttribute("user"));
		
		if(option != null){
			if(option.equals("cadastrar") && agenda != null && !agenda.getName().isEmpty()){
				agenda.setUser((User) session.getAttribute("user"));
				agendaDAO.insert(agenda);
				model.addAttribute("sucesso", agenda.getName() + " registrada com sucesso!");
			}
			else{
				model.addAttribute("erro", "favor verificar os dados");
			}
		}
		
		return "user/agenda/new";
	}
	
	@RequestMapping("/user/agenda/edit")
	public String editAgenda(Integer id, Model model, HttpSession session) {
		if(id != null && id > 0){
			model.addAttribute("agenda", agendaDAO.agenda(id));
			model.addAttribute("user", session.getAttribute("user"));
			
			return "user/agenda/edit";
		}
		else{
			model.addAttribute("erro", "erro ao direcionar o contato!");
			return listAgenda(model, session);
		}
	}
	
	@RequestMapping("/user/agenda/remove")
	public String removeAgenda(Integer id, Model model, HttpSession session){
		if(id != null && id > 0){
			agendaDAO.remove(id);
			model.addAttribute("sucesso", "contato removido com sucesso!");
		}
		else
			model.addAttribute("erro", "erro ao remover o contato, tente novamente");
		
		return listAgenda(model, session);
	}
	
	@RequestMapping("/user/agenda/list")
	public String listAgenda(Model model, HttpSession session){
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("agendas", agendaDAO.agendas((User) session.getAttribute("user")));
		return "user/agenda/list";
	}
	
	@RequestMapping("/user/agenda/update")
	public String updateAgenda(Agenda agenda, Model model, HttpSession session, String option){
		if(option != null){
			if(option.equals("editar") && agenda != null && !agenda.getName().isEmpty()){
				agendaDAO.update(agenda);
				model.addAttribute("sucesso", agenda.getName() + " editado com suceso");
			}
			else{
				model.addAttribute("erro", "favor verificar os dados, tente novamente");
				editAgenda(agenda.getId(), model, session);
			}
		}
		
		return listAgenda(model, session);
	}
	
	@RequestMapping("/user/agenda/show")
	public String showAgenda(Integer id, Model model, HttpSession session){
		if(id != null && id > 0){
			model.addAttribute("user ", session.getAttribute("user"));
			model.addAttribute("agenda", agendaDAO.agenda(id));
			return "user/agenda/show";
		}
		else
			return listAgenda(model, session);
		
	}
	
	@RequestMapping("/user/financialcontrol/register")
	public String newFinancialControl(FinancialControl fc, Model model, HttpSession session, String option) {
		model.addAttribute("user", session.getAttribute("user"));
		
		if(option != null){
			if(option.equals("cadastrar") && fc != null && !fc.getName().isEmpty() && fc.getValue() > 0){
				fc.setUser((User) session.getAttribute("user"));
				Date dateRegistered = new Date();
				fc.setRegistered(dateRegistered);
				
				fcDAO.insert(fc);
				model.addAttribute("sucesso", " Lan�amento cadastrado com sucesso!");
				model.addAttribute("user", session.getAttribute("user"));
			}
			else{
				model.addAttribute("erro", "Erro ao cadastrar, favor verificar os dados!");
			}
		}
		return "user/financialcontrol/new";
	}
	
	@RequestMapping("/user/financialcontrol/edit")
	public String  editFinancialControl(Integer  id, Model model, HttpSession session) {
		if(id != null && id > 0){
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("fc", fcDAO.fc(id));
			
			return "user/financialcontrol/edit";
		}
		else{
			model.addAttribute("erro", "erro ao direcionar, tente novamente!");
			return listFinancialControl(model, session);
		}		
	}
	
	@RequestMapping("/user/financialcontrol/remove")
	public String removeFinancialControl(Integer id, Model model, HttpSession session){
		if(id != null && id >0){
			fcDAO.remove(id);
			model.addAttribute("sucesso", "lan�amento removido com sucesso!");
		}
		else
			model.addAttribute("erro", "erro ao deletar lancamento");
		
		return listFinancialControl(model, session);
	}
	
	@RequestMapping("/user/financialcontrol/list")
	public String listFinancialControl(Model model, HttpSession session){
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("fcs", fcDAO.fcs((User) session.getAttribute("user")));
		return "user/financialcontrol/list";
	}
	
	@RequestMapping("/user/financialcontrol/update")
	public String updateFinancialControl(FinancialControl fc, Model model, HttpSession  session, String option){
		if(option != null){
			if(!option.isEmpty() && option.equals("editar") && fc != null){
				fcDAO.update(fc);
				
				model.addAttribute("sucesso", "Lancamento editado!");
				
				return listFinancialControl(model, session);
			}
			else{
				model.addAttribute("erro", "Erro, favor verificar os dados");				
				return editFinancialControl(fc.getId(), model, session);
			}
		}
		
		return listFinancialControl(model, session);
	}
	
	@RequestMapping("/user/financialcontrol/show")
	public String showFinancialControl(Integer id, Model model, HttpSession session){
		if(id != null && id > 0){
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("fc", fcDAO.fc(id));
			
			return "user/financialcontrol/show";
		}
		else{
			model.addAttribute("erro", "Erro ao redirecionar o lan�amento");
			return "user/financialcontrol/show";
		}
	}
	
	@RequestMapping("/user/financialcontrol/down")
	public String downFinancialControl(Integer id, Model model, HttpSession session){
		return listFinancialControl(model, session);
	}

}
