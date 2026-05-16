package com.example.demo.serviceImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.exception.PlayerServiceException;
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
			throw new PlayerServiceException("Enter a Valid Name",HttpStatus.BAD_REQUEST);
		
		
		if(playerDto.getMobileNo().length()!=10) 
			throw new PlayerServiceException("Mobile Number should be of 10 digits",HttpStatus.BAD_REQUEST);
		
		if(!playerDto.getEmail().contains("@") || !playerDto.getEmail().contains(".com"))
			throw new PlayerServiceException("Invalid Email Format!",HttpStatus.BAD_REQUEST);
		
		if(playerDto.getAge()<=1 || playerDto.getAge()>=70)
			throw new PlayerServiceException("Enter Valid Age",HttpStatus.BAD_REQUEST);
		
		if(playerDto.getDateOfBirth().isAfter(LocalDate.now()))
			throw new PlayerServiceException("Enter Valid Date of Birth",HttpStatus.BAD_REQUEST);
		
	   List<Player> players = playerRepository.findAll();
	   
	   for(Player p : players) {
		   if(p.getEmail().equals(playerDto.getEmail())) {
			   throw new PlayerServiceException("Email already exists",HttpStatus.CONFLICT);
		   }
		   
		   if(p.getMobileNo().equals(playerDto.getMobileNo())) {
			   throw new PlayerServiceException("Mobile Number already exists",HttpStatus.CONFLICT);
		   }
	   }
		
		playerRepository.save(player);
	}

	@Override
	public Player getPlayer(int id) {
		Optional<Player> playerId = playerRepository.findById(id);
	    if(playerId.isEmpty()) 
	    	throw new PlayerServiceException("Player not found",HttpStatus.BAD_REQUEST);
	   
	    return playerId.get();
	}

	@Override
	public void removePlayer(int id) {
		Optional<Player> playerById = playerRepository.findById(id);
		if(playerById.isEmpty()) 
			throw new PlayerServiceException("Player not found",HttpStatus.BAD_REQUEST);
		
		playerRepository.deleteById(id);
	}

	@Override
	public Player updatePlayer(int id, PlayerDto playerDto) {
		Optional<Player> playerById = playerRepository.findById(id);

		if(playerById.isEmpty()) {
		    throw new PlayerServiceException("Player not found",HttpStatus.BAD_REQUEST);
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
			throw new PlayerServiceException("Name cannot be empty",HttpStatus.BAD_REQUEST);
		
		if(playerDto.getMobileNo().length()!=10)
			throw new PlayerServiceException("Mobile Number should be of 10digits!",HttpStatus.BAD_REQUEST);
		
		if(!playerDto.getEmail().contains("@") || !playerDto.getEmail().contains(".com"))
			throw new PlayerServiceException("Invalid Email Format!",HttpStatus.BAD_REQUEST);
		
		if(playerDto.getAge()<=1 || playerDto.getAge()>=70)
			throw new PlayerServiceException("Enter Valid Age",HttpStatus.BAD_REQUEST);
		
		if(playerDto.getDateOfBirth().isAfter(LocalDate.now()))
			throw new PlayerServiceException("Enter Valid Date of Birth",HttpStatus.BAD_REQUEST);
		
		return playerRepository.save(player);
	}

	@Override
	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public List<Player> getPlayersByName(String name)  {
	 List<Player> playerName = playerRepository.getPlayersByName(name);
	 if(playerName.isEmpty()) 
		 throw new PlayerServiceException("Player not found",HttpStatus.BAD_REQUEST);
	 
	 return playerName;
     }
	
	@Override
	public List<Player> getPlayersBySport(String sport) {
	 List<Player> playerState =  playerRepository.getPlayersBySport(sport);
	 if(playerState.isEmpty())
		 throw new PlayerServiceException("Player not found",HttpStatus.BAD_REQUEST);
	 
	 return playerState;
	}	
}
