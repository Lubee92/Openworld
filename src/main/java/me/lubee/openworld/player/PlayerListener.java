package me.lubee.openworld.player;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
	
	@EventHandler
	public void onWaterFill(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		
		if (item == null || item.getType() != Material.GLASS_BOTTLE) return;
		if (!item.getItemMeta().getDisplayName().equals("Leather Water Pouch")) return;
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			Block targetBlock = player.getTargetBlockExact(5);
			if (targetBlock != null && targetBlock.getType() == Material.WATER) {
				ItemStack dirtyWater = new ItemStack(Material.POTION);
				ItemMeta meta = dirtyWater.getItemMeta();
				meta.setDisplayName("Dirty Leather Pouch");
				meta.setLore(Arrays.asList("It looks dangerous to drink directly.", "Must be boiled in a furnace."));
				dirtyWater.setItemMeta(meta);
				
				item.setAmount(item.getAmount() - 1);
				player.getInventory().addItem(dirtyWater);
				event.setCancelled(true);
			}
		}
	}
}
