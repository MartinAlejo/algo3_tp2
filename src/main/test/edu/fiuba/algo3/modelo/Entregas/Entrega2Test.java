package edu.fiuba.algo3.modelo.Entregas;


import edu.fiuba.algo3.modelo.Direccion.DireccionAbajo;
import edu.fiuba.algo3.modelo.Direccion.DireccionDerecha;
import edu.fiuba.algo3.modelo.General.*;

import edu.fiuba.algo3.modelo.Obstaculos.Obstaculo;
import edu.fiuba.algo3.modelo.Obstaculos.Piquete;

import edu.fiuba.algo3.modelo.Obstaculos.Pozo;
import edu.fiuba.algo3.modelo.Sorpresas.CambioVehiculo;
import edu.fiuba.algo3.modelo.Sorpresas.Desfavorable;
import edu.fiuba.algo3.modelo.Sorpresas.Favorable;
import edu.fiuba.algo3.modelo.Sorpresas.Sorpresa;
import edu.fiuba.algo3.modelo.Vehiculo.Auto;
import edu.fiuba.algo3.modelo.Vehiculo.Camioneta;
import edu.fiuba.algo3.modelo.Vehiculo.Moto;
import edu.fiuba.algo3.modelo.Vehiculo.Vehiculo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Entrega2Test {
    private String nombre = "Tomas";
    private int fila = 1;
    private int columna = 1;
    private Casillero casilleroInicial = new Casillero(fila, columna);
    private int totalFilas = 8;
    private int totalColumnas = 8;

    @Test
    public void UnaMotoSeMuevePorLaCiudad12VecesYSeEncuentraUnaSorpresaFavorableDeberiaTener10Movimientos() {
        Vehiculo moto = new Moto(casilleroInicial);
        Jugador jugador1 = new Jugador(nombre, moto);
        List<Jugador> jugadores = new ArrayList<Jugador>() {{ add(jugador1); }};

        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego juego = new Juego(jugadores);
        Sorpresa favorable = new Favorable();

        for (int i = 0; i < 6; i++) {
            juego.moverVehiculo(new DireccionDerecha());
            juego.moverVehiculo(new DireccionAbajo());
        }
        // Realizo 12 movimientos

        moto.recibe(favorable);

        int movimientosEsperados = 10;

        assertTrue(juego.verificarMovJugadorActivo(movimientosEsperados));
    }

    @Test
    public void UnaMotoSeMuevePorLaCiudad12VecesYSeEncuentraUnaSorpresaDesfavorableDeberiaTener14Movimientos() {
        Vehiculo moto = new Moto(casilleroInicial);
        Jugador jugador1 = new Jugador(nombre, moto);
        List<Jugador> jugadores = new ArrayList<Jugador>() {{ add(jugador1); }};

        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego juego = new Juego(jugadores);
        Sorpresa desfavorable = new Desfavorable();

        for (int i = 0; i < 6; i++) {
            juego.moverVehiculo(new DireccionDerecha());
            juego.moverVehiculo(new DireccionAbajo());
        }
        // Realizo 12 movimientos

        moto.recibe(desfavorable);

        int movimientosEsperados = 15;

        assertTrue(juego.verificarMovJugadorActivo(movimientosEsperados));
    }

    @Test
    public void UnaMotoSeMuevePorLaCiudad4VecesYSeEncuentraUnaSorpresaCambioDeVehiculoDeberiaConvertirseEnAuto() {
        Vehiculo vehiculo = new Moto(casilleroInicial);
        Jugador jugador1 = new Jugador(nombre, vehiculo);
        List<Jugador> jugadores = new ArrayList<Jugador>() {{ add(jugador1); }};

        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego juego = new Juego(jugadores);
        Sorpresa cambioVehiculo = new CambioVehiculo();

        for (int i = 0; i < 3; i++) {
            juego.moverVehiculo(new DireccionDerecha());
        }
        juego.moverVehiculo(new DireccionAbajo());


        vehiculo.recibe(cambioVehiculo);
        jugador1.cambiarVehiculo(vehiculo.cambio());

        Vehiculo vehiculoEsperado = new Auto(casilleroInicial);

        assertTrue(jugador1.mismoVehiculo(vehiculoEsperado));
    }

   @Test
    public void UnaCamionetaSeEncuentraConSorpresaCambioDeVehiculoYUnPiqueteDeberiaPoderPasarElPiqueteYTener6Movimientos(){
        Vehiculo vehiculo = new Camioneta(casilleroInicial);
        Jugador jugador1 = new Jugador(nombre, vehiculo);
        List<Jugador> jugadores = new ArrayList<Jugador>() {{ add(jugador1); }};

        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego juego = new Juego(jugadores);
        Sorpresa cambioVehiculo = new CambioVehiculo();
        Obstaculo piquete = new Piquete();

        for (int i = 0; i < 3; i++) {
            juego.moverVehiculo(new DireccionDerecha());
        }
        juego.moverVehiculo(new DireccionAbajo());


       vehiculo.recibe(cambioVehiculo);
       jugador1.cambiarVehiculo(vehiculo.cambio());
       jugador1.obtenerVehiculo().recibe(piquete);

       int movimientosEsperados = 6;

       int fila = 2;
       int columna = 4;

       Vehiculo vehiculoEsperado = new Moto(new Casillero(fila,columna));

       assertTrue(jugador1.mismoVehiculo(vehiculoEsperado));
       assertTrue(juego.verificarMovJugadorActivo(movimientosEsperados));
    }


    @Test
    public void UnaCamionetaSeMueve4VecesPorLaCiudadYSeEncuentra4VecesConPozoDeberiaTener7Movimientos(){
        Vehiculo vehiculo = new Camioneta(casilleroInicial);
        Jugador jugador1 = new Jugador(nombre, vehiculo);
        List<Jugador> jugadores = new ArrayList<Jugador>() {{ add(jugador1); }};

        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego juego = new Juego(jugadores);
        Obstaculo pozo = new Pozo();

        for (int i = 0; i < 3; i++) {
            juego.moverVehiculo(new DireccionDerecha());
        }
        juego.moverVehiculo(new DireccionAbajo());

        for(int i=0; i<4; i++){
            vehiculo.recibe(pozo);
        }

        int movimientosEsperados = 7;

        assertTrue(juego.verificarMovJugadorActivo(movimientosEsperados));
    }
}
