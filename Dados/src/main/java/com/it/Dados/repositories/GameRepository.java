package com.it.Dados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.Dados.domain.Game;
import com.it.Dados.domain.Player;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{

    public List<Game> findByIdPlayer (Integer idPlayer);

    public List<Game> deleteByIdPlayer (Integer idPlayer);
	
}
