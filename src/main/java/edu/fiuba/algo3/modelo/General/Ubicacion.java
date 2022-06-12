package edu.fiuba.algo3.modelo.General;

public class Ubicacion {
    private int fila;
    private int columna;

    public Ubicacion(int fila, int columna) {
    	this.fila = fila;
    	this.columna = columna;
    }

	public boolean equals(Ubicacion ubicacion){
		return (ubicacion.fila == this.fila & ubicacion.columna == this.columna);
	}

	public int fila() {
		return this.fila;
	}

	public int columna() {
		return this.columna;
	}

	public void incrementarFila () {
		this.fila += 1;
	}
	public void incrementarColumna () {
		this.columna += 1;
	}

	public void disminuirFila () {
		this.fila -= 1;
	}

	public void disminuirColumna () {
		this.columna -= 1;
	}

	public boolean noEstaVacia(){
		return (this != null);
	}

	}

