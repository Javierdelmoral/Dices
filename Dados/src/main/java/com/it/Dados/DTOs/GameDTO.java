package com.it.Dados.DTOs;

import com.it.Dados.domain.Game;
import com.it.Dados.domain.Player;

public class GameDTO {

	private Integer idGame;
	private Integer idPlayer;
	private Integer valueDice1;
	private Integer valueDice2;
	private Integer valueDice3;
	private Integer valueDice4;
	private Integer valueDice5;
	private Integer valueDice6;
	private boolean won;
	private int totalDiceRolls;
	private Double successRate;

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
	
	public Integer getValueDice3() {
		return valueDice3;
	}

	public void setValueDice3(Integer valueDice3) {
		this.valueDice3 = valueDice3;
	}

	public Integer getValueDice4() {
		return valueDice4;
	}

	public void setValueDice4(Integer valueDice4) {
		this.valueDice4 = valueDice4;
	}

	public Integer getValueDice5() {
		return valueDice5;
	}

	public void setValueDice5(Integer valueDice5) {
		this.valueDice5 = valueDice5;
	}

	public Integer getValueDice6() {
		return valueDice6;
	}

	public void setValueDice6(Integer valueDice6) {
		this.valueDice6 = valueDice6;
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
		return "\n" + "GameDTO " + "\n" + "[idGame=" + idGame + ", idPlayer=" + idPlayer + ", valueDice1=" + valueDice1 + ", valueDice2="
				+ valueDice2 + ", valueDice3=" + valueDice3 + ", valueDice4=" + valueDice4 + ", valueDice5="
				+ valueDice5 + ", valueDice6=" + valueDice6 + ", won=" + won + ", totalDiceRolls=" + totalDiceRolls
				+ ", successRate=" + successRate + "]";
	}
	
//	@Override
//	public String toString() {
//		return "\n" + "GameDTO " + "\n" + "[idGame=" + idGame + ", idPlayer=" + idPlayer + ", valueDice1="
//				+ valueDice1 + ", valueDice2=" + valueDice2 + ", won=" + won + ", totalDiceRolls=" + totalDiceRolls
//				+ ", successRate=" + successRate + "]";
//	}


}
