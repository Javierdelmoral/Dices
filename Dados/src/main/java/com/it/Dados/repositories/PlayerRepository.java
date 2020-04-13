package com.it.Dados.repositories;

import org.springframework.stereotype.Repository;

import com.it.Dados.domain.Player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
//	Player findByName (String playerName);
	
//	Player findByPlayerId (Integer idPlayer);
	
}
