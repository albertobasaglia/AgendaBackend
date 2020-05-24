package com.alberto.agenda.model;

import com.alberto.agenda.repository.TelefonoRepository;

public class TelefonoModel {
    private String numero;
    public TelefonoModel(){}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
