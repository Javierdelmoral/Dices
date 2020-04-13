package com.it.Dados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.Dados.domain.Game;
import com.it.Dados.exception.ErrorException;
import com.it.Dados.repositories.GameRepository;
import com.it.Dados.repositories.PlayerRepository;

@Service
public class GameService {

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	GameRepository gameRepository;

	// POST a game of a player

	public Game createGame(Integer id) {

		if (playerRepository.findById(id).isPresent()) {

			Game gameTry = new Game();
			
			gameTry.setIdPlayer(id);
			gameTry.setPlayer(playerRepository.getOne(id));
			gameTry.setDice1((int) Math.floor(Math.random() * 6 + 1));
			gameTry.setDice2((int) Math.floor(Math.random() * 6 + 1));

			int totalValue = gameTry.getDice1() + gameTry.getDice2();

			if (totalValue == 7) {
				gameTry.setWon(true);
			} else {
				gameTry.setWon(false);
			}

			gameRepository.save(gameTry);

			return gameTry;

		} else {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

	}

	// GET All games of a player

	public List<Game> getAllGames(Integer id) {

		if (playerRepository.findById(id).isPresent()) {

			return gameRepository.findByIdPlayer(id);

		} else {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

	}

}
