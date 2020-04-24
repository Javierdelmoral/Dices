package com.it.Dados.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.Dados.DTOs.GameDTO;
import com.it.Dados.domain.Game;
import com.it.Dados.domain.Player;
import com.it.Dados.repositories.PlayerRepository;
import com.it.Dados.services.GameService;
import com.it.Dados.services.PlayerService;

@RestController
public class GameController {
	
    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;
    @Autowired
    PlayerRepository playerRepository;
    
    @PostMapping("dices/players/id/games")
    public Game createGame(@RequestBody Player player) {
    	return gameService.createGame(player.getId());
    }
        
    @GetMapping("dices/players/id/games")
    public Map<String, List<GameDTO>> getAllGames(@RequestBody Player player){
    	
		player = playerRepository.getOne(player.getId());
    	//save all games of playerX
		List<Game> listGames = gameService.getAllGamesById(player.getId());
		//list to add each new DTO realted to the list of games OG
		List<GameDTO> listGamesDTO = new ArrayList<>();
		//Map to relate the user with it's GameDTOs list
		Map<String,List<GameDTO>> allGames = new HashMap<String,List<GameDTO>>();

		for (int i = 0; i < listGames.size(); i++) {
			//DTO to save the info of the fields we are interested to
			GameDTO gameDTO = new GameDTO();

			gameDTO.setIdGame(listGames.get(i).getId());
			gameDTO.setIdPlayer(listGames.get(i).getIdPlayer());
			gameDTO.setValueDice1(listGames.get(i).getDice1());
			gameDTO.setValueDice2(listGames.get(i).getDice2());
			gameDTO.setWon(listGames.get(i).getWon());
			gameDTO.setTotalDiceRolls(listGames.get(i).getPlayer().getTotalDiceRolls());
			gameDTO.setSuccessRate(listGames.get(i).getPlayer().getSuccessRate());
			
			listGamesDTO.add(i, gameDTO);
		}
		
		System.out.println(listGamesDTO.toString());
		allGames.put(player.getName().toUpperCase(), listGamesDTO);

		//MIRAR DE EXTRAPOLAR ESTO A SERVICE Y DEJAR EL CONTROLLER MÁS LIMPIO
		
        return allGames;
    }
    
    @DeleteMapping("dices/players/id/games")
    public String deleteAllGames(@RequestBody Player player){
        gameService.deleteGamesById(player.getId());

        return "All games of Player with ID '" + player.getId() + "' have been deleted.";
    }

}
