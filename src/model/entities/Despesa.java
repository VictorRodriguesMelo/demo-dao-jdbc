package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Despesa  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;	
	private Date data;
	private Double valor;	
	private Categoria categoria;
	
	public Despesa () { 		
	}	
	public Despesa(Integer id, String descricao, Date data, Double valor, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.categoria = categoria;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data; 
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Categoria getDepartment() {
		return categoria;
	}
	public void setDepartment(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Seller [id=" + id + ", descricao=" + descricao + ", data=" + data + ", valor=" + valor + ", department="
				+ categoria + "]";
	}
	
	
}
