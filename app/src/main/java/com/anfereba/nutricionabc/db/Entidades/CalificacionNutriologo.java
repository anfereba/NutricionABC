package com.anfereba.nutricionabc.db.Entidades;

public class CalificacionNutriologo {


    private Integer idCalificacion;
    private Integer idUsuario;
    private Integer idPerfilSistema;
    private Integer rating;

    public String toString(){
        return getIdCalificacion() +" "+ getIdUsuario() +" "+ getIdPerfilSistema() + ""+ getRating() +" ";
    }

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPerfilSistema() {
        return idPerfilSistema;
    }

    public void setIdPerfilSistema(Integer idPerfilSistema) {
        this.idPerfilSistema = idPerfilSistema;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
