package com.anfereba.nutricionabc.db.Entidades;

public class Alimentos {
private Integer idAlimento;
private byte[] FotoAlimento;
private Integer Calorias;
private String NombreAlimento;


    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public byte[] getFotoAlimento() {
        return FotoAlimento;
    }

    public void setFotoAlimento(byte[] fotoAlimento) {
        FotoAlimento = fotoAlimento;
    }

    public Integer getCalorias() {
        return Calorias;
    }

    public void setCalorias(Integer calorias) {
        Calorias = calorias;
    }

    public String getNombreAlimento() {
        return NombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        NombreAlimento = nombreAlimento;
    }
}
