package lista07.Cadastro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import custom.Objects.Pessoa;

public class Cadastro {

	Hashtable<String, LinkedList<Pessoa>> tabelaHash = new Hashtable <String, LinkedList<Pessoa>>();
	private int colisoes;	

	/*<--------------------------------------------------------------------------------------->*/

	public Cadastro (){

		this.colisoes = 0;
	}

	public void cadastrar () {

		this.colisoes = 0;

		LinkedList<Pessoa> pessoas = this.listar ();

		System.out.println ("----------------------------");
		System.out.println ("--- Cadastro de Pessoas: ---");
		System.out.println ("----------------------------");

		for (Pessoa p : pessoas) {

			System.out.println ("Cadatrando: Pessoa: " + p.getNome() + " Profiss�o: " + p.getProfissao());

			if (this.tabelaHash.containsKey(p.getProfissao())) {

				LinkedList<Pessoa> l = this.tabelaHash.get(p.getProfissao());
				l.add (p);
				this.colisoes++;
			} 
			else {

				LinkedList<Pessoa> l = new LinkedList<Pessoa>();
				l.add (p);
				this.tabelaHash.put (p.getProfissao (), l);
			}
		}		
		System.out.println();
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void listarCadastro () {

		int i, cont = 0;
		Enumeration<String> profissoes = this.tabelaHash.keys ();

		System.out.println ("-----------------------------");
		System.out.println ("--- Conte�do do Cadastro: ---");
		System.out.println ("-----------------------------");

		while (profissoes.hasMoreElements ()) {

			cont++;
			String profissao = profissoes.nextElement();
			LinkedList<Pessoa> l = this.tabelaHash.get(profissao);

			System.out.print (cont + ") " + profissao + ": ");

			for (i = 0; i < l.size(); i++) {

				Pessoa p = l.get(i);
				System.out.print (p.getNome ());
			}			
			System.out.println();
		}
		System.out.println ();
	}


	/*<--------------------------------------------------------------------------------------->*/


	public LinkedList<Pessoa> listar() {

		LinkedList<String> listaNomes, listaProfissoes;
		LinkedList<Pessoa> pessoas = new LinkedList<Pessoa>();

		listaNomes = this.lerArquivo("listaNomes2.txt");
		listaProfissoes = this.lerArquivo("listaProfissoes2.txt");

		System.out.println ("-------------------------------------------");
		System.out.println ("--- Lista de Pessoas e suas Profissões: ---");
		System.out.println ("-------------------------------------------");

		for (int i = 0; i < listaNomes.size(); i++) {

			Pessoa p = new Pessoa (listaNomes.get(i), listaProfissoes.get(i));
			System.out.println("Pessoa: " + p.getNome() + " Profiss�o: " + p.getProfissao());
			pessoas.add(p);
		}

		System.out.println();
		return pessoas;
	}


	/*<--------------------------------------------------------------------------------------->*/


	public LinkedList<String> lerArquivo(String nomeArquivo) {

		LinkedList<String> listaEncadeada = new LinkedList<String>();
		File arquivo = null;
		FileInputStream entrada = null;
		InputStreamReader leitor = null;
		BufferedReader buffer_entrada = null;

		try {

			arquivo = new File(nomeArquivo);
			entrada = new FileInputStream(arquivo);
			leitor = new InputStreamReader(entrada);
			buffer_entrada = new BufferedReader(leitor);

			System.out.println ("-------------------------------------------------");
			System.out.println ("--- Conte�do do Arquivo : " + nomeArquivo + " ---");
			System.out.println ("-------------------------------------------------");

			String linha;

			while ((linha = buffer_entrada.readLine()) != null) {

				linha = linha.trim();
				System.out.println(linha);
				listaEncadeada.add(linha);
			}

			System.out.println();
		} 
		catch (Exception e) {

			System.out.println("Erro ao ler o arquivo " + nomeArquivo + "!");
			e.printStackTrace();
		} 
		finally {

			try {

				if (buffer_entrada != null) {buffer_entrada.close ();}
				if (leitor != null) {leitor.close ();}
				if (entrada != null) {entrada.close ();}
			} 
			catch (Exception e) {e.printStackTrace ();}
		}

		return listaEncadeada;
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void imprimirColisoes(){

		System.out.println("\nO numero de colisoes durante a inser��o de dados foi: " + this.colisoes);
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void informarTamanhoListas(){

		if (!this.tabelaHash.isEmpty()){

			int tamanho;
			Enumeration<String> profissoes = this.tabelaHash.keys();
			LinkedList<Pessoa> pessoas;

			while(profissoes.hasMoreElements()){

				tamanho = 0;
				String profissao = profissoes.nextElement();
				pessoas = this.tabelaHash.get(profissao);

				for (Pessoa p : pessoas) 
					tamanho++;

				System.out.print("\nProfissao: " + profissao + "   Tamanho da Lista: " + tamanho);			
			}
		}
		else{System.out.println("\nA tabela esta vazia!");}
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void InformarTamanhoMedio(){

		if (!this.tabelaHash.isEmpty()){

			float resultado = 0;
			Enumeration<String> profissoes = this.tabelaHash.keys();
			LinkedList<Pessoa> pessoas = null;

			while(profissoes.hasMoreElements()){

				pessoas = tabelaHash.get(profissoes.nextElement());

				for (Pessoa p : pessoas) 
					resultado++;		

				resultado = resultado/tabelaHash.size();
				System.out.println("\nAs listas da Tabela Hash possuem em media " + resultado + "  elementos");
			}
		}
		else{System.out.println("\nA tabela esta vazia!");}
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void informarCadastros(String profissao){

		if(tabelaHash.containsKey(profissao)){

			LinkedList<Pessoa> pessoas = tabelaHash.get(profissao);

			System.out.print("\nNomes: ");

			for (Pessoa p : pessoas){

				System.out.print(p.getNome() + ", ");
			}
		}
		else{System.out.println("\nN�o existe ningu�m cadastrado nessa profiss�o!");
		}
	}


	/*<--------------------------------------------------------------------------------------->*/

	public void buscarPessoa(String nome){

		if (!this.tabelaHash.isEmpty()){

			Enumeration<String> profissoes = this.tabelaHash.keys();
			LinkedList<Pessoa> pessoas = null;
			boolean procurando = true;
			String profissao = "";
			int comparacoes = 0;

			while(profissoes.hasMoreElements() && procurando){

				profissao = profissoes.nextElement();
				pessoas = tabelaHash.get(profissao);

				for (Pessoa p : pessoas){

					comparacoes++;

					if (p.getNome().equalsIgnoreCase(nome)) {
						procurando = false;
						break;
					}
				}
			}

			if (procurando){

				System.out.println("\nA pessoa n�o est� cadastrada!");
			}
			else System.out.println(nome + " est� cadastrada na profissao: " + profissao);

			System.out.println("Numero de comparacoes: " + comparacoes);
		}
	}
}