package com.anfereba.nutricionabc.db.Entidades;

public class PerfilSistema {

    private Integer idPerfilSistema;
    private String NombrePerfil;

    public PerfilSistema() {
    }

    public PerfilSistema(Integer idPerfilSistema, String nombrePerfil) {
        this.idPerfilSistema = idPerfilSistema;
        this.NombrePerfil = nombrePerfil;
    }

    public Integer getIdPerfilSistema() {
        return idPerfilSistema;
    }

    public void setIdPerfilSistema(Integer idPerfilSistema) {
        this.idPerfilSistema = idPerfilSistema;
    }

    public String getNombrePerfil() {
        return NombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        NombrePerfil = nombrePerfil;
    }
}
