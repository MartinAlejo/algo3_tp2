package edu.fiuba.algo3.modelo.Sorpresas;

import edu.fiuba.algo3.modelo.General.Jugador;
import edu.fiuba.algo3.modelo.General.Ubicacion;
import edu.fiuba.algo3.modelo.Vehiculo.Auto;
import edu.fiuba.algo3.modelo.Vehiculo.Camioneta;
import edu.fiuba.algo3.modelo.Vehiculo.Moto;
import edu.fiuba.algo3.modelo.Vehiculo.Vehiculo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesfavorableTest {
    private String nombre = "Pablo";
    private int fila = 1;
    private int columna = 1;

    @Test
    public void unJugadorPasaPorUnaSorpresaDesfavorableConMotoDeberiaDescontarse20PorcientoDeMovimientosAJugador() {
        Sorpresa desfavorable = new Desfavorable();
        Ubicacion ubicacion = (new Ubicacion(fila, columna));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador = new Jugador(nombre, moto);
        int cantidadMovimientosHechos = 10;
        int cantidadMovimientosEsperados = (int) (cantidadMovimientosHechos * 1.25);

        jugador.incrementarMovimientos(cantidadMovimientosHechos);
        moto.atravesar(desfavorable, jugador);

       // assertEquals(jugador.obtenerMovimientos(), cantidadMovimientosEsperados);

        assertTrue(jugador.verificarMovimiento(cantidadMovimientosEsperados));
    }

    @Test
    public void unJugadorPasaPorUnaSorpresaDesfavorableConAutoDeberiaDescontarse20PorcientoDeMovimientosAJugador() {
        Sorpresa desfavorable = new Desfavorable();
        Ubicacion ubicacion = (new Ubicacion(fila, columna));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador = new Jugador(nombre, auto);
        int cantidadMovimientosHechos = 10;
        int cantidadMovimientosEsperados = (int) (cantidadMovimientosHechos * 1.25);

        jugador.incrementarMovimientos(cantidadMovimientosHechos);
        auto.atravesar(desfavorable, jugador);

        assertTrue(jugador.verificarMovimiento(cantidadMovimientosEsperados));
    }

    @Test
    public void unJugadorPasaPorUnaSorpresaDesfavorableConCamionetaDeberiaDescontarse20PorcientoDeMovimientosAJugador() {
        Sorpresa desfavorable = new Desfavorable();
        Ubicacion ubicacion = (new Ubicacion(fila, columna));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador = new Jugador(nombre, camioneta);
        int cantidadMovimientosHechos = 10;
        int cantidadMovimientosEsperados = (int) (cantidadMovimientosHechos * 1.25);

        jugador.incrementarMovimientos(cantidadMovimientosHechos);
        camioneta.atravesar(desfavorable, jugador);

        assertTrue(jugador.verificarMovimiento(cantidadMovimientosEsperados));
    }
}
