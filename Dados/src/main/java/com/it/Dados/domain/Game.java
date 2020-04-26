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
	@GeneratedValue(strategy = GenerationType.SEQUENCE/* TABLE *//* IDENTITY */)
	@JoinColumn(name = "id_game")
	private Integer idGame;
	@OnDelete(action = OnDeleteAction.CASCADE) // @Cascade(CascadeType.DELETE_ORPHAN)
	@JoinColumn(name = "id_player", nullable = false)
	private Integer idPlayer;
	@OnDelete(action = OnDeleteAction.CASCADE) // @Cascade(CascadeType.DELETE_ORPHAN)
	@ManyToOne
	private Player player;
	private Integer valueDice1;
	private Integer valueDice2;
	private boolean won;

	public Game() {
	}

	public Game(Integer idGame, Integer idPlayer, Integer valueDice1, Integer valueDice2, boolean won) {
		super();
		this.idGame = idGame;
		this.idPlayer = idPlayer;
//		this.player = new Player(idPlayer,"",null,0);
		this.valueDice1 = valueDice1;
		this.valueDice2 = valueDice2;
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
