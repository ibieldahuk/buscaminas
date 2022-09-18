package ar.edu.unlam.pb2.buscaminas;

public class Buscaminas {

	private Boolean estaPerdido = false;
	private Boolean estaGanado = false;
	private Casillero[][] tablero;

	public Buscaminas() {
	}

	public Buscaminas(Integer cantidadDeFilas, Integer cantidadDeColumnas, Integer cantidadDeMinas) {
		this.tablero = generarTablero(cantidadDeFilas, cantidadDeColumnas);
		colocarMinas(cantidadDeMinas);
	}
	
	private Casillero[][] generarTablero(Integer cantidadDeFilas, Integer cantidadDeColumnas) {
		Casillero[][] tablero = new Casillero[cantidadDeFilas][cantidadDeColumnas];
		Boolean noTieneMina = false;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = new Casillero(noTieneMina);
			}
		}
		return tablero;
	}
	
	private void colocarMinas(Integer cantidadDeMinasDeseada) {
		Integer cantidadDeCasilleros = this.tablero.length * this.tablero[0].length;
		Double posibilidadDeMinaPorCasillero = (cantidadDeMinasDeseada * 100.0) / cantidadDeCasilleros;
		Integer cantidadDeMinasColocadas = 0;
		while (cantidadDeMinasColocadas < cantidadDeMinasDeseada) {
			for (int i = 0; i < this.tablero.length; i++) {
				for (int j = 0; j < this.tablero[i].length; j++) {
					if (!this.tablero[i][j].tieneMina()) {
						Double dado = Math.random() * 100.0;
						if (dado < posibilidadDeMinaPorCasillero) {
							this.tablero[i][j].setMina();
							cantidadDeMinasColocadas++;
						}
					}
					if (cantidadDeMinasColocadas >= cantidadDeMinasDeseada) {
						break;
					}
				}
				if (cantidadDeMinasColocadas >= cantidadDeMinasDeseada) {
					break;
				}
			}
		}
	}

	public void activarCasillero(Casillero[][] tablero, Integer posicionFila, Integer posicionColumna) {
		if (tablero[posicionFila][posicionColumna].activar(tablero, posicionFila, posicionColumna)) {
			this.estaPerdido = true;
		} else {
			Integer cantidadDeCasilleros = tablero.length * tablero[0].length;
			Integer cantidadDeMinas = 0;
			Integer cantidadDeCasillerosRevelados = 0;
			for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero[i].length; j++) {
					if (tablero[i][j].estaRevelado()) {
						cantidadDeCasillerosRevelados++;
					}
					if (tablero[i][j].tieneMina()) {
						cantidadDeMinas++;
					}
				}
			}
			if (cantidadDeMinas + cantidadDeCasillerosRevelados == cantidadDeCasilleros) {
				this.estaGanado = true;
			}
		}
	}

	public Boolean estaGanado() {
		return estaGanado;
	}

	public Boolean estaPerdido() {
		return estaPerdido;
	}

	public Casillero[][] getTablero() {
		return this.tablero;
	}

}
