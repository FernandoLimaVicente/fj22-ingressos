package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> sessoes;

	public GerenciadorDeSessao(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public boolean cabe(Sessao novaSessao) {

		if (terminaAmanha(novaSessao))
			return false;

//		for (Sessao sessao : sessoes)
//			if (horarioIsConflitante(sessao, novaSessao))
//				return false;
//
//		return true;
		
		return	sessoes.stream()
				.noneMatch(sessao -> horarioIsConflitante(sessao, novaSessao));
	}

	private boolean horarioIsConflitante(Sessao sessao, Sessao novaSessao) {

		// i: inicio / t: termino / s: sessão / e: existente / n:nova

		LocalDateTime ise = getInicioSessaoComDiaDeHoje(sessao);
		LocalDateTime tse = getTerminoSessaoComDiaDeHoje(sessao);
		LocalDateTime ins = getInicioSessaoComDiaDeHoje(novaSessao);
		LocalDateTime tns = getTerminoSessaoComDiaDeHoje(novaSessao);

		return !(tns.isBefore(ise) || tse.isBefore(ins));

	}

	private LocalDateTime getTerminoSessaoComDiaDeHoje(Sessao sessao) {
		return getInicioSessaoComDiaDeHoje(sessao).plus(sessao.getFilme().getDuracao());
	}

	private LocalDateTime getInicioSessaoComDiaDeHoje(Sessao sessao) {
		LocalDate hoje = LocalDate.now();

		return sessao.getHorario().atDate(hoje);
	}

	private boolean terminaAmanha(Sessao sessao) {

		// tns - Termino Nova Sessao / ush - Ultimo Segundo de Hoje
		LocalDateTime tns = getTerminoNovaSessaoComDiaDeHoje(sessao);
		LocalDateTime ush = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

		if (tns.isAfter(ush)) // Se termino da nova sessao é depois do ultimo segundo do dia
			return true;

		return false;
	}

	private LocalDateTime getTerminoNovaSessaoComDiaDeHoje(Sessao sessao) {
		return getInicioSessaoComDiaDeHoje(sessao).plus(sessao.getFilme().getDuracao());
	}

}
