package com.it.Dados.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.Dados.domain.Game;
import com.it.Dados.domain.Player;
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
			Player player = playerRepository.getOne(id);

			gameTry.setIdPlayer(id);
			gameTry.setPlayer(player);
			// random dice rolls and convert to Integers
			gameTry.setDice1((int) Math.floor(Math.random() * 6 + 1));
			gameTry.setDice2((int) Math.floor(Math.random() * 6 + 1));

			// set the actual amount of dice rolls of player 'X'
			player.setTotalDiceRolls(player.getTotalDiceRolls());

			int totalValue = gameTry.getDice1() + gameTry.getDice2();

			if (totalValue == 7) {
				gameTry.setWon(true);
				// each won game is enumerated and saved
				player.setGamesWon(player.getGamesWon() + 1);

			} else {
				gameTry.setWon(false);
			}

			// sum every new dice roll and save it to the field of player 'X'
			player.setTotalDiceRolls(player.getTotalDiceRolls() + 1);

			// calculate the succes rate of player 'X' with total games won and total dice
			// rolls, then save to player 'X' field
			float successRate = (((float) player.getGamesWon() / player.getTotalDiceRolls()) * 100);
			player.setSuccessRate(successRate);

			gameRepository.save(gameTry);

			System.out.println("Player: " + player.getId() + " ||| Number of game tries: " + player.getTotalDiceRolls()
					+ " ||| number of games won: " + player.getGamesWon() + " ||| Succes: " + player.getSuccessRate());

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

	// DELETE All games of a player

	@Transactional
	public void deleteGamesById(Integer id) {
		if (playerRepository.findById(id).isPresent()) {

			List<Game> gamesList = new ArrayList<>();
			gameRepository.findAll().forEach(gamesList::add);

			if (gamesList.isEmpty()) {

				throw new ErrorException("The Player with id '" + id + "' doesn't has any game to delete.");
			} else {

				gameRepository.deleteByIdPlayer(id);
				playerRepository.getOne(id).setGamesWon(0);
				playerRepository.getOne(id).setSuccessRate(0);
				playerRepository.getOne(id).setTotalDiceRolls(0);
			}

		} else {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

	}
}
