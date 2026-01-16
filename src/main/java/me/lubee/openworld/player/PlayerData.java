package me.lubee.openworld.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerData {
	
	private final UUID uuid;
	
	private double thirst;
	private final double maxThirst = 100.0;
	
	public void setThirst(double thirst) {
		this.thirst = Math.clamp(thirst, 0.0, maxThirst);
	}
	
	public void addThirst(double amount) {
		setThirst(this.thirst + amount);
	}
	
	private double stamina;
	private final double maxStamina = 100.0;
	
	public void setStamina(double stamina) {
		this.stamina = Math.clamp(stamina, 0.0, maxStamina);
	}
	
	public void addStamina(double amount) {
		setStamina(this.stamina + amount);
	}
	
	private double infection;
	
	public void setInfection(double infection) {
		this.infection = Math.clamp(infection, 0.0, 100.0);
	}
	
	public void addInfection(double amount) {
		setInfection(this.infection + amount);
	}
	
	private double mana;
	private final double maxMana = 100.0;
	
	public void setMana(double mana) {
		this.mana = Math.clamp(mana, 0.0, maxMana);
	}
	
	public void addMana(double amount) {
		setMana(this.mana + amount);
	}
	
	private Map<String, Integer> skills = new HashMap<>();
	
	private int miningLevel = 1;
	private double miningXp = 0;
	private int miningSkillPoint = 0;
	
	public void setMiningLevel(int level) {
		this.miningLevel = level;
	}
	
	public void addMiningXp(double xp) {
		this.miningXp += xp;
		checkLevelUp();
	}
	
	private void checkLevelUp() {
		double requiredXp = miningLevel * 100;
		if (this.miningXp >= requiredXp) {
			this.miningXp -= requiredXp;
			this.miningLevel++;
			this.miningSkillPoint++;
		}
	}
	
	public int getSkillLevel(String skillId) {
		return skills.getOrDefault(skillId, 0);
	}
	
	public boolean upgradeMiningSkill(String skillId) {
		if (miningSkillPoint > 0) {
			skills.put(skillId, getSkillLevel(skillId) + 1);
			miningSkillPoint--;
			return true;
		}
		return false;
	}
}
