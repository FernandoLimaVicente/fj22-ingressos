package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sessao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@ManyToOne
	private Sala sala;
	@ManyToOne
	private Filme filme;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Ingresso> ingressos = new HashSet<Ingresso>();

	private LocalTime horario;

	private BigDecimal preco;


	public Sessao(LocalTime horario, Filme filme, Sala sala) {
		this.horario = horario;
		this.sala = sala;
		this.filme = filme;
		this.preco = sala.getPreco().add(filme.getPreco());
	}
	
	/**
	 * @deprecated hibernate only
	 */
	public Sessao() {

	}
	
	public boolean isDisponivel(Lugar lugar) {
		return ingressos.stream()
				.map(Ingresso::getLugar)
				.noneMatch(l -> l.equals(lugar));
	}
	
	
	public Map<String,List<Lugar>> getMapaDeLugares(){
		return sala.getMapaDeLugares();
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Set<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	
}
