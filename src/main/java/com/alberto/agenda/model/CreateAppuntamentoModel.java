package com.alberto.agenda.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class CreateAppuntamentoModel {
    @NotEmpty(message = "PersonaIds must not be empty!")
    List<Long> personaIds;
    @NotBlank(message = "Descrizione must not be blank!")
    private String descrizione;
    @NotNull(message = "Data inizio must not be null!")
    private Date dataInizio;
    @NotNull(message = "Data fine must not be null!")
    private Date dataFine;

    public List<Long> getPersonaIds() {
        return personaIds;
    }

    public void setPersonaIds(List<Long> personaIds) {
        this.personaIds = personaIds;
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
}
