package models;

import javax.persistence.*;

import play.data.validation.Constraints.*;


@Entity(name="Meta")
public class Meta{
	
	@Id @GeneratedValue
	private Long id;

	@Transient
	private final String PENDENTE = "pendente";
	@Transient
	private final String CONCLUIDA = "concluida";

	@Required
	private String descricao;
	private String situacao = PENDENTE;
	private int prioridade;
	private int semana;
	
	public int getSemana() {
		return semana;
	}

	public void setSemana(int semana) {
		if(semana>=1 || semana <=6)
			this.semana = semana;
	}

	public Meta(){}

	public Meta(String descricao, int prioridade, int semana){
		situacao = PENDENTE;
		this.descricao = descricao;
		setPrioridade(prioridade);
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		if(prioridade >= 1 && prioridade <= 5)
			this.prioridade = prioridade;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao.toLowerCase();
	}

	public String getPENDENTE() {
		return PENDENTE;
	}

	public String getCONCLUIDA() {
		return CONCLUIDA;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Meta){
			if(descricao.equals(((Meta) o).getDescricao()))
				return true;
		}
		return false;
	}

	@Override
	public String toString(){
		return "Id: " + getId() + "; Descricao: " + getDescricao() + "; Situcao: " + getSituacao() + "; Semana: " + getSemana() + "; Prioridade: " + getPrioridade() +"\n";
	}
}
