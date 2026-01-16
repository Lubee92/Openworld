package me.lubee.openworld;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.listeners.PlayerListener;
import me.lubee.openworld.player.PlayerManager;
import me.lubee.openworld.recipes.LeatherPouchRecipe;

@RequiredArgsConstructor
public class OpenWorld extends JavaPlugin {

	private static OpenWorld instance;
	private final PlayerManager playerManager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		getServer().getPluginManager().registerEvents(new PlayerListener(playerManager), this);
		
		new LeatherPouchRecipe(this).register();
		
		new StatusTask(playerManager).runTaskTimer(this, 0L, 10L);
	}
	
	public static OpenWorld getInstance() {
		return instance;
	}
}
