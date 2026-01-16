package me.lubee.openworld.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.OpenWorld;
import me.lubee.openworld.items.ManaPotion;

@RequiredArgsConstructor
public class ManaPotionRecipe {

	private final OpenWorld plugin;
	
	public void register() {
		NamespacedKey craftKey = new NamespacedKey(plugin, "mana_potion_craft");
		ShapelessRecipe shapeless = new ShapelessRecipe(craftKey, ManaPotion.getItem());
		shapeless.addIngredient(Material.GLASS_BOTTLE);
		shapeless.addIngredient(Material.LAPIS_LAZULI);
		Bukkit.addRecipe(shapeless);
	}
}
