package me.lubee.openworld.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.player.PlayerData;
import me.lubee.openworld.player.PlayerManager;

@RequiredArgsConstructor
public class InfectionListener implements Listener {
	private final PlayerManager playerManager;

	@EventHandler
	public void onZombieAttach(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player player && event.getDamager() instanceof Zombie) {
			PlayerData data = playerManager.getPlayerData(player.getUniqueId());
			if (data != null) {
				data.addInfection(2.5);
			}
		}
	}
}
