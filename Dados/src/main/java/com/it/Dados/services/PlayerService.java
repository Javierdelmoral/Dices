package com.it.Dados.services;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.it.Dados.domain.Player;
import com.it.Dados.exception.ErrorException;
import com.it.Dados.repositories.GameRepository;
import com.it.Dados.repositories.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	GameRepository gameRepository;
	@Autowired
	GameService gameService;

	// POST create new player

	public void createPlayer(Player player) {
		boolean exists = false;

		if (player.getName().equalsIgnoreCase("")) {
			player.setName("Anonymous");
		}

		List<Player> playersList = new ArrayList<>();

		playerRepository.findAll().forEach(playersList::add);

		for (int i = 0; i < playersList.size(); i++) {

			if (player.getName().equalsIgnoreCase(playersList.get(i).getName())
					&& !player.getName().equalsIgnoreCase("Anonymous")) {
				exists = true;

				throw new ErrorException(
						"The name '" + player.getName() + "' is already taken, choose another name please.");
			}
		}

		if (exists == false || player.getName().equalsIgnoreCase("Anonymous")) {
			player.setName(player.getName());
			player.setRegisterDate(player.getRegisterDate());
			player.setSuccessRate(player.getSuccessRate());
			playerRepository.save(player);
		}
	}

	// GET all players

	public List<Player> getAllPlayers() {

		if (playerRepository.count() > 0) {

			return playerRepository.findAll();

		} else {
			throw new ErrorException("Doesn't exist any player!");
		}

	}

	// GET player by id

	public Player getPlayerById(Integer id) {
		if (playerRepository.count() > 0) {

			Player player = new Player();

			if (playerRepository.findById(id).isPresent()) {

				player = playerRepository.getOne(id);

			} else {
				throw new ErrorException(
						"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
			}

			return player;

		} else {
			throw new ErrorException("Doesn't exist any player!");
		}

	}

	// GET average success of all players

	public JSONObject getPlayersAverage() {

		if (playerRepository.count() > 0) {

			List<Player> playersList = playerRepository.findAll();
			Double sumSuccess = (double) 0;

			for (Player player : playersList) {

				sumSuccess += player.getSuccessRate();
			}

			Double averageSuccess = sumSuccess / playersList.size();
			averageSuccess = Math.round(averageSuccess * 100.0) / 100.0;

			JSONObject averageJSON = new JSONObject();

			try {
				averageJSON.put("averageSuccess", averageSuccess);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return averageJSON;

		} else {
			throw new ErrorException("There is no player!");
		}
	}

	// GET players by ranking

	public List<Player> getAllPlayersRanking() {

		if (playerRepository.count() > 0) {

			List<Player> playersList = playerRepository.findAll();
			List<Player> rankingList = new ArrayList<Player>();

			for (int i = 0; i < playersList.size() - 1; i++) {

				Player player = new Player();
				Player player1 = playersList.get(i);

				for (int j = 0; j < playersList.size() - 1; j++) {

					Player player2 = playersList.get(j + 1);

					if (player1.getSuccessRate() > player2.getSuccessRate()) {

						player = player1;
						player1 = player2;
						player2 = player;

						rankingList.add(player1);
					}

				}

			}
//				
//				Player player1 = playersList.get(i);
//				
//				for (int j = 0; j < playersList.size()-1; j++) {
//
//					Player player2 = playersList.get(j + 1);
//
//					if (player1.getSuccessRate() > player2.getSuccessRate()) {
//
//						rankingList.add(player1);
//
//					}
//				}
//			}

			System.out.println(rankingList);
			return rankingList;

		} else {
			throw new ErrorException("There is no player!");
		}

	}

	// DELETE player by id

	@Transactional
	public void deletePlayerById(Integer id) {
		if (playerRepository.count() > 0) {

			if (playerRepository.findById(id).isPresent()) {

				gameRepository.deleteByIdPlayer(id);
				playerRepository.deleteById(id);

			} else {
				throw new ErrorException(
						"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
			}
		} else {
			throw new ErrorException("Doesn't exist any player!");
		}
	}

	// PUT player by id

	public Player updatePlayerById(Player player, Integer id) {

		if (playerRepository.count() > 0) {

//			if player with ID 'x' exists...
			if (playerRepository.findById(id).isPresent()) {

				List<Player> playersList = playerRepository.findAll();

//			Get player with the ID
				Player updatePlayer = playerRepository.getOne(id);

				boolean exists = false;

//			if it's empty then set to anonymous
				if (player.getName().equalsIgnoreCase("")) {
					player.setName("Anonymous");
				}

//			if it's not empty then check if the updated name already exists
				for (int i = 0; i < playersList.size(); i++) {

					if (player.getName().equalsIgnoreCase(playersList.get(i).getName())
							&& !player.getName().equalsIgnoreCase("Anonymous")) {
						exists = true;

						throw new ErrorException(
								"The name '" + player.getName() + "' is already taken, choose another name please.");
					}
				}

//			if the updated name doesn't exist then update it
				if (exists == false || player.getName().equalsIgnoreCase("Anonymous")) {
					updatePlayer.setName(player.getName());
					playerRepository.save(player);
				}

				return updatePlayer;

			} else {
				throw new ErrorException(
						"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
			}

		} else {
			throw new ErrorException("Doesn't exist any player!");
		}

	}

}
