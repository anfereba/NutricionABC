package com.anfereba.nutricionabc.db.Entidades;

public class Alimentos {
private Integer idAlimento;
private String NombreAlimento;

    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombreAlimento() {
        return NombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        NombreAlimento = nombreAlimento;
    }
}
