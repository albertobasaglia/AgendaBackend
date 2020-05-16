package com.alberto.agenda.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    @OneToMany(mappedBy = "persona")
    private List<TelefonoEntity> telefoni;
    @OneToMany(mappedBy = "persona")
    private List<PromemoriaEntity> promemoria;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<AppuntamentiEntity> appuntamenti;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TelefonoEntity> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(List<TelefonoEntity> telefoni) {
        this.telefoni = telefoni;
    }

    public List<PromemoriaEntity> getPromemoria() {
        return promemoria;
    }

    public void setPromemoria(List<PromemoriaEntity> promemoria) {
        this.promemoria = promemoria;
    }

    public List<AppuntamentiEntity> getAppuntamenti() {
        return appuntamenti;
    }

    public void setAppuntamenti(List<AppuntamentiEntity> appuntamenti) {
        this.appuntamenti = appuntamenti;
    }
}
