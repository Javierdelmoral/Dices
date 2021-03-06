package com.it.Dados.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.it.Dados.DTOs.GameDTO;
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
		if (!playerRepository.findById(id).isPresent()) {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

		Game gameTry = new Game();
		Player player = playerRepository.getOne(id);

		gameTry.setIdPlayer(id);
		gameTry.setPlayer(player);
		// random dice rolls and convert to Integers
		gameTry.setDice1((int) Math.floor(Math.random() * 6 + 1));
		gameTry.setDice2((int) Math.floor(Math.random() * 6 + 1));
		gameTry.setDice3((int) Math.floor(Math.random() * 6 + 1));
		gameTry.setDice4((int) Math.floor(Math.random() * 6 + 1));
		gameTry.setDice5((int) Math.floor(Math.random() * 6 + 1));
		gameTry.setDice6((int) Math.floor(Math.random() * 6 + 1));

		// set the actual amount of dice rolls of player 'X'
		player.setTotalDiceRolls(player.getTotalDiceRolls());

		int totalValue = gameTry.getDice1() + gameTry.getDice2() + gameTry.getDice3() + gameTry.getDice4()
				+ gameTry.getDice5() + gameTry.getDice6();

		if (totalValue >= 12 && totalValue <= 18) {
			gameTry.setWon(true);
			// each won game is enumerated and saved
			player.setGamesWon(player.getGamesWon() + 1);

		} else {
			gameTry.setWon(false);
		}

		// sum every new dice roll and save it to the field of player 'X'
		player.setTotalDiceRolls(player.getTotalDiceRolls() + 1);

		// calculate the succes rate of player 'X' with total games won and total dice
		// rolls and round to two decimals only, then save to player 'X' field
		Double successRate = (((double) player.getGamesWon() / player.getTotalDiceRolls()) * 100);
		successRate = Math.round(successRate * 100.0) / 100.0;

		player.setSuccessRate(successRate);

		gameRepository.save(gameTry);

		gameTry.getPlayer().setRegisterDate(null);

		System.out.println("Player: " + player.getId() + " ||| Number of game tries: " + player.getTotalDiceRolls()
				+ " ||| won?: " + gameTry.getWon() + " ||| total value of all dices: " + totalValue
				+ " ||| number of games won: " + player.getGamesWon() + " ||| Succes: " + player.getSuccessRate());

		return gameTry;
	}

	// GET All games of a player

	public Map<String, List<GameDTO>> getAllGames(Integer id) {
		if (!playerRepository.findById(id).isPresent()) {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

		Player player = new Player();
		player.setId(id);

		player = playerRepository.getOne(player.getId());
		// save all games of playerX
		List<Game> listGames = allGamesById(player.getId());
		// list to add each new DTO realted to the list of games OG
		List<GameDTO> listGamesDTO = new ArrayList<>();
		// Map to relate the user with it's GameDTOs list
		Map<String, List<GameDTO>> allGames = new HashMap<String, List<GameDTO>>();

		for (int i = 0; i < listGames.size(); i++) {
			// DTO to save the info of the fields we are interested to
			GameDTO gameDTO = new GameDTO();

			gameDTO.setIdGame(listGames.get(i).getId());
			gameDTO.setIdPlayer(listGames.get(i).getIdPlayer());
			gameDTO.setName(player.getName());
			gameDTO.setValueDice1(listGames.get(i).getDice1());
			gameDTO.setValueDice2(listGames.get(i).getDice2());
			gameDTO.setValueDice3(listGames.get(i).getDice3());
			gameDTO.setValueDice4(listGames.get(i).getDice4());
			gameDTO.setValueDice5(listGames.get(i).getDice5());
			gameDTO.setValueDice6(listGames.get(i).getDice6());

			gameDTO.setWon(listGames.get(i).getWon());
			gameDTO.setTotalDiceRolls(listGames.get(i).getPlayer().getTotalDiceRolls());
			gameDTO.setSuccessRate(listGames.get(i).getPlayer().getSuccessRate());

			listGamesDTO.add(i, gameDTO);
		}

		System.out.println(listGamesDTO.toString());
		allGames.put("GAMES", listGamesDTO);

		return allGames;
	}

	// method to get a list of games by player id

	public List<Game> allGamesById(Integer id) {
		List<Game> gamesList = gameRepository.findByIdPlayer(id);

		if (gamesList.isEmpty()) {
			throw new ErrorException("The Player with id '" + id + "' doesn't have any saved games.");
		}

		return gameRepository.findByIdPlayer(id);

	}

	// DELETE All games of a player

	@Transactional
	public void deleteGamesById(Integer id) {
		if (!playerRepository.findById(id).isPresent()) {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

		List<Game> gamesList = new ArrayList<>();
		gameRepository.findAll().forEach(gamesList::add);

		if (gamesList.isEmpty()) {
			throw new ErrorException("The Player with id '" + id + "' doesn't has any game to delete.");
		}

		gameRepository.deleteByIdPlayer(id);
		playerRepository.getOne(id).setGamesWon(0);
		playerRepository.getOne(id).setSuccessRate(0);
		playerRepository.getOne(id).setTotalDiceRolls(0);
	}
}
