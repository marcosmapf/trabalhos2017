package custom.Objects;

import java.security.SecureRandom;

public class VetorInt {

	int tamanhoVetor = 0;
	int[] vetorInteiros;

	public VetorInt(int tamanho){

		this.vetorInteiros = new int[tamanho];
		tamanhoVetor = tamanho;
		zerarVetor(vetorInteiros);		
	}
	
	public VetorInt(int[] vetor){

		this.vetorInteiros = vetor;
		tamanhoVetor = vetor.length;
		zerarVetor(vetorInteiros);			
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void printVetor(){

		for (int i = 0; i < tamanhoVetor - 1; i ++){

			System.out.print(vetorInteiros[i]+ ", ");
		}

		System.out.println(vetorInteiros[tamanhoVetor -1]);
	}



	public static void printVetor(int[] matriz){

		int tamanho = matriz.length;

		for (int i = 0; i < tamanho - 1; i ++){

			System.out.print(matriz[i]+ ", ");
		}

		System.out.println(matriz[tamanho]);
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void preencherAleatorio(int limite){

		SecureRandom aleatorio = new SecureRandom();

		for (int i = 0; i < tamanhoVetor; i++){

			vetorInteiros[i] = aleatorio.nextInt(limite);
		}
	}


	public static int[] preencherAleatorio(int[] vetor, int limite){

		int tamanho = vetor.length;
		SecureRandom aleatorio = new SecureRandom();

		for (int i = 0; i < tamanho; i++){

			vetor[i] = aleatorio.nextInt(limite);
		}

		return vetor;
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void zerarVetor(){

		for (int i = 0; i < tamanhoVetor; i++){

			vetorInteiros[i] = 0;
		}
	}

	public static int[] zerarVetor(int[] vetor){

		int tamanho = vetor.length;

		for (int i = 0; i < tamanho; i++){

			vetor[i] = 0;
		}

		return vetor;
	}


	/*<--------------------------------------------------------------------------------------->*/


	public void setValor(int i, int valor){

		this.vetorInteiros[i] = valor;
	}
	
	public int getValor(int i){

		return this.vetorInteiros[i];
	}

	public int getTamanhoVetor() {
		
		return tamanhoVetor;
	}
}
