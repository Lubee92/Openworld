package me.lubee.openworld.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.OpenWorld;

@RequiredArgsConstructor
public class LeatherPouch {
	public static ItemStack getItem() {
		ItemStack item = new ItemStack(Material.GLASS_BOTTLE);
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName("Leather Water Pouch");
			meta.setLore(Arrays.asList("A leather pouch that can hold water."));
			NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
			meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "leather_pouch");
			item.setItemMeta(meta);
		}
		return item;
	}
	
	public static ItemStack getDirtyItem() {
		ItemStack item = new ItemStack(Material.POTION);
		ItemMeta meta = item.getItemMeta();
		if(meta != null) {
			meta.setDisplayName("Dirty Leather Pouch");
			meta.setLore(Arrays.asList("It looks dangerous to drink directly.", "Must be boiled in a furnace."));
			NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
			meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "dirty_leather_pouch");
			item.setItemMeta(meta);
		}
		return item;
	}
	
	public static ItemStack getCleanItem() {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta) item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName("Clean Leather Pouch");
			meta.setLore(Arrays.asList("The water is now safe to drink"));
			NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
			meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "clean_leather_pouch");
			meta.setBasePotionType(PotionType.WATER);
			item.setItemMeta(meta);
		}
		return item;
	}
}
