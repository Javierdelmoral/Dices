package com.it.Dados.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonView;
import com.it.Dados.domain.Player;
import com.it.Dados.exception.ErrorException;
import com.it.Dados.tools.View;
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
	public List<Player> getAllPlayers() {
		return playerService.getAllPlayers();
	}
	
	@GetMapping("dices/players/ranking")
	public List<Player> getAllPlayersRanking() {
		return playerService.getAllPlayersRanking();
	}

	@GetMapping("dices/players/id")
	public Player getPlayerById(@RequestBody Player player) {
		return playerService.getPlayerById(player.getId());
	}

//	@JsonView(View.Summary.class)
	@GetMapping("dices/players/average")
	public ResponseEntity<String> getPlayersAverage() {
		
		try
		{
			JSONObject averageJSON = playerService.getPlayersAverage();
			return new ResponseEntity<String>( averageJSON.toString() , HttpStatus.FOUND);
		}
		catch (ErrorException e)
		{
			String exceptionMessage = e.getMessage();
			JSONObject sendData = new JSONObject();
			JSONObject message = new JSONObject();
			message.put("type" , "error");
			message.put("message" , exceptionMessage);
			sendData.put("Message" , message);
			return new ResponseEntity<String>( sendData.toString() , HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("dices/players/id")
	public String updatePlayerById(@RequestBody Player player) {
		playerService.updatePlayerById(player, player.getId());

		return "Player with id '" + player.getId() + "' was updated.";
	}

	@DeleteMapping("dices/players/id")
	public String deletePlayerById(@RequestBody Player player) {
		playerService.deletePlayerById(player.getId());

		return "Player with id '" + player.getId() + "' was deleted.";
	}

	@DeleteMapping("dices/players")
	public String deleteAllPlayers() {
		playerRepository.deleteAll();

		return "All players were deleted!";
	}

}
