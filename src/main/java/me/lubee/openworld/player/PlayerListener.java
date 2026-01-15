package me.lubee.openworld.player;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		PlayerData data = playerManager.getPlayerData(player.getUniqueId());
		if (data == null) return;
		if (player.isSprinting()) {
			data.addStamina(-0.5);
			
			if (data.getStamina() <= 0) {
				player.setSprinting(false);
			}
		} else {
			data.addStamina(0.3);
		}
	}
}
