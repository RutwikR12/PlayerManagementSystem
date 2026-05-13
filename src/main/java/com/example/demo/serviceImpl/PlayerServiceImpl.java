package com.example.demo.serviceImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
		
		if(playerDto.getName().trim().isEmpty()) 
			throw new RuntimeException("Enter a Valid Name");
		
		
		if(playerDto.getMobileNo().length()!=10) 
			throw new RuntimeException("Mobile Number should be of 10 digits");
		
		if(!playerDto.getEmail().contains("@") || !playerDto.getEmail().contains(".com"))
			throw new RuntimeException("Invalid Email Format!");
		
		if(playerDto.getAge()<=1 || playerDto.getAge()>=70)
			throw new RuntimeException("Enter Valid Age");
		
		if(playerDto.getDateOfBirth().isAfter(LocalDate.now()))
			throw new RuntimeException("Enter Valid Date of Birth");
		
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
		Optional<Player> playerId = playerRepository.findById(id);
	    if(playerId.isEmpty()) 
	    	throw new RuntimeException("Player not found");
	   
	    return playerId.get();
	}

	@Override
	public void removePlayer(int id) {
		Optional<Player> playerById = playerRepository.findById(id);
		if(playerById.isEmpty()) 
			throw new RuntimeException("Player not found");
		
		playerRepository.deleteById(id);
	}

	@Override
	public Player updatePlayer(int id, PlayerDto playerDto) {
		Optional<Player> playerById = playerRepository.findById(id);

		if(playerById.isEmpty()) {
		    throw new RuntimeException("Player not found");
		}

		Player player = playerById.get();
		player.setName(playerDto.getName());
		player.setAge(playerDto.getAge());
		player.setEmail(playerDto.getEmail());
		player.setDateOfBirth(playerDto.getDateOfBirth());
		player.setCity(playerDto.getCity());
		player.setState(playerDto.getState());
		player.setMobileNo(playerDto.getMobileNo());
		player.setSport(playerDto.getSport());
		
		if(playerDto.getName().trim().isEmpty())
			throw new RuntimeException("Name cannot be empty");
		
		if(playerDto.getMobileNo().length()!=10)
			throw new RuntimeException("Mobile Number should be of 10digits!");
		
		if(!playerDto.getEmail().contains("@") || !playerDto.getEmail().contains(".com"))
			throw new RuntimeException("Invalid Email Format!");
		
		if(playerDto.getAge()<=1 || playerDto.getAge()>=70)
			throw new RuntimeException("Enter Valid Age");
		
		if(playerDto.getDateOfBirth().isAfter(LocalDate.now()))
			throw new RuntimeException("Enter Valid Date of Birth");
		
		return playerRepository.save(player);
	}

	@Override
	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}	
}
