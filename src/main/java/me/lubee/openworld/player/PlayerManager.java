package me.lubee.openworld.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

	private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();
	
	public void loadPlayer(UUID uuid) {
		playerDataMap.put(uuid, new PlayerData(uuid));
	}
	
	public void unloadPlayer(UUID uuid) {
		playerDataMap.remove(uuid);
	}
	
	public PlayerData getPlayerData(UUID uuid) {
		return playerDataMap.get(uuid);
	}
}
