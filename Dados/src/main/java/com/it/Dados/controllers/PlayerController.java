package com.it.Dados.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonView;
import com.it.Dados.DTOs.GameDTO;
import com.it.Dados.DTOs.PlayerDTO;
import com.it.Dados.domain.Player;
import com.it.Dados.exception.ErrorException;
import com.it.Dados.repositories.PlayerRepository;
import com.it.Dados.services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerService playerService;
	@Autowired
	PlayerRepository playerRepository;

	@PostMapping("dices/players")
	public Player createPlayer(@RequestBody Player player) {
		playerService.createPlayer(player);

		return player;
	}
	
	@GetMapping("dices/players")
	public Map<String, List<PlayerDTO>> getAllPlayers() {
		return playerService.getAllPlayers();
	}

    @RequestMapping("dices/players/{id}")
    public Player getPlayer(@PathVariable Integer id){
        return playerService.getPlayerById(id);
    }

	@GetMapping("dices/players/ranking")
	public Map<String, List<PlayerDTO>> getRanking() {
		return playerService.getAllPlayersRanking();
	}
	
	@GetMapping("dices/players/worst")
	public Player getWorstPlayer() {
		List<Player> listPlayers = playerRepository.findAll();
		
		listPlayers.sort(Comparator.comparing(Player::getSuccessRate));
		
		Player worstPlayer = listPlayers.get(0);

		return worstPlayer;
	}
	
	@GetMapping("dices/players/best")
	public Player getBestPlayer() {
		List<Player> listPlayers = playerRepository.findAll();
		
		listPlayers.sort(Comparator.comparing(Player::getSuccessRate));
		
		Player bestPlayer = listPlayers.get(listPlayers.size()-1);

		return bestPlayer;
	}

	@GetMapping("dices/players/average")
	public String getPlayersAverage() {
			JSONObject averageJSON = playerService.getPlayersAverage();
			String json = averageJSON.toString();
			return json;
	}

	@PutMapping("dices/players/id")
	public String updatePlayerById(@RequestBody Player player) {
		playerService.updatePlayerById(player, player.getId());

		return "Player with id '" + player.getId() + "' was updated.";
	}

	@DeleteMapping("dices/players")
	public String deleteAllPlayers() {
		playerRepository.deleteAll();

		return "All players were deleted!";
	}

	@DeleteMapping("dices/players/id")
	public String deletePlayerById(@RequestBody Player player) {
		playerService.deletePlayerById(player.getId());

		return "Player with id '" + player.getId() + "' was deleted.";
	}
}
