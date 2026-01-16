package me.lubee.openworld.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.OpenWorld;
import me.lubee.openworld.items.LeatherPouch;

@RequiredArgsConstructor
public class LeatherPouchRecipe {
	private final OpenWorld plugin;
	
	public void register() {
		NamespacedKey craftKey = new NamespacedKey(plugin, "leather_pouch_craft");
		ShapedRecipe craftRecipe = new ShapedRecipe(craftKey, LeatherPouch.getItem());
		craftRecipe.shape("L L", "L L", "LLL");
		craftRecipe.setIngredient('L', Material.LEATHER);
		Bukkit.addRecipe(craftRecipe);
		
		NamespacedKey boilKey = new NamespacedKey(plugin, "leather_pouch_boil");
		FurnaceRecipe boilRecipe = new FurnaceRecipe(boilKey,
				LeatherPouch.getCleanItem(),
				Material.POTION,
				0.1f, 200);
		Bukkit.addRecipe(boilRecipe);
	}
}
