package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class TestaGerenciadorDeSessao {

	public static void main(String[] args) {

		System.out.println("Ola. Iniciando alguns testes");

		// Teste #1 - Cabe
		Filme f = new Filme("Terminator", Duration.ofMinutes(119), "Acao");
		Sala s = new Sala("Sala 1");
		Sessao sessao = new Sessao(LocalTime.of(22, 00), f, s);
		List<Sessao> sessoes = new ArrayList<Sessao>();
		GerenciadorDeSessao gds = new GerenciadorDeSessao(sessoes);

		System.out.println(gds.cabe(sessao) == true ? "Ok" : "Nok");

		// Teste #2 - Não cabe
		f = new Filme("Terminator II", Duration.ofMinutes(122), "Acao");
		s = new Sala("Sala 1");
		sessao = new Sessao(LocalTime.of(22, 00), f, s);
		sessoes = new ArrayList<Sessao>();
		gds = new GerenciadorDeSessao(sessoes);

		System.out.println(gds.cabe(sessao) == false ? "Ok" : "Nok");

		// Teste #3 - Inserindo com sessão existente
		f = new Filme("Terminator III", Duration.ofMinutes(60), "Acao");
		s = new Sala("Sala 1");
		sessao = new Sessao(LocalTime.of(19, 00), f, s);
		Sessao novaSessao = new Sessao(LocalTime.of(22, 00), f, s);
		sessoes = Arrays.asList(sessao);
		gds = new GerenciadorDeSessao(sessoes);

		System.out.println(gds.cabe(novaSessao) == true ? "Ok" : "Nok");

		// Teste #4 - Inserindo com sessão existente conflitando
		f = new Filme("Terminator IV", Duration.ofMinutes(52), "Acao");
		s = new Sala("Sala 1");
		sessao = new Sessao(LocalTime.of(22, 00), f, s);
		novaSessao = new Sessao(LocalTime.of(22, 30), f, s);
		sessoes = Arrays.asList(sessao);
		gds = new GerenciadorDeSessao(sessoes);

		System.out.println(gds.cabe(novaSessao) == false ? "Ok" : "Nok");

	}
}
