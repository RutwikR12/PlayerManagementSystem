package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Player;

public interface PlayerRepository extends JpaRepository<Player,Integer>{

	@Query("Select p from Player p where p.name = :name")
	public List<Player> getPlayersByName(String name);
	
	@Query(value = "Select * from playerss where sport =:sport", nativeQuery=true)
	public List<Player> getPlayersBySport(String sport);
}
