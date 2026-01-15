package me.lubee.openworld;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.player.PlayerListener;
import me.lubee.openworld.player.PlayerManager;
import me.lubee.openworld.player.StatusTask;

@RequiredArgsConstructor
public class OpenWorld extends JavaPlugin {

	private final PlayerManager playerManager;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(playerManager), this);
		new StatusTask(playerManager).runTaskTimer(this, 0L, 10L);
	}
}
