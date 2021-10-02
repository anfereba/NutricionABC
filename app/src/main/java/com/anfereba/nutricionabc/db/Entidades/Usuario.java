package com.anfereba.nutricionabc.db.Entidades;

import android.graphics.Bitmap;

public class Usuario {

    private Integer idUsuario;
    private Integer idPerfilSistema;
    private String Nombres;
    private String Apellidos;
    private String FechaNacimiento;
    private String Correo;
    private String Password;
    private String Direccion;
    private String Ciudad;
    private String Telefono;
    private String FechaCreacion;
    private Bitmap FotoPerfil;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, Integer idPerfilSistema, String nombres, String apellidos, String fechaNacimiento, String correo, String password, String direccion, String ciudad, String telefono, String fechaCreacion, Bitmap fotoPerfil) {
        this.idUsuario = idUsuario;
        this.idPerfilSistema = idPerfilSistema;
        Nombres = nombres;
        Apellidos = apellidos;
        FechaNacimiento = fechaNacimiento;
        Correo = correo;
        Password = password;
        Direccion = direccion;
        Ciudad = ciudad;
        Telefono = telefono;
        FechaCreacion = fechaCreacion;
        FotoPerfil = fotoPerfil;
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

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public Bitmap getFotoPerfil() {
        return FotoPerfil;
    }

    public void setFotoPerfil(Bitmap fotoPerfil) {
        FotoPerfil = fotoPerfil;
    }
}

