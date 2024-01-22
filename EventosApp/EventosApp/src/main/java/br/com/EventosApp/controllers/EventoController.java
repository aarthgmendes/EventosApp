package br.com.EventosApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.EventosApp.models.Convidado;
import br.com.EventosApp.models.Evento;
import br.com.EventosApp.repository.ConvidadoRepository;
import br.com.EventosApp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	//metodo para salvar o evento
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos e tente novamente!");
			return "redirect:/cadastrarEvento";
		}
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("id") long id) {
		Evento evento = er.findById(id);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		//busca e criação da lista dos convidados na pagina
		Iterable<Convidado> convidados= cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	//função para deletar um evento da tabela
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long id) {
		Evento evento = er.findById(id);
		er.delete(evento);
		
		return "redirect:/eventos";
	}
	
	//metodo pra relacionar o convidado inserido com o evento desejado, salvando o convidado no banco
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("id") long id, @Valid Convidado convidado, RedirectAttributes attributes) {
		if(convidado.getNomeConvidado().equals("") || convidado.getRg().equals("")) {
			attributes.addFlashAttribute("mensagem", "**VERIFIQUE OS CAMPOS E TENTE NOVAMENTE!**");
			return "redirect:/{id}";
		} else {
			Evento evento = er.findById(id);
			convidado.setEvento(evento);
			cr.save(convidado);
			attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
			return "redirect:/{id}";
		}
	}
	
	//função pra deletar convidado
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
		
		//pegar o codigo do evento, para redirecionar para o método ${/id}
		Evento evento = convidado.getEvento();
		long idLong = evento.getId();
		String id = "" + idLong;
		return "redirect:/" + id;
	}
}
