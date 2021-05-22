package es.backend.eltiempo.model;

public class Temperatura {

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }
    
    public String getFechatexto() {
        return fechatexto;
    }

    public void setFechatexto(String fechatexto) {
        this.fechatexto = fechatexto;
    }


    private String fechatexto;
    private int maxima;
    private int minima;

    public Temperatura(){

    }
    
    public Temperatura(String fechatexto,int max, int min){
        this.fechatexto = fechatexto;
        this.maxima = max;
        this.minima = min;
    }
}
