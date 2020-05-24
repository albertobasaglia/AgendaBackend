package com.alberto.agenda.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class PromemoriaModel {
    @NotBlank(message = "Descrizione must not be blank!")
    private String descrizione;
    @NotNull(message = "Data inizio must not be blank!")
    private Date dataInizio;
    @NotNull(message = "Data fine must not be blank!")
    private Date dataFine;
    @NotBlank(message = "Ricorrenza must not be blank!")
    private String ricorrenza;

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
        if(ricorrenza.equals("settimanale")||ricorrenza.equals("mensile")||ricorrenza.equals("annuale")) {
            this.ricorrenza = ricorrenza;
        } else {
            this.ricorrenza = "nessuna";
        }
    }
}
