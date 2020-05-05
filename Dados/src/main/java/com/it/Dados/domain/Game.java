package com.it.Dados.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JoinColumn(name = "id_game")
	private Integer idGame;
	@OnDelete(action = OnDeleteAction.CASCADE) 
	@JoinColumn(name = "id_player", nullable = false)
	private Integer idPlayer;
	@OnDelete(action = OnDeleteAction.CASCADE) 
	@ManyToOne
	private Player player;
	private Integer valueDice1;
	private Integer valueDice2;
	private Integer valueDice3;
	private Integer valueDice4;
	private Integer valueDice5;
	private Integer valueDice6;

	private boolean won;

	public Game() {
	}

	public Game(Integer idGame, Integer idPlayer, Integer valueDice1, Integer valueDice2, Integer valueDice3, Integer valueDice4, Integer valueDice5, Integer valueDice6, boolean won) {
		super();
		this.idGame = idGame;
		this.idPlayer = idPlayer;
		this.valueDice1 = valueDice1;
		this.valueDice2 = valueDice2;
		this.valueDice3 = valueDice3;
		this.valueDice4 = valueDice4;
		this.valueDice5 = valueDice5;
		this.valueDice6 = valueDice6;

		this.won = won;

	}

	public Integer getId() {
		return idGame;
	}

	public void setId(Integer idGame) {
		this.idGame = idGame;
	}

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Integer getDice1() {
		return valueDice1;
	}

	public void setDice1(Integer valueDice1) {
		this.valueDice1 = valueDice1;
	}

	public Integer getDice2() {
		return valueDice2;
	}

	public void setDice2(Integer valueDice2) {
		this.valueDice2 = valueDice2;
	}
	
	public Integer getDice3() {
		return valueDice3;
	}

	public void setDice3(Integer valueDice3) {
		this.valueDice3 = valueDice3;
	}
	
	public Integer getDice4() {
		return valueDice4;
	}

	public void setDice4(Integer valueDice4) {
		this.valueDice4 = valueDice4;
	}
	
	public Integer getDice5() {
		return valueDice5;
	}

	public void setDice5(Integer valueDice5) {
		this.valueDice5 = valueDice5;
	}
	
	public Integer getDice6() {
		return valueDice6;
	}

	public void setDice6(Integer valueDice6) {
		this.valueDice6 = valueDice6;
	}

	public boolean getWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
