package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;

@RestController
public class PlayerController {
  
	PlayerService playerService;
	
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	  
	
	@PostMapping("player") 
	public ResponseEntity<String> addPlayer(@RequestBody PlayerDto playerDto) {
		playerService.savePlayer(playerDto);
		return new ResponseEntity<String>("Player Addedd Sucesfully",HttpStatus.CREATED);
	}
	
	@GetMapping("player/id/{id}") 
	public ResponseEntity<Player> getPlayer(@PathVariable int id) {
		Player playerId = playerService.getPlayer(id);
		return new ResponseEntity<Player>(playerId,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}") 
	public ResponseEntity<String> deletePlayer(@PathVariable int id) {
		playerService.removePlayer(id);
		return new ResponseEntity<String>("Player Deleted Successfully",HttpStatus.OK);
	}
	
	@PutMapping("update/{id}") 
	public ResponseEntity<String> updatePlayer(@PathVariable int id, @RequestBody PlayerDto playerDto) {
		  playerService.updatePlayer(id, playerDto);
		return new ResponseEntity<String>("Player Details updated sucessfully",HttpStatus.OK); 
	}
	
	@GetMapping("players")
	  public ResponseEntity<List<Player>> getAllPlayers() {
		List<Player> allPlayers = playerService.getPlayers();
		return new ResponseEntity<List<Player>>(allPlayers,HttpStatus.OK);
	}
	
	@GetMapping("name/{name}")
     public ResponseEntity<List<Player>> getPlayersByName(@PathVariable String name) {
		List<Player> playerName = playerService.getPlayersByName(name);
		return new ResponseEntity<List<Player>>(playerName,HttpStatus.OK);
	}
	
	@GetMapping("sport/{sport}")
    public ResponseEntity<List<Player>> getPlayersBySport(@PathVariable String sport) {
		List<Player> playerSport = playerService.getPlayersBySport(sport);
		return new ResponseEntity<List<Player>>(playerSport,HttpStatus.OK);
	}
	
}
