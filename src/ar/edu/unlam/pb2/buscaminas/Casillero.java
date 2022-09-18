package ar.edu.unlam.pb2.buscaminas;

public class Casillero {

	private Boolean estaRevelado = false;
	private Boolean tieneMina;
	private Integer cantidadDeMinasAlRededor;
	
	public Casillero() {
	}

	public Casillero(Boolean tieneMina) {
		this.tieneMina = tieneMina;
	}

	public Boolean activar(Casillero[][] tablero, Integer posicionFila, Integer posicionColumna) {
		if (this.tieneMina) {
			return true;
		} else {
			this.calcularCantidadDeMinasAlRededor(tablero, posicionFila, posicionColumna);
			this.estaRevelado = true;
			if (this.cantidadDeMinasAlRededor == 0) {
				for (int i = Math.max(0, posicionFila - 1); i < Math.min(posicionFila + 2, tablero.length); i++) {
					for (int j = Math.max(0, posicionColumna - 1); j < Math.min(posicionColumna + 2, tablero[i].length); j++) {
						if (!tablero[i][j].estaRevelado()) {
							tablero[i][j].activar(tablero, i, j);
						}
					}
				}
			}
			return false;
		}
	}

	private void calcularCantidadDeMinasAlRededor(Casillero[][] tablero, Integer posicionFila,
			Integer posicionColumna) {
		Integer cantidadDeMinas = 0;
		for (int i = Math.max(0, posicionFila - 1); i < Math.min(posicionFila + 2, tablero.length); i++) {
			for (int j = Math.max(0, posicionColumna - 1); j < Math.min(posicionColumna + 2, tablero[i].length); j++) {
				if (tablero[i][j].tieneMina) {
					cantidadDeMinas++;
				}
			}
		}
		this.cantidadDeMinasAlRededor = cantidadDeMinas;
	}

	public Boolean estaRevelado() {
		return this.estaRevelado;
	}

	public Boolean tieneMina() {
		return this.tieneMina;
	}

	public Integer getCantidadDeMinasAlRededor() {
		return this.cantidadDeMinasAlRededor;
	}
	
	public void setMina() {
		this.tieneMina = true;
	}

}
