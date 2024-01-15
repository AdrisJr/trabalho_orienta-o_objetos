package model;


public class Categoria {

	String cat;
	
	public Categoria(String cat) {
		this.cat = cat;
	}
	
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}


	public String getCat() {
		return cat;
	}


	public void setCat(String cat) {
		this.cat = cat;
	}


	public String toString()
	{
		return this.cat+"";
	}	
	
	public Object[] toObject() {
		Object[] vetor = new Object[] {cat};
		return vetor;
	}
	
}
