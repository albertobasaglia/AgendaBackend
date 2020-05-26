package com.alberto.agenda.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AppuntamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descrizione;

    private Date dataInizio;

    private Date dataFine;

    @ManyToMany(mappedBy = "appuntamenti", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("appuntamenti")
    private List<PersonaEntity> persone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public List<PersonaEntity> getPersone() {
        return persone;
    }

    public void setPersone(List<PersonaEntity> persone) {
        this.persone = persone;
    }
}
