package me.lubee.openworld.guis;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.player.PlayerData;
import me.lubee.openworld.player.PlayerManager;

@RequiredArgsConstructor
public class SkillMenu {
	private final PlayerManager playerManager;
	
	public void openSkillMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "Mining Skill Tree");
		PlayerData data = playerManager.getPlayerData(player.getUniqueId());
		
		ItemStack passive1 = new ItemStack(Material.GOLDEN_PICKAXE);
		ItemMeta meta = passive1.getItemMeta();
		meta.setDisplayName("Passive : Efficiency");
		meta.setLore(Arrays.asList("Current Level : " + data.getSkillLevel("efficiency_passive"),
				"Required: 1 Skill Point",
				"Gives Haste effect while mining."));
		passive1.setItemMeta(meta);
		
		inv.setItem(11, passive1);
		player.openInventory(inv);
	}
}
