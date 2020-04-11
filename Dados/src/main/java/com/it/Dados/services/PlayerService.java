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
		Player player = null;

		List<Player> playersList = new ArrayList<>();

		playerRepository.findAll().forEach(playersList::add);

		for (Player tempPlayer : playersList) {
			if (tempPlayer.getId().equals(id)) {

				player = tempPlayer;
			}
//			else {  					//TRY TO FIND A FORM TO COMUNICATE WHEN THE ID DOESN'T EXISTS THROUGH THE LIST OF PLAYERS!!!!!!!!!!
//
//				throw new ErrorException(
//						"The id '" + id + "' doesn't exists in our database, try with another one please.");
//			}
		}

		return player;
	}

	// DELETE player by id
	
    @Transactional
	public void deletePlayerById(Integer id) {
		
        playerRepository.deleteById(id);
        
//        java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`dices`.`game`, CONSTRAINT `FKss1l3mwkp44i09b7j2rolnyp5` FOREIGN KEY (`player_id_player`) REFERENCES `player` (`id_player`))

	}
}
