package com.it.Dados.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //create new player
    
	public void createPlayer(Player player) {
		// TODO Auto-generated method stub
		boolean exists = false;
		
		if(player.getName().equalsIgnoreCase("")) {
			player.setName("Anonymous");
		}
		
		List<Player> playersList = new ArrayList<>();
		
		playerRepository.findAll().forEach(playersList::add); 
		
		for (int i = 0; i < playersList.size(); i++) {
			
			if(player.getName().equalsIgnoreCase(playersList.get(i).getName()) && !player.getName().equalsIgnoreCase("Anonymous")) {
				exists = true;
				
        		throw new ErrorException("The name '" + player.getName() + "' is already taken, choose another name please");      
        		
        		//ARREGLAR PARA QUE PUEDA ACEPTAR MÁS DE 1 ANONYMOUS, AHORA MISMO LO INTERPRETA COMO QUE YA ESTÁ COGIDO Y NO DEJA QUE SE REPITA!!
        		
			}
		}
		
		if(exists == false || player.getName().equalsIgnoreCase("Anonymous")) {
			player.setName(player.getName());
			player.setRegisterDate(player.getRegisterDate());//(LocalDateTime.now());
			player.setSuccessRate(player.getSuccessRate());//(Double.valueOf(0));
			playerRepository.save(player);
		}
				
	}

	public List<Player> getAllPlayers() {
		
		return playerRepository.findAll();
		
	}

}
