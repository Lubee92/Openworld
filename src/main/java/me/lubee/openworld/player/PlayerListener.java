package me.lubee.openworld.player;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

	private final PlayerManager playerManager;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		playerManager.loadPlayer(event.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		playerManager.unloadPlayer(event.getPlayer().getUniqueId());
	}
}
