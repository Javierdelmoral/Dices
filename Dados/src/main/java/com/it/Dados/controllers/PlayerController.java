package com.it.Dados.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.Dados.domain.Player;
import com.it.Dados.services.PlayerService;


@RestController
public class PlayerController {
	
    @Autowired
    PlayerService playerService;
	
    @PostMapping("dices/players")
    public Player createPlayer (@RequestBody Player player){
        playerService.createPlayer(player);
        
        return player;
    }
    
    @GetMapping("dices/players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }
    
    @GetMapping("dices/players/id")
    public Player getPlayerById(@RequestBody Player player){
        return playerService.getPlayerById(player.getId());
    }
    
    @PutMapping("dices/players/id")
    public String updatePlayerById(@RequestBody Player player){
        playerService.updatePlayerById(player, player.getId());
        
        return "Player with id '" + player.getId() + "' was updated.";
    }
    
    @DeleteMapping("dices/players/id")
    public String deletePlayerById(@RequestBody Player player){
        playerService.deletePlayerById(player.getId());
        
        return "Player with id '" + player.getId() + "' was deleted.";
    }

}
