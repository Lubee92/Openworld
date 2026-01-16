package me.lubee.openworld.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.lubee.openworld.OpenWorld;

public class Antiseptic {

	public static ItemStack getItem() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName("Medical Antiseptic");
			meta.setLore(Arrays.asList("A spray used to treat zombie infections.", "Reduces infection by 30%."));
			NamespacedKey key = new NamespacedKey(OpenWorld.getInstance(), "item_id");
			meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "antiseptic");
			item.setItemMeta(meta);
		}
		return item;
	}
}
