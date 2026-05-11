package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;

public interface PlayerService {

	public void savePlayer(PlayerDto playerDto);
	public Player getPlayer(int id);
	public void removePlayer(int id);
	public Player updatePlayer(int id, PlayerDto playerDto);
	public List<Player> getPlayers();
}
