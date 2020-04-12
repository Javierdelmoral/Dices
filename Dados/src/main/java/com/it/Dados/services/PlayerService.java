package com.it.Dados.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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
		// TODO Auto-generated method stub
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

		return playerRepository.findAll();

	}

	// GET player by id

	public Player getPlayerById(Integer id) {
		Player player = new Player();

		if (playerRepository.findById(id).isPresent()) {

			player = playerRepository.getOne(id);

		} else {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}

		return player;
	}

	// DELETE player by id

	@Transactional
	public void deletePlayerById(Integer id) {

		if (playerRepository.findById(id).isPresent()) {

			gameRepository.deleteByIdPlayer(id);
			playerRepository.deleteById(id);

		} else {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}
	}

	// PUT player by id

	public Player updatePlayerById(Player player, Integer id) {

//		if player with ID 'x' exists...
		if (playerRepository.findById(id).isPresent()) {

			List<Player> playersList = new ArrayList<>();
			playerRepository.findAll().forEach(playersList::add);

//			Get player with the ID
			Player updatePlayer = playerRepository.getOne(id);

//			if field "name" of JSON Body is empty set it to ANONYMOUS
			if (player.getName().equalsIgnoreCase("")) {

				updatePlayer.setName("Anonymous");

//			if field "name" is not empty will check if that name exists and then save it to repo
			} else {

				for (int i = 0; i < playersList.size(); i++) {

					if (player.getName().equalsIgnoreCase(playersList.get(i).getName())
							&& !player.getName().equalsIgnoreCase("Anonymous")) {

						throw new ErrorException(
								"The name '" + player.getName() + "' is already taken, choose another name please.");
					} else {

						updatePlayer.setName(player.getName());
					}
				}
			}

			playerRepository.save(updatePlayer);

			return updatePlayer;

		} else {
			throw new ErrorException(
					"The Player with id '" + id + "' doesn't exists in our database, try with another one please.");
		}
	}
}
