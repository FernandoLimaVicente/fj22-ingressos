package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.model.Filme;

public class TestaStream {

	public static void main(String[] args) {

		Filme f1 = new Filme("Terminator", Duration.ofMinutes(119), "Acao");
		Filme f2 = new Filme("Die Hard", Duration.ofMinutes(119), "Acao");
		Filme f3 = new Filme("Predator", Duration.ofMinutes(119), "Ficcao");
		Filme f4 = new Filme("Alien", Duration.ofMinutes(119), "Ficcao");

		List<Filme> filmes = Arrays.asList(f1, f2, f3, f4);

		

		System.out.println("Todos os filmes: ");
	
		//Esse
		filmes.stream().forEach(f -> System.out.println(f.getNome()));
		
		List<Filme> ficcaoFilmes = filmes.stream()
										.filter(f -> f.getGenero().equals("Ficcao"))
										.collect(Collectors.toList());
		
		System.out.println("\nFilmes de Ficcao: ");

		//E esse são a mesma coisa
		ficcaoFilmes.stream().map(f -> f.getNome()).forEach(System.out::println);
		
		
		System.out.println("\nFilmes de Acao ordenado: ");
		
		
		filmes.stream()
				.filter(f -> f.getGenero().equals("Acao"))
				.map(f -> f.getNome())
				.sorted()
				.forEach(System.out::println);
	}

}
