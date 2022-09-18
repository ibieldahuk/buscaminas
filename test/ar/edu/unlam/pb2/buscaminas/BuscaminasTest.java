package ar.edu.unlam.pb2.buscaminas;

import org.junit.Assert;

import org.junit.Test;

public class BuscaminasTest {
	
	@Test
	public void queAlActivarUnCasilleroSinMinasSeaRevelado() {
		Buscaminas juego = dadoQueExisteUnJuegoDeBuscaminas();
		Casillero[][] tableroArreglado = dadoQueExisteUnTableroArreglado();
		Integer posicionFila = 0;
		Integer posicionColumna = 1;
		cuandoActivoElCasillero(juego, tableroArreglado, posicionFila, posicionColumna);
		entoncesSeRevelaElCasillero(tableroArreglado, posicionFila, posicionColumna);
	}
	
	@Test
	public void queAlActivarUnCasilleroConMinasSePierdaElJuego() {
		Buscaminas juego = dadoQueExisteUnJuegoDeBuscaminas();
		Casillero[][] tableroArreglado = dadoQueExisteUnTableroArreglado();
		Integer posicionFila = 0;
		Integer posicionColumna = 0;
		cuandoActivoElCasillero(juego, tableroArreglado, posicionFila, posicionColumna);
		entoncesPerdiElJuego(juego);
	}
	
	@Test
	public void queAlRevelarTodosLosCasillerosSinMinasSeGaneElJuego() {
		Buscaminas juego = dadoQueExisteUnJuegoDeBuscaminas();
		Casillero[][] tableroArreglado = dadoQueExisteUnTableroArreglado();
		cuandoActivoElCasillero(juego, tableroArreglado, 0, 1);
		cuandoActivoElCasillero(juego, tableroArreglado, 0, 2);
		cuandoActivoElCasillero(juego, tableroArreglado, 1, 1);
		cuandoActivoElCasillero(juego, tableroArreglado, 1, 2);
		cuandoActivoElCasillero(juego, tableroArreglado, 2, 0);
		cuandoActivoElCasillero(juego, tableroArreglado, 2, 1);
		cuandoActivoElCasillero(juego, tableroArreglado, 2, 2);
		entoncesGaneElJuego(juego);
	}
	
	@Test
	public void queAlActivarUnCasilleroCalculeLaCantidadDeMinasAlRededorSuyo() {
		Casillero[][] tableroArreglado = dadoQueExisteUnTableroArreglado();
		Buscaminas juego = dadoQueExisteUnJuegoDeBuscaminas();
		Integer posicionFila = 2;
		Integer posicionColumna = 2;
		cuandoActivoElCasillero(juego, tableroArreglado, posicionFila, posicionColumna);
		Integer cantidadDeMinas = cuandoCalculoLasMinasAlRededorDelCasillero(tableroArreglado, posicionFila, posicionColumna);
		entoncesLaCantidadDeMinasEs(0, cantidadDeMinas);
	}
	
	@Test
	public void queAlActivarUnCasilleroSinMinasAlRededorSeRevelenLosCasillerosSinMinasAdyacentes() {
		Casillero[][] tableroArreglado = dadoQueExisteUnTableroArreglado();
		Buscaminas juego = dadoQueExisteUnJuegoDeBuscaminas();
		cuandoActivoElCasillero(juego, tableroArreglado, 0, 2);
		entoncesSeRevelaElCasillero(tableroArreglado, 1, 1);
	}
	
	@Test
	public void queAlGenerarseElTableroTengaLaCantidadDeMinasCorrecta() {
		Integer cantidadDeFilas = 9;
		Integer cantidadDeColumnas = 9;
		Integer cantidadDeMinas = 10;
		Buscaminas juego = cuandoCreoUnJuegoDeBuscaminas(cantidadDeFilas, cantidadDeColumnas, cantidadDeMinas);
		entoncesLaCantidadDeMinasEnElTableroEs(cantidadDeMinas, juego.getTablero());
	}
	
	private Buscaminas dadoQueExisteUnJuegoDeBuscaminas() {
		Buscaminas juego = new Buscaminas();
		return juego;
	}
	
	private void entoncesSeRevelaElCasillero(Casillero[][] tablero, Integer posicionFila, Integer posicionColumna) {
		Assert.assertTrue(tablero[posicionFila][posicionColumna].estaRevelado());
	}
	
	private void cuandoActivoElCasillero(Buscaminas juego, Casillero[][] tablero, Integer posicionFila, Integer posicionColumna) {
		juego.activarCasillero(tablero, posicionFila, posicionColumna);
	}
	
	private Integer cuandoCalculoLasMinasAlRededorDelCasillero(Casillero[][] tablero, Integer posicionFila, Integer posicionColumna) {
		return tablero[posicionFila][posicionColumna].getCantidadDeMinasAlRededor();
	}
	
	private Buscaminas cuandoCreoUnJuegoDeBuscaminas(Integer cantidadDeFilas, Integer cantidadDeColumnas, Integer cantidadDeMinas) {
		Buscaminas juego = new Buscaminas(cantidadDeFilas, cantidadDeColumnas, cantidadDeMinas);
		return juego;
	}
	
	private void entoncesPerdiElJuego(Buscaminas juego) {
		Assert.assertTrue(juego.estaPerdido());
	}
	
	private void entoncesGaneElJuego(Buscaminas juego) {
		Assert.assertTrue(juego.estaGanado());
	}
	
	private void entoncesLaCantidadDeMinasEs(Integer valorEsperado, Integer valorObtenido) {
		Assert.assertEquals(valorEsperado, valorObtenido);
	}
	
	private void entoncesLaCantidadDeMinasEnElTableroEs(Integer cantidadDeMinasEsperada, Casillero[][] tablero) {
		Integer cantidadDeMinasEncontradas = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].tieneMina()) {
					cantidadDeMinasEncontradas++;
				}
			}
		}
		Assert.assertEquals(cantidadDeMinasEsperada, cantidadDeMinasEncontradas);
	}
	
	private Casillero[][] dadoQueExisteUnTableroArreglado(){
		Boolean tieneMina = true;
		Boolean noTieneMina = false;
		Casillero[] fila1 = {new Casillero(tieneMina), new Casillero(noTieneMina), new Casillero(noTieneMina)};
		Casillero[] fila2 = {new Casillero(tieneMina), new Casillero(noTieneMina), new Casillero(noTieneMina)};
		Casillero[] fila3 = {new Casillero(noTieneMina), new Casillero(noTieneMina), new Casillero(noTieneMina)};
		Casillero[][] tableroArreglado = {fila1, fila2, fila3};
		return tableroArreglado;
	}

}
