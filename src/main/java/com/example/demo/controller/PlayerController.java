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
		try {
		playerService.savePlayer(playerDto);
		return new ResponseEntity<String>("Player Addedd Sucesfully",HttpStatus.CREATED);
		
		} catch(RuntimeException re) {
			return new ResponseEntity<String>(re.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("player/{id}") 
	public ResponseEntity<Player> getPlayer(@PathVariable int id) {
		try {
		Player playerId = playerService.getPlayer(id);
		return new ResponseEntity<Player>(playerId,HttpStatus.OK);
		
		}catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("player/{id}") 
	public ResponseEntity<String> deletePlayer(@PathVariable int id) {
		try {
		playerService.removePlayer(id);
		return new ResponseEntity<String>("Player Deleted Successfully",HttpStatus.OK);
		}catch(RuntimeException re) {
			return new ResponseEntity<String>(re.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("update/{id}") 
	public ResponseEntity<String> updatePlayer(@PathVariable int id, @RequestBody PlayerDto playerDto) {
		try {
		  playerService.updatePlayer(id, playerDto);
		return new ResponseEntity<String>("Player Details updated sucessfully",HttpStatus.OK); 
		}catch(RuntimeException re) {
			return new ResponseEntity<String>(re.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("players")
	  public ResponseEntity<List<Player>> getAllPlayers() {
		List<Player> allPlayers = playerService.getPlayers();
		return new ResponseEntity<List<Player>>(allPlayers,HttpStatus.OK);
	}
}
