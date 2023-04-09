package com.usuario.service.modelo;

public class Moto {
    private String Marca;
    private String Modelo;
    // Este atributo se agregó para usar Feign (despues de configuar)
    private int usuarioId;

    public Moto() {
        super();
    }

    public Moto(String marca, String modelo, int usuarioId) {
        Marca = marca;
        Modelo = modelo;
        this.usuarioId = usuarioId;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
