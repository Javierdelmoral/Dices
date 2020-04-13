package com.it.Dados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.Dados.domain.Game;
import com.it.Dados.domain.Player;
import com.it.Dados.services.GameService;
import com.it.Dados.services.PlayerService;


@RestController
public class GameController {
	
    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;
    
    @PostMapping("dices/players/id/games")
    public Game createGame(@RequestBody Player player) {
    	return gameService.createGame(player.getId());
    }
    
    @GetMapping("dices/players/id/games")
    public List<Game> getAllGames(@RequestBody Player player){
        return gameService.getAllGames(player.getId());
    }

}
