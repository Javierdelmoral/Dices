package com.it.Dados.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_player")
    private Integer idPlayer;
    private String name;
    private LocalDateTime registerDate;
    private float successRate;

    public Player() {
    }

    public Player(Integer idPlayer, String name, LocalDateTime registerDate, float successRate) {
        this.idPlayer = idPlayer;
        this.name = name;        
        this.registerDate = registerDate;
        this.successRate = successRate;
    }

    public Integer getId() {
        return idPlayer;
    }

    public void setId(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return name.toLowerCase();
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public float getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }
    
    
    
}
