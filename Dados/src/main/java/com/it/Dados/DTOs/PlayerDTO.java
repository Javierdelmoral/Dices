package com.it.Dados.DTOs;

import java.time.LocalDateTime;

public class PlayerDTO {

	private Integer idPlayer;
	private String name;
	private LocalDateTime registerDate;
	private int totalDiceRolls;
	private int gamesWon;
	private Double successRate;

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public int getTotalDiceRolls() {
		return totalDiceRolls;
	}

	public void setTotalDiceRolls(int totalDiceRolls) {
		this.totalDiceRolls = totalDiceRolls;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public Double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}
}
