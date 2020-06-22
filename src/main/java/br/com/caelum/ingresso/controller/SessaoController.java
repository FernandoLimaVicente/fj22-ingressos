package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;

	@Autowired
	private FilmeDao filmeDao;

	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {

		ModelAndView mav = new ModelAndView("sessao/sessao");

		mav.addObject("sala", salaDao.findOne(salaId));
		mav.addObject("filmes", filmeDao.findAll());
		mav.addObject("form", form);

		return mav;
	}
	
	@PostMapping(value = "/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
		
		System.out.println("Entrei no salva...");
		
		if(result.hasErrors())
			return form(form.getSalaId(),form);
		
		System.out.println("passei o primeiro if");
		
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		List<Sessao> sessoes = sessaoDao.buscaSessoesDeSala(sessao.getSala());
		GerenciadorDeSessao gds = new GerenciadorDeSessao(sessoes);
		
		System.out.println("Passei a inicialização das variaveis");
		
		if(gds.cabe(sessao)) {
			System.out.println("A sessao cabe....");
			sessaoDao.save(form.toSessao(salaDao, filmeDao));	
			return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");		
		}
		
		System.out.println("Acho que a sessão nao cabe");
		
		return form(form.getSalaId(),form);
	}

}
