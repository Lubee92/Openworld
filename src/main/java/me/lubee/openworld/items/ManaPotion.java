package me.lubee.openworld.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.lubee.openworld.OpenWorld;

public class ManaPotion {

	public static ItemStack getItem() {
		ItemStack item = new ItemStack(Material.POTION);
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName("Mana Potion");
			NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
			meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mana_potion");
			item.setItemMeta(meta);
		}
		return item;
	}
}
