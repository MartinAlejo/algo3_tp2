package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.General.Casillero;
import edu.fiuba.algo3.modelo.General.Escenario;
import edu.fiuba.algo3.modelo.General.Ubicacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EscenarioTest {
	private int filas = 4;
	private int columnas = 4;
	@Test
	public void escenarioEsElMismo() {
		assertEquals(Escenario.getInstance(), Escenario.getInstance());
	}

	@Test
    public void alResetearEscenarioYaNoEsElMismoEscenario() {
		Escenario.resetInstance(filas, columnas);

		Escenario primerEscenario = Escenario.getInstance();
		Escenario.resetInstance(filas, columnas);
		Escenario segundoEscenario = Escenario.getInstance();

        assertNotEquals(primerEscenario, segundoEscenario);
    }

	@Test
	public void elEscenarioSeReseteoCorrectamente() {
		int filasEsperadas = 6;
		int columnasEsperadas = 6;
		Escenario.resetInstance(filasEsperadas, columnasEsperadas);

		assertTrue(Escenario.getInstance().verificarNumeroDeFilas(filasEsperadas));
		assertTrue(Escenario.getInstance().verificarNumeroDeColumnas(columnasEsperadas));

		Escenario.resetInstance(filas, columnas);
		assertTrue(Escenario.getInstance().verificarNumeroDeFilas(filas));
		assertTrue(Escenario.getInstance().verificarNumeroDeColumnas(columnas));
	}

	@Test
	public void EscenarioDevuelveElCasilleroBuscadoPorUbicacion() {
		Escenario.resetInstance(filas, columnas);

		Escenario primerEscenario = Escenario.getInstance();
		int fila = 2;
		int columna = 2;
		Casillero casilleroBuscado = primerEscenario.buscarCasilleroEn(new Ubicacion(fila,columna));
		int otraFila = 3;
		int otraColumna = 3;

		assertTrue(casilleroBuscado.equals(new Casillero(fila,columna)));
		assertFalse(casilleroBuscado.equals(new Casillero(otraFila,otraColumna)));
	}



	@Test
	public void alObtenerLaInstanciaDeEscenarioEstaSeComparteEntreFunciones() {
		assertEquals(auxiliarObtieneUnEscenarioYLoDevuelve(), Escenario.getInstance());
	}

	// Metodo auxiliar para tests
	private Escenario auxiliarObtieneUnEscenarioYLoDevuelve() {
		return Escenario.getInstance();
	}
}
