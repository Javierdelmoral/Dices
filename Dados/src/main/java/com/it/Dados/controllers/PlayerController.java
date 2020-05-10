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
	public Map<String, List<PlayerDTO>> getAllPlayers() {
		return playerService.getAllPlayers();
	}

//	@GetMapping("dices/players/id")
//	public Player getPlayerById(@RequestBody Player player) {
//		return playerService.getPlayerById(player.getId());
//	}
	
	//Here we're using a RequestMapping instead of GetMapping because it can cause problems with the browsers
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

//	How to transform it to JSON directly? Now is pure String(in json format)
//	@GetMapping("dices/players/average")
//	public ResponseEntity<String> getPlayersAverage() {
//
//		try {
//			JSONObject averageJSON = playerService.getPlayersAverage();
//			String averageJSONString = averageJSON.toString();
//			return new ResponseEntity<String>(averageJSONString, HttpStatus.OK);
//		} catch (ErrorException e) {
//			String exceptionMessage = e.getMessage();
//			JSONObject sendData = new JSONObject();
//			JSONObject message = new JSONObject();
//			message.put("type", "error");
//			message.put("message", exceptionMessage);
//			sendData.put("Message", message);
//			return new ResponseEntity<String>(sendData.toString(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
	//I changed this GET to have the average easily than with the ResponseEntity of above
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
