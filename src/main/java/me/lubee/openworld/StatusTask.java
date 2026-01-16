package me.lubee.openworld;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.RequiredArgsConstructor;
import me.lubee.openworld.player.PlayerData;
import me.lubee.openworld.player.PlayerManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

@RequiredArgsConstructor
public class StatusTask extends BukkitRunnable {

	private final PlayerManager playerManager;
	private int tickCount = 0;

	@Override
	public void run() {
		tickCount++;

		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerData data = playerManager.getPlayerData(player.getUniqueId());
			
			if (data == null) continue;
			
			int miningLevel = data.getMiningLevel();
			if (miningLevel >= 5) {
				int hasteLevel = (miningLevel / 5) - 1;
				if (player.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, hasteLevel, false, false));
				}
			}
			
			data.addThirst(-0.05);
			
			if (data.getThirst() <= 0) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 1));
				if (tickCount % 4 == 0) {
					player.damage(1.0);
				}
			}
			
			double infection = data.getInfection();
			
			if (infection >= 50.0) {
				data.addStamina(-0.15);
			}
			
			if (infection >= 80.0) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 0));
			}
			
			if (infection >= 100.0) {
				if (tickCount % 4 == 0) {
					player.damage(1.0);
				}
			}
			
			String message = String.format("Mana : %d%%, Thirst : %d%%, Stamina : %d%%, Infection : %d%%",
					(int) data.getMana(),
					(int) data.getThirst(),
					(int) data.getStamina(),
					(int) data.getInfection());
			
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
		}
		
		if (tickCount >= 200) tickCount = 0;
	}
}
