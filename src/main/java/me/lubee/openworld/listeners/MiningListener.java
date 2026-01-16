package me.lubee.openworld.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.player.PlayerData;
import me.lubee.openworld.player.PlayerManager;

@RequiredArgsConstructor
public class MiningListener implements Listener {

	private final PlayerManager playerManager;
	
	@EventHandler
	public void onMine(BlockBreakEvent event) {
		Player player = event.getPlayer();
		PlayerData data = playerManager.getPlayerData(player.getUniqueId());
		if (data == null) return;
		
		Block block = event.getBlock();
		if (!block.getType().name().contains("ORE")) return;
		
		int luckLevel = data.getSkillLevel("luck_passive");
		if (luckLevel > 0) {
			double chance = luckLevel * 0.05;
			if (Math.random() < chance) {
				block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(block.getDrops().iterator().next().getType()));
			}
		}
	}
}
