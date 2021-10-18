package com.anfereba.nutricionabc.db.Entidades;

public class CalificacionNutriologo {


    private Integer IdCalificacion;
    private Integer IdPadre;
    private Integer IdNutriologo;
    private double Puntuacion;
    private String Comentario;

    public CalificacionNutriologo(Integer idCalificacion, Integer idPadre, Integer idNutriologo, double puntuacion, String comentario) {
        IdCalificacion = idCalificacion;
        IdPadre = idPadre;
        IdNutriologo = idNutriologo;
        Puntuacion = puntuacion;
        Comentario = comentario;
    }

    public Integer getIdCalificacion() {
        return IdCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        IdCalificacion = idCalificacion;
    }

    public Integer getIdPadre() {
        return IdPadre;
    }

    public void setIdPadre(Integer idPadre) {
        IdPadre = idPadre;
    }

    public Integer getIdNutriologo() {
        return IdNutriologo;
    }

    public void setIdNutriologo(Integer idNutriologo) {
        IdNutriologo = idNutriologo;
    }

    public double getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        Puntuacion = puntuacion;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }
}
