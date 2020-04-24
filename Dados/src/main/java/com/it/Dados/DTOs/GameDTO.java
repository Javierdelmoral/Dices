package com.it.Dados.DTOs;

import com.it.Dados.domain.Game;
import com.it.Dados.domain.Player;

public class GameDTO {

	private Integer idGame;
	private Integer idPlayer;
	private Integer valueDice1;
	private Integer valueDice2;
	private boolean won;
	private int totalDiceRolls;
	private Double successRate;
	
//	public GameDTO(Game game, Player player) {
//		super();
//		this.idGame = game.getId();
//		this.idPlayer = player.getId();
//		this.playerName = player.getName();
//		this.valueDice1 = game.getDice1();
//		this.valueDice2 = game.getDice2();
//		this.won = game.getWon();
//		this.totalDiceRolls = player.getTotalDiceRolls();
//		this.successRate = player.getSuccessRate();
//	}

	public Integer getIdGame() {
		return idGame;
	}

	public void setIdGame(Integer idGame) {
		this.idGame = idGame;
	}

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Integer getValueDice1() {
		return valueDice1;
	}

	public void setValueDice1(Integer valueDice1) {
		this.valueDice1 = valueDice1;
	}

	public Integer getValueDice2() {
		return valueDice2;
	}

	public void setValueDice2(Integer valueDice2) {
		this.valueDice2 = valueDice2;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public int getTotalDiceRolls() {
		return totalDiceRolls;
	}

	public void setTotalDiceRolls(int totalDiceRolls) {
		this.totalDiceRolls = totalDiceRolls;
	}

	public Double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}

	@Override
	public String toString() {
		return "\n" + "GameDTO " + "\n" + "[idGame=" + idGame + ", idPlayer=" + idPlayer + ", valueDice1="
				+ valueDice1 + ", valueDice2=" + valueDice2 + ", won=" + won + ", totalDiceRolls=" + totalDiceRolls
				+ ", successRate=" + successRate + "]";
	}
	
	
}
