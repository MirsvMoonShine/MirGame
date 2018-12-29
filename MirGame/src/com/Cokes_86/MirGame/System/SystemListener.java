package com.Cokes_86.MirGame.System;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Cokes_86.MirGame.MirGame;

public class SystemListener implements Listener {
	final MirGame m;
	
	public SystemListener (MirGame main){
		this.m = main;
	}
	
	@EventHandler
	public void clickevent(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK
				|| e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			ItemStack main = p.getInventory().getItemInMainHand();
			if (main != null && main.hasItemMeta() && main.getItemMeta().hasDisplayName() && main.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어")){
				p.getInventory().setItemInMainHand(null);
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 0.5F, 0);
				p.sendMessage("§4§l아직 그대에겐 이 검을 사용할 자격이 주어지지 않았다.");
			}
			else if (main != null && main.hasItemMeta() && main.getItemMeta().hasDisplayName() && main.getItemMeta().getDisplayName().equals("§d캡슐")){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void clickentityevent(EntityDamageByEntityEvent e){
		Entity entity = e.getEntity();
		if (entity instanceof Player){
			Player p = (Player) entity;
			ItemStack mainhand = p.getInventory().getItemInMainHand();
			if (mainhand != null && mainhand.hasItemMeta() && mainhand.getItemMeta().hasDisplayName() && mainhand.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어")){
				p.getInventory().setItemInMainHand(null);
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 0.5F, 0);
				p.sendMessage("§4§l아직 그대에겐 이 검을 사용할 자격이 주어지지 않았다.");
			}
		}
	}
}
