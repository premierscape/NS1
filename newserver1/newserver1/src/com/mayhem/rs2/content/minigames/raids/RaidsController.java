package com.mayhem.rs2.content.minigames.raids;

import com.mayhem.core.task.TaskQueue;
import com.mayhem.core.task.impl.MobWalkTask;
import com.mayhem.rs2.content.combat.Combat.CombatTypes;
import com.mayhem.rs2.entity.Entity;
import com.mayhem.rs2.entity.Location;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.controllers.Controller;
import com.mayhem.rs2.entity.player.net.out.impl.SendWalkableInterface;

public final class RaidsController extends Controller {

	public static final String MINIGAME = "Raids";

	@Override
	public boolean allowMultiSpells() {
		return true;
	}

	@Override
	public boolean allowPvPCombat() {
		return false;
	}

	@Override
	public boolean canAttackNPC() {
		return true;
	}

	@Override
	public boolean canAttackPlayer(Player p, Player p2) {
		return false;
	}

	@Override
	public boolean canClick() {
		return true;
	}

	@Override
	public boolean canDrink(Player p) {
		return true;
	}

	@Override
	public boolean canEat(Player p) {
		return true;
	}

	@Override
	public boolean canEquip(Player p, int id, int slot) {
		return true;
	}

	@Override
	public boolean canUnequip(Player player) {
		return true;
	}

	@Override
	public boolean canDrop(Player player) {
		return true;
	}

	@Override
	public boolean canLogOut() {
		return true;
	}

	@Override
	public boolean canMove(Player p) {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public boolean canTalk() {
		return true;
	}

	@Override
	public boolean canTeleport() {
		return false;
	}

	@Override
	public boolean canTrade() {
		return false;
	}

	@Override
	public boolean canUseCombatType(Player p, CombatTypes type) {
		return true;
	}

	@Override
	public boolean canUsePrayer(Player p, int id) {
		return true;
	}

	@Override
	public boolean canUseSpecialAttack(Player p) {
		return true;
	}

	@Override
	public Location getRespawnLocation(Player player) {
		return new Location(2495, 5110, player.getRaids().getHeight(player));
	}

	@Override
	public boolean isSafe(Player player) {
		return true;
	}

	@Override
	public void onControllerInit(Player p) {
		//p.send(new SendWalkableInterface(17500));
	}

	@Override
	public void onDeath(Player p) {
		p.teleport(new Location(p.getRaids().getStartLocation().getX(),p.getRaids().getStartLocation().getY(),p.getRaids().getHeight(p)));
		p.getRaids().currentRoom = 0;
	}

	@Override
	public void onDisconnect(Player p) {
		p.getRaids().leaveGame(p);
	}

	@Override
	public void onTeleport(Player p) {
	}


	@Override
	public void tick(Player p) {
	}

	@Override
	public String toString() {
		return "Raids";
	}

	@Override
	public boolean transitionOnWalk(Player p) {
		return false;
	}

	@Override
	public void onKill(Player player, Entity killed) {

	}
}
