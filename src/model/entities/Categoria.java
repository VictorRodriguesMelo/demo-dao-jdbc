package model.entities;

import java.io.Serializable;

public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	
	public Categoria() {
		super();
	}
	public Categoria(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	@Override
	public String toString() {
		return "Department [id=" + id + ", descricao=" + descricao + "]";
	}		
	
}
