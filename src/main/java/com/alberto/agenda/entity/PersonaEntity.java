package com.alberto.agenda.entity;

import com.alberto.agenda.model.PersonaInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "persona")
    @JsonIgnoreProperties("persona")
    private List<TelefonoEntity> telefoni;
    @OneToMany(mappedBy = "persona")
    @JsonIgnoreProperties("persona")
    private List<PromemoriaEntity> promemoria;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable
    private List<AppuntamentoEntity> appuntamenti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<AppuntamentoEntity> getAppuntamenti() {
        return appuntamenti;
    }

    public void setAppuntamenti(List<AppuntamentoEntity> appuntamenti) {
        this.appuntamenti = appuntamenti;
    }

    public PersonaInfo extractInfo() {
        PersonaInfo personaInfo = new PersonaInfo();
        personaInfo.setCognome(cognome);
        personaInfo.setNome(nome);
        personaInfo.setEmail(email);
        personaInfo.setId(id);
        personaInfo.setUsername(username);
        return personaInfo;
    }
}
