package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.enviadorEmail.service.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoService service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {
		Iterable<Convidado> convidados = service.obterTodos();
		model.addAttribute("convidados", convidados);
		return "listaconvidados";
	}
	
	@RequestMapping("adicionaConvidado")
	public String adicionaConvidado(Convidado convidado) {
		service.salvar(convidado);
		new EmailService().enviar(convidado.getNome(), convidado.getEmail());
		return "redirect:listaconvidados";
	}
	
}
