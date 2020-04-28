package com.it.Dados.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.Dados.DTOs.GameDTO;
import com.it.Dados.DTOs.PlayerDTO;
import com.it.Dados.domain.Game;
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

	// GET all players with DTO (JSON properly ordered)

	public Map<String, List<PlayerDTO>> getAllPlayers() {

		if (playerRepository.count() > 0) {

			List<Player> playersList = playerRepository.findAll();
			// list to add each new DTO realted to the list of games OG
			List<PlayerDTO> listPlayersDTO = new ArrayList<>();
			// Map to relate the user with it's GameDTOs list
			Map<String, List<PlayerDTO>> allPlayers = new HashMap<String, List<PlayerDTO>>();

			for (int i = 0; i < playersList.size(); i++) {
				// DTO to save the info of the fields we are interested to
				PlayerDTO playerDTO = new PlayerDTO();

				playerDTO.setIdPlayer(playersList.get(i).getId());
				playerDTO.setName(playersList.get(i).getName());
				playerDTO.setRegisterDate(playersList.get(i).getRegisterDate());
				playerDTO.setTotalDiceRolls(playersList.get(i).getTotalDiceRolls());
				playerDTO.setGamesWon(playersList.get(i).getGamesWon());
				playerDTO.setSuccessRate(playersList.get(i).getSuccessRate());

				listPlayersDTO.add(i, playerDTO);
			}

			System.out.println(listPlayersDTO.toString());
			allPlayers.put("PLAYERS", listPlayersDTO);

			return allPlayers;

		} else {
			throw new ErrorException("There is no player!");
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
			throw new ErrorException("There is no player!");
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

	// GET players by ranking with DTO (JSON properly ordered)

	public Map<String, List<PlayerDTO>> getAllPlayersRanking() {

		if (playerRepository.count() > 0) {

			List<Player> playersList = playerRepository.findAll();
			// list to add each new DTO related to the list of games OG
			List<PlayerDTO> listPlayersDTO = new ArrayList<>();
			// Map to relate the user with it's GameDTOs list
			Map<String, List<PlayerDTO>> allPlayersRanking = new HashMap<String, List<PlayerDTO>>();

			for (int i = 0; i < playersList.size(); i++) {
				// DTO to save the info of the fields we are interested to
				PlayerDTO playerDTO = new PlayerDTO();

				playerDTO.setIdPlayer(playersList.get(i).getId());
				playerDTO.setName(playersList.get(i).getName());
				playerDTO.setRegisterDate(playersList.get(i).getRegisterDate());
				playerDTO.setTotalDiceRolls(playersList.get(i).getTotalDiceRolls());
				playerDTO.setGamesWon(playersList.get(i).getGamesWon());
				playerDTO.setSuccessRate(playersList.get(i).getSuccessRate());

				listPlayersDTO.add(i, playerDTO);
			}

			// Sort all players by they succes rate and reverse it from best to worst
			listPlayersDTO.sort(Comparator.comparing(PlayerDTO::getSuccessRate).reversed());

			for (PlayerDTO player : listPlayersDTO) {
				player.setRegisterDate(null);
			}

			System.out.println(listPlayersDTO.toString());
			allPlayersRanking.put("RANKING", listPlayersDTO);

			return allPlayersRanking;

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
			throw new ErrorException("There is no player!");
		}
	}

	// PUT player by id

	public Player updatePlayerById(Player player, Integer id) {

		if (playerRepository.count() > 0) {

			// if player with ID 'x' exists...
			if (playerRepository.findById(id).isPresent()) {

				List<Player> playersList = playerRepository.findAll();

				// Get player with the ID
				Player updatePlayer = playerRepository.getOne(id);

				boolean exists = false;

				// if it's empty then set to anonymous
				if (player.getName().equalsIgnoreCase("")) {
					player.setName("Anonymous");
				}

				// if it's not empty then check if the updated name already exists
				for (int i = 0; i < playersList.size(); i++) {

					if (player.getName().equalsIgnoreCase(playersList.get(i).getName())
							&& !player.getName().equalsIgnoreCase("Anonymous")) {
						exists = true;

						throw new ErrorException(
								"The name '" + player.getName() + "' is already taken, choose another name please.");
					}
				}

				// if the updated name doesn't exist then update it
				if (exists == false || player.getName().equalsIgnoreCase("Anonymous")) {
					updatePlayer.setName(player.getName());

					playerRepository.save(updatePlayer);
				}

				return updatePlayer;

			} else {
				throw new ErrorException(
						"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
			}

		} else {
			throw new ErrorException("There is no player!");
		}
	}
}
