package model;

public class Filme {
	private int filmeID;
	private String nomeFilme;
	private String sinopse;
	private String categoria;
	private String autor;
	
	public Filme( int i,String nome,String autor, String sinopse, String categoria) {
		
		this.nomeFilme = nome;
		this.sinopse = sinopse;
		this.filmeID = i;
		this.autor = autor;
		this.categoria = categoria;		
	}
	
	public int getID() {
		return filmeID;
	}
	
	public void setID(int ID) {
		this.filmeID = ID;
	}
	
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}
	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setAtores(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String toString()
	{
		return Integer.toString(filmeID)+";"+nomeFilme+";"+autor+";"+sinopse+";"+categoria;
	}	
	
	public Object[] toObject()
	{
		Object[] vetor = new Object[] {filmeID, nomeFilme,autor, sinopse, categoria};
		return vetor;
	}
	
}

