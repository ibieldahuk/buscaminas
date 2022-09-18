package ar.edu.unlam.pb2.buscaminas;

import java.util.Scanner;

public class BuscaminasMain {

	public static void main(String[] args) {
		Integer cantidadDeFilas = 9;
		Integer cantidadDeColumnas = 9;
		Integer cantidadDeMinas = 10;
		Buscaminas juego = new Buscaminas(cantidadDeFilas, cantidadDeColumnas, cantidadDeMinas);
//		Casillero[][] tablero = generarTablero();
		Scanner teclado = new Scanner(System.in);
		mainLoop(juego, juego.getTablero(), teclado);
	}
	
	private static void mainLoop(Buscaminas juego, Casillero[][] tablero, Scanner teclado) {
		while (!juego.estaGanado() && !juego.estaPerdido()) {
			imprimirTablero(tablero);
			System.out.print("Ingrese la fila que desea activar: ");
			Integer posicionFila = teclado.nextInt();
			System.out.print("Ingrese la columna que desea activar: ");
			Integer posicionColumna = teclado.nextInt();
			juego.activarCasillero(tablero, posicionFila, posicionColumna);
		}
		imprimirTablero(tablero);
		if (juego.estaGanado()) {
			System.out.println("Ganaste!");
		} else if (juego.estaPerdido()) {
			System.out.println("Perdiste.");
		}
	}
	
//	private static Casillero[][] generarTablero(){
//		Boolean tieneMina = true;
//		Boolean noTieneMina = false;
//		Casillero[] fila1 = {new Casillero(tieneMina), new Casillero(noTieneMina), new Casillero(noTieneMina)};
//		Casillero[] fila2 = {new Casillero(tieneMina), new Casillero(noTieneMina), new Casillero(noTieneMina)};
//		Casillero[] fila3 = {new Casillero(noTieneMina), new Casillero(noTieneMina), new Casillero(noTieneMina)};
//		Casillero[][] tablero = {fila1, fila2, fila3};
//		return tablero;
//	}
	
	private static void imprimirTablero(Casillero[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].estaRevelado()) {
					System.out.print(tablero[i][j].getCantidadDeMinasAlRededor() + " ");
				} else {
					System.out.print("x ");
				}
			}
			System.out.println();
		}
	}

}
