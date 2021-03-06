package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Direccion.DireccionAbajo;
import edu.fiuba.algo3.modelo.Direccion.DireccionArriba;
import edu.fiuba.algo3.modelo.Direccion.DireccionDerecha;
import edu.fiuba.algo3.modelo.Direccion.DireccionIzquierda;
import edu.fiuba.algo3.modelo.General.*;
import edu.fiuba.algo3.modelo.Math.Random;
import edu.fiuba.algo3.modelo.Meta.Meta;
import edu.fiuba.algo3.modelo.Meta.MetaFinal;
import edu.fiuba.algo3.modelo.Obstaculos.Obstaculo;
import edu.fiuba.algo3.modelo.Obstaculos.Piquete;
import edu.fiuba.algo3.modelo.Obstaculos.Policia;
import edu.fiuba.algo3.modelo.Obstaculos.Pozo;
import edu.fiuba.algo3.modelo.Sorpresas.CambioVehiculo;
import edu.fiuba.algo3.modelo.Sorpresas.Desfavorable;
import edu.fiuba.algo3.modelo.Sorpresas.Favorable;
import edu.fiuba.algo3.modelo.Vehiculo.Auto;
import edu.fiuba.algo3.modelo.Vehiculo.Camioneta;
import edu.fiuba.algo3.modelo.Vehiculo.Moto;
import edu.fiuba.algo3.modelo.Vehiculo.Vehiculo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneralTest {
    private String nombre = "Martin";
    private int totalFilas = 20;
    private int totalColumnas = 20;
    private int filaInicial = 2;
    private int columnaInicial = 2;

    @Test
    public void seAgregaUnPozoAlEscenarioDelJuegoYUnAutoSeMueveYPasaPorElDeberiaTener4Movimientos() {
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador1 = new Jugador(nombre, auto);
        List<Jugador> jugadores = new ArrayList<>() {{ add(jugador1); }};
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Pozo pozo = new Pozo();
        Ubicacion ubicacionPozo = new Ubicacion(2,3);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPozo, pozo);

        // Act
        Juego.getInstance().moverVehiculo(new DireccionDerecha());

        // Assert
        assertTrue(jugador1.verificarMovimientos(4));
    }

    @Test
    public void seAgregaUnaSorpresaFavorableAlEscenarioDelJuegoYUnAutoSeMueveYPasaPorEstaDeberiaTener10Movimientos() {
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador1 = new Jugador(nombre, auto);
        List<Jugador> jugadores = new ArrayList<>() {{ add(jugador1); }};
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        // Act
        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa sorpresaFavorable = new Favorable();
        Ubicacion ubicacionSorpresa = new Ubicacion(2,25);
        Escenario.getInstance().agregarSorpresaEn(ubicacionSorpresa, sorpresaFavorable);


        for (int i = 0; i < 12; i++) {
            Juego.getInstance().moverVehiculo(new DireccionDerecha()); // Me muevo 12 veces a la derecha
        }

        // Al momento de pasar por la sorpresa, tiene 12 movimientos, luego de aplicarse tiene 9

        // Assert
        assertTrue(auto.verificarUbicacion(new Ubicacion(2,26)));
        assertTrue(jugador1.verificarMovimientos(9));
    }

    @Test
    public void seAgregaUnaSorpresaDesfavorableAlEscenarioDelJuegoYUnAutoSeMueveYPasaPorEstaDeberiaTener10Movimientos() {
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador1 = new Jugador(nombre, auto);
        List<Jugador> jugadores = new ArrayList<>() {{ add(jugador1); }};
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        // Act
        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa sorpresaDesfavorable = new Desfavorable();
        Ubicacion ubicacionSorpresa = new Ubicacion(2,25);
        Escenario.getInstance().agregarSorpresaEn(ubicacionSorpresa, sorpresaDesfavorable);

        for (int i = 0; i < 12; i++) {
            Juego.getInstance().moverVehiculo(new DireccionDerecha()); // Me muevo 12 veces a la derecha
        }

        // Assert
        assertTrue(auto.verificarUbicacion(new Ubicacion(2,26)));
        assertTrue(jugador1.verificarMovimientos(15));
    }

    @Test
    public void SeAgregaUnaSorpresaDesvaforableYUnPozoAlEscenarioYCamionetaSeMueveDeberiaTener13(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador0 = new Jugador(nombre, camioneta);
        int cantMovimientos = 13;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa sorpresaDes = new Desfavorable();
        Ubicacion ubicacionSorpresa = new Ubicacion(2,15);
        Escenario.getInstance().agregarSorpresaEn(ubicacionSorpresa, sorpresaDes);
        Obstaculo pozo = new Pozo();
        Ubicacion ubicacionObstaculo = new Ubicacion(2,7);
        Escenario.getInstance().agregarObstaculoEn(ubicacionObstaculo, pozo);

        for(int i=0; i<12; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
    }

    @Test
    public void SeAgrega2SorpresasDesfavorablesYAutoPasaPorLasDosSuMovimientoDeberiaSer11(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador0 = new Jugador(nombre, auto);
        int cantMovimientos = 11;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa sorpresaDes1 = new Desfavorable();
        Ubicacion ubicacionSorpresa1 = new Ubicacion(2,17);
        Escenario.getInstance().agregarSorpresaEn(ubicacionSorpresa1, sorpresaDes1);
        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa sorpresaDes2 = new Desfavorable();
        Ubicacion ubicacionSorpresa2 = new Ubicacion(2,13);
        Escenario.getInstance().agregarSorpresaEn(ubicacionSorpresa2, sorpresaDes2);

        for(int i=0; i<8; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        assertTrue(auto.verificarUbicacion(new Ubicacion(2,18)));
        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
    }

    @Test
    public void unAutoPasaPorUnCasilleroQueTieneUnPozoYUnaSorpresaDesfavorableYSeAplicaPrimeroLaSorpresa(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador = new Jugador(nombre, auto);
        int movimientosEsperados = 14;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa sorpresaDesfavorable = new Desfavorable();
        Ubicacion ubicacionSorpresa = new Ubicacion(2,19);
        Escenario.getInstance().agregarSorpresaEn(ubicacionSorpresa, sorpresaDesfavorable);
        Pozo pozo = new Pozo();
        Ubicacion ubicacionPozo = new Ubicacion(2,19);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPozo, pozo);

        for(int i=0; i<9; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        // 2-18 8 mov
        // 2-19 9 mov (se encuentra con la sorpresa y el obstaculo)
        // 2-20 (9 mov + 9 mov * 0.25 + 3) = 14
        // Notamos que primero se aplico la sorpresa y despues el obstaculo, sino los movimientos serian 15

        assertTrue(auto.verificarUbicacion(new Ubicacion(2,20)));
        assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }

    @Test
    public void unAutoIntentaPasarPorUnCasilleroQueTieneUnPiqueteNoDeberiaPoderPasar(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador = new Jugador(nombre, auto);
        int movimientosEsperados = 9;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Piquete piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(2,19);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        for(int i=0; i<9; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        assertTrue(auto.verificarUbicacion(new Ubicacion(2,18)));
        assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }

    @Test
    public void unaCamionetaIntentaPasarPorUnCasilleroQueTieneUnPiqueteNoDeberiaPoderPasar(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador = new Jugador(nombre, camioneta);
        int movimientosEsperados = 9;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Piquete piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(2,19);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        for(int i=0; i<9; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        assertTrue(camioneta.verificarUbicacion(new Ubicacion(2,18)));
        assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }

    @Test
    public void unaMotoIntentaPasarPorUnCasilleroQueTieneUnPiqueteDeberiaPoderPasarYRecibirPenalizacion(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador = new Jugador(nombre, moto);
        int movimientosEsperados = 11;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Piquete piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(2,19);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        for(int i=0; i<9; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        assertTrue(moto.verificarUbicacion(new Ubicacion(2,20)));
        assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }

    @Test
    public void unaMotoIntentaPasarPorUnCasilleroDelBordeQueTieneUnPiqueteNoDeberiaMoverse(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador = new Jugador(nombre, moto);
        //int movimientosEsperados = 11;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Piquete piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(1,2);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        Juego.getInstance().moverVehiculo(new DireccionArriba());

        assertTrue(moto.verificarUbicacion(new Ubicacion(2,2)));
        //assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }
/*
    @Test
    public void unaCamionetaIntentaPasarPorUnCasilleroDelBordeQueTieneUnPiqueteNoDeberiaMoverse(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador = new Jugador(nombre, camioneta);
        //int movimientosEsperados = 11;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Piquete piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(1,2);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        Juego.getInstance().moverVehiculo(new DireccionArriba());

        assertTrue(camioneta.verificarUbicacion(new Ubicacion(2,2)));
        //assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }

    @Test
    public void unAutoIntentaPasarPorUnCasilleroDelBordeQueTieneUnPiqueteNoDeberiaMoverse(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador = new Jugador(nombre, auto);
        //int movimientosEsperados = 11;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Piquete piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(1,2);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        Juego.getInstance().moverVehiculo(new DireccionArriba());

        assertTrue(auto.verificarUbicacion(new Ubicacion(2,2)));
        //assertTrue(jugador.verificarMovimientos(movimientosEsperados));
    }
*/
    @Test
    public void unaMotoPasaPorUnCasilleroQueTieneUnControlPolicial(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador = new Jugador(nombre, moto);
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Policia policia = new Policia(new Random());
        Ubicacion ubicacionPolicia = new Ubicacion(2,19);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPolicia, policia);

        // Act
        for(int i=0; i<9; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha());
        }

        // Assert (los movimientos van a ser 9 o 12 dependiendo la probabilidad)
        assertTrue(moto.verificarUbicacion(new Ubicacion(2,20)));
        assertTrue(jugador.verificarMovimientos(9) || jugador.verificarMovimientos(12));
    }

    @Test
    public void unaMotoPasaPorUnCasilleroDelBordeSuperiorNoDeberiaPoderPasarPeroSiAgarrarElObstaculo(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador = new Jugador(nombre, moto);
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Pozo pozo = new Pozo();
        Ubicacion ubicacionPolicia = new Ubicacion(1,2);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPolicia, pozo);

        // Act
        Juego.getInstance().moverVehiculo(new DireccionArriba());

        // Assert
        assertTrue(moto.verificarUbicacion(new Ubicacion(2,2)));
        assertTrue(jugador.verificarMovimientos(4));
    }

    @Test
    public void unAutoHaceVariosMovimientosYPasaPorUnCambioDeVehiculoYUnPiqueteDeberiaCambiarseACamionetaYNoPoderPasar(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Auto(ubicacion);
        Jugador jugador0 = new Jugador(nombre, auto);
        int cantMovimientos = 4;
        Ubicacion ubicacionEsperada = new Ubicacion(2,4);
        List<Jugador> jugadores = new ArrayList<>() {
            { add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa cambioVehiculo = new CambioVehiculo();
        Ubicacion ubicacionCambioV = new Ubicacion(2, 3);
        Escenario.getInstance().agregarSorpresaEn(ubicacionCambioV, cambioVehiculo);

        Obstaculo piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(2, 3);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        // Act
        Juego.getInstance().moverVehiculo(new DireccionAbajo());
        Juego.getInstance().moverVehiculo(new DireccionDerecha());
        Juego.getInstance().moverVehiculo(new DireccionArriba()); // Hasta aca la ubicacion es (2,4)
        Juego.getInstance().moverVehiculo(new DireccionIzquierda()); // Se deberia encontrar con cambio de vehiculo y piquete

        // Assert
        assertTrue(jugador0.verificarUbicacion(ubicacionEsperada));
        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
        assertTrue(jugador0.mismoVehiculo(new Camioneta(ubicacionEsperada)));
    }

    @Test
    public void unaMotoHaceVariosMovimientosYPasaPorUnCambioDeVehiculoYUnPiqueteDeberiaCambiarseAAutoYNoPoderPasar(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador0 = new Jugador(nombre, moto);
        int cantMovimientos = 4;
        Ubicacion ubicacionEsperada = new Ubicacion(4,2);
        List<Jugador> jugadores = new ArrayList<>() {
            { add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa cambioVehiculo = new CambioVehiculo();
        Ubicacion ubicacionCambioV = new Ubicacion(3, 2);
        Escenario.getInstance().agregarSorpresaEn(ubicacionCambioV, cambioVehiculo);

        Obstaculo piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(3, 2);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        // Act
        Juego.getInstance().moverVehiculo(new DireccionDerecha());
        Juego.getInstance().moverVehiculo(new DireccionAbajo());
        Juego.getInstance().moverVehiculo(new DireccionIzquierda()); // Hasta aca la ubicacion es (4,2)
        Juego.getInstance().moverVehiculo(new DireccionArriba()); // Se deberia encontrar con cambio de vehiculo y piquete

        // Assert
        assertTrue(jugador0.verificarUbicacion(ubicacionEsperada));
        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
        assertTrue(jugador0.mismoVehiculo(new Auto(ubicacionEsperada)));
    }

    @Test
    public void unaCamionetaSeMueveParaAbajoYSeEncuentraUnPiqueteNoPuedePasar(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador0 = new Jugador(nombre, camioneta);
        int cantMovimientos = 1;
        Ubicacion ubicacionEsperada = new Ubicacion(2,2);
        List<Jugador> jugadores = new ArrayList<>() {
            { add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        Obstaculo piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(3, 2);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        // Act
        Juego.getInstance().moverVehiculo(new DireccionAbajo());

        // Assert
        assertTrue(jugador0.verificarUbicacion(ubicacionEsperada));
        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
    }

    @Test
    public void unaMotoPasaPorUnaMetaSeLlamaAlFinalizarDeJuegoYSeAgregaLaPuntuacionDelJugador(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo moto = new Moto(ubicacion);
        Jugador jugador = new Jugador(nombre, moto);
        int movimientosEsperados = 9;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);
        Ranking.resetInstance();

        Meta meta = new MetaFinal();
        Ubicacion ubicacionMeta = new Ubicacion(2,19);
        Escenario.getInstance().agregarMetaEn(ubicacionMeta, meta);

        // Act
        for(int i=0; i<8; i++){
            Juego.getInstance().moverVehiculo(new DireccionDerecha()); // 8 movimientos a la derecha
        }

        // Si se mueve una vez mas a la derecha, pasa por la meta
        Juego.getInstance().moverVehiculo(new DireccionDerecha());

        // Assert 1
        assertTrue(moto.verificarUbicacion(new Ubicacion(2,20)));
        assertTrue(jugador.verificarMovimientos(movimientosEsperados));

        // Assert 2
        List<Puntaje> puntajes = Ranking.getInstance().puntajes();
        Puntaje puntajeJugador = puntajes.get(0);

        assertEquals(puntajes.size(), 1);
        assertEquals(puntajeJugador.obtenerNombreJugador(), nombre);
        assertEquals(puntajeJugador.obtenerPuntuacion(), movimientosEsperados);

    }

    @Test
    public void UnaCamionetaPasaPorUnCambioDeVehiculoYJustoDespuesPorUnPiqueteDeberiaPoderPasarloYTener2Movimientos() {
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador0 = new Jugador(nombre, camioneta);
        int cantMovimientos = 3;
        List<Jugador> jugadores = new ArrayList<>() {
            { add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa cambioVehiculo = new CambioVehiculo();
        Ubicacion ubicacionCambioV = new Ubicacion(2, 3);
        Escenario.getInstance().agregarSorpresaEn(ubicacionCambioV, cambioVehiculo);
        Obstaculo piquete = new Piquete();
        Ubicacion ubicacionPiquete = new Ubicacion(2, 3);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPiquete, piquete);

        Juego.getInstance().moverVehiculo(new DireccionDerecha());

        Ubicacion nuevaUbicacion = new Ubicacion(2,4);

        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
        assertTrue(jugador0.verificarUbicacion(nuevaUbicacion));
    }

    @Test
    public void UnaCamionetaPasa2VecesPorElMismoCambioDeVehiculoDeberiaCambiarsePrimeroAMotoYLuegoAAuto() {
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo camioneta = new Camioneta(ubicacion);
        Jugador jugador0 = new Jugador(nombre, camioneta);
        int cantMovimientos = 2;
        List<Jugador> jugadores = new ArrayList<>() {
            { add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa cambioVehiculo = new CambioVehiculo();
        Ubicacion ubicacionCambioV = new Ubicacion(3, 2);
        Escenario.getInstance().agregarSorpresaEn(ubicacionCambioV, cambioVehiculo);

        // Act 1
        Juego.getInstance().moverVehiculo(new DireccionAbajo());
        Ubicacion ubicacionEsperada = new Ubicacion(4,2);

        // Assert 1
        assertTrue(jugador0.verificarUbicacion(ubicacionEsperada));
        assertTrue(jugador0.mismoVehiculo(new Moto(ubicacionEsperada)));

        // Act 2
        Juego.getInstance().moverVehiculo(new DireccionArriba());
        ubicacionEsperada = new Ubicacion(2,2);

        // Assert 2
        assertTrue(jugador0.verificarUbicacion(ubicacionEsperada));
        assertTrue(jugador0.mismoVehiculo(new Auto(ubicacionEsperada)));
    }

    @Test
    public void SeAgregaUnCambioVehiculoYUnPozoYAutoPasaPorLosDos(){
        Ubicacion ubicacion = (new Ubicacion(filaInicial, columnaInicial));
        Vehiculo auto = new Camioneta(ubicacion);
        Jugador jugador0 = new Jugador(nombre, auto);
        int cantMovimientos = 4;
        List<Jugador> jugadores = new ArrayList<>(){
            {add(jugador0);}
        };
        Escenario.resetInstance(totalFilas, totalColumnas);
        Juego.resetInstance(jugadores);

        edu.fiuba.algo3.modelo.Sorpresas.Sorpresa cambioVehiculo = new CambioVehiculo();
        Ubicacion ubicacionCambioV = new Ubicacion(2,3);
        Escenario.getInstance().agregarSorpresaEn(ubicacionCambioV, cambioVehiculo);
        Obstaculo pozo = new Pozo();
        Ubicacion ubicacionPozo = new Ubicacion(2,3);
        Escenario.getInstance().agregarObstaculoEn(ubicacionPozo, pozo);

        Juego.getInstance().moverVehiculo(new DireccionDerecha());

        assertTrue(jugador0.verificarMovimientos(cantMovimientos));
    }
}
