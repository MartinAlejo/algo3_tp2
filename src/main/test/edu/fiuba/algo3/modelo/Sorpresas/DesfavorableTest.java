package edu.fiuba.algo3.modelo.Sorpresas;

import edu.fiuba.algo3.modelo.Efecto.Efecto;
import edu.fiuba.algo3.modelo.Efecto.EfectoGeneral;
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
        Efecto efecto = new EfectoGeneral();

        int cantidadMovimientosHechos = 10;
        int cantidadMovimientosEsperados = (int) (cantidadMovimientosHechos * 1.25);

        jugador.incrementarMovimientos(cantidadMovimientosHechos);
        efecto = moto.atravesar(desfavorable);
        efecto.aplicar(jugador);

        assertTrue(jugador.verificarMovimientos(cantidadMovimientosEsperados));
    }

    @Test
    public void unJugadorPasaPorUnaSorpresaDesfavorableConAutoDeberiaDescontarse20PorcientoDeMovimientosAJugador() {
        Sorpresa desfavorable = new Desfavorable();
        Ubicacion ubicacion = (new Ubicacion(fila, columna));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador = new Jugador(nombre, auto);
        Efecto efecto = new EfectoGeneral();

        int cantidadMovimientosHechos = 10;
        int cantidadMovimientosEsperados = (int) (cantidadMovimientosHechos * 1.25);

        jugador.incrementarMovimientos(cantidadMovimientosHechos);
        efecto = auto.atravesar(desfavorable);
        efecto.aplicar(jugador);

        assertTrue(jugador.verificarMovimientos(cantidadMovimientosEsperados));
    }

    @Test
    public void unJugadorPasaPorUnaSorpresaDesfavorableConCamionetaDeberiaDescontarse20PorcientoDeMovimientosAJugador() {
        Sorpresa desfavorable = new Desfavorable();
        Ubicacion ubicacion = (new Ubicacion(fila, columna));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador = new Jugador(nombre, camioneta);
        Efecto efecto = new EfectoGeneral();

        int cantidadMovimientosHechos = 10;
        int cantidadMovimientosEsperados = (int) (cantidadMovimientosHechos * 1.25);

        jugador.incrementarMovimientos(cantidadMovimientosHechos);
        efecto = camioneta.atravesar(desfavorable);
        efecto.aplicar(jugador);

        assertTrue(jugador.verificarMovimientos(cantidadMovimientosEsperados));
    }
}
