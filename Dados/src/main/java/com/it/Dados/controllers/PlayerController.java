package com.it.Dados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.Dados.domain.Player;
import com.it.Dados.services.PlayerService;


@RestController
public class PlayerController {
	
    @Autowired
    PlayerService playerService;
	
    @PostMapping("dices/players")
    public Player addPlayer (@RequestBody Player player){
        playerService.createPlayer(player);
        
        return player;
    }
    
    @GetMapping("dices/players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }
    

}
