package com.alberto.agenda.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PromemoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private PersonaEntity persona;
    private String descrizione;
    private Date dataInizio;
    private Date dataFine;
    private String ricorrenza;

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
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

    public String getRicorrenza() {
        return ricorrenza;
    }

    public void setRicorrenza(String ricorrenza) {
        this.ricorrenza = ricorrenza;
    }
}
