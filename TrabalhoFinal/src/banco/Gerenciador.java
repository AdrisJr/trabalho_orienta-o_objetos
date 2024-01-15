package banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


import model.Categoria;
import model.Filme;

public class Gerenciador {

	private final static String banco_filmes = "banco_filmes.txt";
	private final static String categorias = "categorias.txt";

	// Inserir filmes em banco_filmes:
	public boolean inserirFilme(Filme a) {

		File arquivo = new File(banco_filmes);

		try {
			arquivo.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Para escrever os dados do filme
		try (Writer saida = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(arquivo, true), StandardCharsets.UTF_8))) {
			saida.append(a.toString() + "\n");
			saida.flush();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}
	
	// Para inserir categoria no categorias.txt
	public boolean inserirCat(Categoria a) {
		File arquivo = new File(categorias);

		try {
			arquivo.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"))) {
			String str;
			while ((str = in.readLine()) != null) {
				String vet[] = str.split(";");

				if ((vet[0]).equalsIgnoreCase(a.getCat().toLowerCase().trim())) {
					// se encontrar um registro acadêmico igual, ele retorna false e não cadastra
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Para escrever os dados do filme
		try (Writer saida = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(arquivo, true), StandardCharsets.UTF_8))) {
			saida.append(a.toString() + "\n");
			saida.flush();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	public Filme getFilmebyID(int id) {

		File arquivo = new File(banco_filmes);

		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"))) {
			String str;
			while ((str = in.readLine()) != null) {
				String vet[] = str.split(";");

				if (Integer.parseInt(vet[0]) == id) {
					return new Filme(id, vet[1], vet[2],vet[3],vet[4]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public LinkedList<Categoria> getCategoria() {
		LinkedList<Categoria> categoria = new LinkedList<Categoria>();

		File arquivo = new File(categorias);

		try {
			arquivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"))) {
			String str;
			while ((str = in.readLine()) != null) {
				String vet[] = str.split(" ");
				categoria.add(new Categoria(vet[0]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return categoria;
	}

	public LinkedList<Filme> getFilmes() {
		LinkedList<Filme> filmes = new LinkedList<Filme>();

		File arquivo = new File(banco_filmes);

		try {
			arquivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"))) {
			String str;
			while ((str = in.readLine()) != null) {
				String vet[] = str.split(";");
				filmes.add(new Filme(Integer.parseInt(vet[0]), vet[1], vet[2], vet[3], vet[4]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return filmes;
	}
	
	public void remove(int ID)
	{
		LinkedList<Filme> filmes = getFilmes();
		int indice=0, aux=0;
		
		for(Filme a : filmes)
		{
			if(a.getID()==ID)
			{
				aux = indice;
			}
			indice++;
		}
		
		filmes.remove(aux);
		
		//Abre arquivo
		File arquivo = new File(banco_filmes);
		arquivo.delete();
		
		//tenta criar se não existe (cria o objeto)
		try {
			arquivo.createNewFile();	//surround with try/catch
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Writer saida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo,true),StandardCharsets.UTF_8)))
		{
			for(Filme a: filmes) {
				saida.append(a.toString()+"\n");
			}
			saida.flush(); //limpar buffer
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
