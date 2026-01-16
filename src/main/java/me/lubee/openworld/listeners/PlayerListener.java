package me.lubee.openworld.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.OpenWorld;
import me.lubee.openworld.items.LeatherPouch;
import me.lubee.openworld.player.PlayerData;
import me.lubee.openworld.player.PlayerManager;

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
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
		
		ItemStack item = event.getItem();
		String itemId = getItemId(item);
		
		if ("leather_pouch".equals(itemId)) {
			Player player = event.getPlayer();
			Block targetBlock = player.getTargetBlockExact(5);
			
			if (targetBlock != null && targetBlock.getType() == Material.WATER) {
				event.setCancelled(true);
				item.setAmount(item.getAmount() - 1);
				player.getInventory().addItem(LeatherPouch.getDirtyItem());
			}
		}
	}
	
	@EventHandler
	public void onUseAntiseptic(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		String itemId = getItemId(item);
		
		if ("antiseptic".equals(itemId)) {
			if (!event.getAction().name().contains("RIGHT_CLICK")) return;
			
			Player player = event.getPlayer();
			PlayerData data = playerManager.getPlayerData(player.getUniqueId());
			
			if (data != null && data.getInfection() > 0) {
				event.setCancelled(true);
				data.addInfection(-30.0);
				item.setAmount(item.getAmount() - 1);
			}
		}
	}
	
	@EventHandler
	public void onUseManaPotion(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		String itemId = getItemId(item);
		
		if ("mana_potion".equals(itemId)) {
			if (!event.getAction().name().contains("RIGHT_CLICK")) return;
			
			Player player = event.getPlayer();
			PlayerData data = playerManager.getPlayerData(player.getUniqueId());
			
			if (data != null && data.getMana() < 100) {
				event.setCancelled(true);
				data.addMana(30.0);
				item.setAmount(item.getAmount() - 1);
			}
		}
	}
	
	@EventHandler
	public void onFurnaceStartSmelt(FurnaceStartSmeltEvent event) {
		ItemStack source = event.getSource();
		
		if (source.getType() == Material.POTION) {
			ItemMeta meta = source.getItemMeta();
			if (meta == null) {
				event.setTotalCookTime(0);
				return;
			}
			
			NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
			String itemId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
			
			if (!"dirty_leather_pouch".equals(itemId)) {
				event.setTotalCookTime(0);
			}
		}
	}
	
	private String getItemId(ItemStack item) {
		if (item == null || !item.hasItemMeta()) return null;
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
		return meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
	}
}
