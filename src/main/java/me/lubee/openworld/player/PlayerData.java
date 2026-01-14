package me.lubee.openworld.player;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerData {
	
	private final UUID uuid;
	
	private double thirst;
	private final double maxThirst = 100.0;
	
	private double stamina;
	private final double maxStamina = 100.0;
	
	private double infection;
	
	public void setThirst(double thirst) {
		this.thirst = Math.clamp(thirst, 0.0, maxThirst);
	}
	
	public void setStamina(double stamina) {
		this.stamina = Math.clamp(stamina, 0.0, maxStamina);
	}
	
	public void setInfection(double infection) {
		this.infection = Math.clamp(infection, 0.0, 100.0);
	}
	
	public void addThirst(double amount) {
		setThirst(this.thirst + amount);
	}
	
	public void addStamina(double amount) {
		setStamina(this.stamina + amount);
	}
	
	public void addInfection(double amount) {
		setInfection(this.infection + amount);
	}
}
