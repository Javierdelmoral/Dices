package com.it.Dados.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE/* IDENTITY */) // PROBAR ESE IDENTITY!
	@JoinColumn(name = "id_player")
	private Integer idPlayer;
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime registerDate;
	@JoinColumn(name = "success_rate")
	private double successRate;
	@JoinColumn(name = "total_dice_rolls")
	private int totalDiceRolls;
	private int gamesWon;

	public Player() {
	}

	public Player(Integer idPlayer, String name, LocalDateTime registerDate, double successRate) {
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

	public double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getTotalDiceRolls() {
		return totalDiceRolls;
	}

	public void setTotalDiceRolls(int totalDiceRolls) {
		this.totalDiceRolls = totalDiceRolls;
	}

}
