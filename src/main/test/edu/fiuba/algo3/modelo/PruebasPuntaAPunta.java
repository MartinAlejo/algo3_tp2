package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Direccion.DireccionAbajo;
import edu.fiuba.algo3.modelo.Direccion.DireccionDerecha;
import edu.fiuba.algo3.modelo.General.Escenario;
import edu.fiuba.algo3.modelo.General.Juego;
import edu.fiuba.algo3.modelo.General.Jugador;
import edu.fiuba.algo3.modelo.General.Ubicacion;
import edu.fiuba.algo3.modelo.Math.IMath;
import edu.fiuba.algo3.modelo.Meta.MetaFinal;
import edu.fiuba.algo3.modelo.Obstaculos.Piquete;
import edu.fiuba.algo3.modelo.Obstaculos.Policia;
import edu.fiuba.algo3.modelo.Obstaculos.Pozo;
import edu.fiuba.algo3.modelo.Sorpresas.CambioVehiculo;
import edu.fiuba.algo3.modelo.Sorpresas.Desfavorable;
import edu.fiuba.algo3.modelo.Sorpresas.Favorable;
import edu.fiuba.algo3.modelo.Vehiculo.Camioneta;
import edu.fiuba.algo3.modelo.Vehiculo.Moto;
import edu.fiuba.algo3.modelo.Vehiculo.Vehiculo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PruebasPuntaAPunta {
    private String nombre = "Martin";
    private String nombre2 = "Pepito";
    private int totalFilas = 4;
    private int totalColumnas = 4;
    private int filaInicial = 2;
    private int columnaInicial = 2;
    private int filaInicialJugador2 = 2;
    private int columnaInicialJugador2 = 4;

    private Ubicacion ubicacionJugador1 = new Ubicacion(filaInicial, columnaInicial);
    private Ubicacion ubicacionJugador2 = new Ubicacion(filaInicialJugador2, columnaInicialJugador2);



    @Test
    public void seJuegaUnaPartidaCompletaYganaElJugador1() {
        Escenario.resetInstance(totalFilas, totalColumnas);

        Vehiculo moto = new Moto(ubicacionJugador1);
        Jugador jugador1 = new Jugador(nombre, moto);

        Vehiculo camioneta = new Camioneta(ubicacionJugador2);
        Jugador jugador2 = new Jugador(nombre2, camioneta);

        int filaMeta = 9;
        int columnaMeta = 8;
        Ubicacion ubicacionMeta = new Ubicacion(filaMeta, columnaMeta);

        int filaPiquete1 = 3;
        int columnaPiquete1 = 2;

        Ubicacion ubicacionPiquete1 = new Ubicacion(filaPiquete1, columnaPiquete1);

        int filaPiquete2 = 3;
        int columnaPiquete2 = 4;

        Ubicacion ubicacionPiquete2 = new Ubicacion(filaPiquete2, columnaPiquete2);

        Escenario.getInstance().agregarMetaEn(ubicacionMeta, new MetaFinal());
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete1, new Piquete());
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete2, new Piquete());

        List<Jugador> jugadores = new ArrayList<>() {{
            add(jugador1);
            add(jugador2);
        }};
        Juego.resetInstance(jugadores);
        assertTrue(Juego.getInstance().verificarJugadorActivo(jugador1));
        int movEsperados1 = 0;
        int movEsperados2 = 0;
        assertTrue(Juego.getInstance().verificarMovJugadorActivo(movEsperados1));
        Juego.getInstance().moverVehiculo(new DireccionAbajo());
        movEsperados1 = 3;
        filaInicial = 4;
        columnaInicial = 2;
        ubicacionJugador1 = new Ubicacion(filaInicial,columnaInicial);

        assertTrue(Juego.getInstance().verificarJugadorActivo(jugador2));
        assertTrue(Juego.getInstance().verificarMovJugadorActivo(movEsperados2));
        Juego.getInstance().moverVehiculo(new DireccionAbajo());

        assertTrue(Juego.getInstance().verificarMovJugadorActivo(movEsperados1));
        assertTrue(Juego.getInstance().verificarUbicacionJugadorActivo(ubicacionJugador1));

    }

    @Test
    public void seJuegaUnaPartidaCompletaYganaElJugador2() {
        IMath mathMock = mock(IMath.class);
        when(mathMock.random()).thenReturn(0.1);
        Escenario.resetInstance(totalFilas, totalColumnas);
        Escenario.getInstance().agregarObstaculoEn(new Ubicacion(2,3), new Piquete());
        Escenario.getInstance().agregarSorpresaEn(new Ubicacion(2,3), new CambioVehiculo());
        Escenario.getInstance().agregarObstaculoEn(new Ubicacion(3,2), new Pozo());
        Escenario.getInstance().agregarObstaculoEn(new Ubicacion(3,4), new Pozo());
        Escenario.getInstance().agregarObstaculoEn(new Ubicacion(4,5), new Policia(mathMock));
        Escenario.getInstance().agregarSorpresaEn(new Ubicacion(7,6), new Favorable());
        Escenario.getInstance().agregarSorpresaEn(new Ubicacion(8,7), new Desfavorable());
        Escenario.getInstance().agregarMetaEn(new Ubicacion(8, 9), new MetaFinal());

        Vehiculo moto = new Moto(new Ubicacion(filaInicial, columnaInicial));
        Jugador jugador1 = new Jugador(nombre, moto);

        Vehiculo camioneta = new Camioneta(new Ubicacion(filaInicial, columnaInicial));
        Jugador jugador2 = new Jugador(nombre2, camioneta);
        List<Jugador> jugadores = new ArrayList<>() {{
            add(jugador1);
            add(jugador2);
        }};
        Juego.resetInstance(jugadores);

        int movEsperadosJugador1 = 0;
        int movEsperadosJugador2 = 0;

        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 2,2 Jugador 1
        movEsperadosJugador1 += 1;
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 2,4 Jugador 2
        movEsperadosJugador2 += 3;
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));

        Juego.getInstance().moverVehiculo(new DireccionAbajo()); // 4,2 Jugador 1
        movEsperadosJugador1 += 4;
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionAbajo()); // 4,4 Jugador 2
        movEsperadosJugador2 += 4;
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));

        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 4,4 Jugador 1
        movEsperadosJugador1 += 1;
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 4,6 Jugador 2
        movEsperadosJugador2 += 4;
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));

        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 4,6 Jugador 1
        movEsperadosJugador1 += 4;
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionAbajo()); // 6,6 Jugador 2
        movEsperadosJugador2 += 1;
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));

        Juego.getInstance().moverVehiculo(new DireccionAbajo()); // 6,6 Jugador 1
        movEsperadosJugador1 += 1;
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionAbajo()); // 8,6 Jugador 2
        movEsperadosJugador2++;
        movEsperadosJugador2 =  movEsperadosJugador2 - (movEsperadosJugador2 * 20 / 100);
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));

        Juego.getInstance().moverVehiculo(new DireccionAbajo()); // 8,6 Jugador 1
        movEsperadosJugador1++;
        movEsperadosJugador1 =  movEsperadosJugador1 - (movEsperadosJugador1 * 20 / 100);
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 8,8 Jugador 2
        movEsperadosJugador2++;
        movEsperadosJugador2 =  movEsperadosJugador2 + (movEsperadosJugador2 * 25 / 100);
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));

        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 8,8 Jugador 1
        movEsperadosJugador1++;
        movEsperadosJugador1 =  movEsperadosJugador1 + (movEsperadosJugador1 * 25 / 100);
        assertTrue(jugador1.verificarMovimientos(movEsperadosJugador1));
        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // FIN Jugador 2
        movEsperadosJugador2 += 1;
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador2));
        Juego.getInstance().moverVehiculo(new DireccionDerecha()); // FIN Jugador 1
        movEsperadosJugador1 += 1;
        assertTrue(jugador2.verificarMovimientos(movEsperadosJugador1));

    }
}

