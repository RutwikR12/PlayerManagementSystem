package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService{

	PlayerRepository playerRepository;
	
	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@Override
	public void savePlayer(PlayerDto playerDto) {
		Player player = new Player();
		player.setName(playerDto.getName());
		player.setAge(playerDto.getAge());
		player.setEmail(playerDto.getEmail());
		player.setDateOfBirth(playerDto.getDateOfBirth());
		player.setCity(playerDto.getCity());
		player.setState(playerDto.getState());
		player.setMobileNo(playerDto.getMobileNo());
		player.setSport(playerDto.getSport());
		
		if(playerDto.getName().trim().isEmpty()) {
			throw new RuntimeException("Enter a Valid Name");
		}
		
		if(playerDto.getMobileNo().length()!=10) {
			throw new RuntimeException("Mobile Number should be of 10 digits");
		}
		
	   List<Player> players = playerRepository.findAll();
	   
	   for(Player p : players) {
		   if(p.getEmail().equals(playerDto.getEmail())) {
			   throw new RuntimeException("Email already exists");
		   }
		   
		   if(p.getMobileNo().equals(playerDto.getMobileNo())) {
			   throw new RuntimeException("Mobile Number already exists");
		   }
	   }
		
		playerRepository.save(player);
	}

	@Override
	public Player getPlayer(int id) {
		return playerRepository.findById(id).get();
	}

	@Override
	public void removePlayer(int id) {
		playerRepository.deleteById(id);
	}

	@Override
	public Player updatePlayer(int id, PlayerDto playerDto) {
		Player player = playerRepository.findById(id).get();
		player.setName(playerDto.getName());
		player.setAge(playerDto.getAge());
		player.setEmail(playerDto.getEmail());
		player.setDateOfBirth(playerDto.getDateOfBirth());
		player.setCity(playerDto.getCity());
		player.setState(playerDto.getState());
		player.setMobileNo(playerDto.getMobileNo());
		player.setSport(playerDto.getSport());
		return playerRepository.save(player);
	}

	@Override
	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}	
}
