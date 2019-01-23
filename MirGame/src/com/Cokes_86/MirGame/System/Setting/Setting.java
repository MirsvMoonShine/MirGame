package com.Cokes_86.MirGame.System.Setting;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.Cokes_86.MirGame.MirGame;

public class Setting implements Listener{
	final MirGame m;
	
	public Setting(MirGame main){
		this.m = main;
	}
	
	public void openSetting(Player p){
		File f = new File("plugins/MirGame/Setting/"+p.getUniqueId()+".setting");
		FileConfiguration setting = YamlConfiguration.loadConfiguration(f);
		try { 
			setting.load(f);
		} catch (Exception ex){}
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 설정");
		
		m.gi.setMenu(i);
		
		m.gi.setItem(i, 18, Material.ANVIL, 1, 0, "§r(구)슬롯 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.OldSlot")});
		m.gi.setItem(i, 27, Material.ANVIL, 1, 0, "§r슬롯 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.Slot")});
		m.gi.setItem(i, 36, Material.ANVIL, 1, 0, "§r슬라이딩 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.Sliding")});
		
		if (setting.getBoolean("Sound")) m.gi.setItem(i, 20, Material.ANVIL, 1, 0, "§r소리 설정", new String[]{"§r⇒ 좌클릭시 변경", "§r⇒ 설정: 켜짐"});
		else m.gi.setItem(i, 20, Material.ANVIL, 1, 0, "§r소리 설정", new String[]{"§r⇒ 좌클릭시 변경", "§r⇒ 설정: 꺼짐"});
		
		if (setting.getBoolean("Broadcast")) m.gi.setItem(i, 29, Material.BOOK, 1, 0, "§r보상 획득 개인 안내 메세지", new String[]{"§r⇒ 좌클릭시 변경. (전체 매세지는 송출)", "§r⇒ 설정: 송출함"});
		else m.gi.setItem(i, 29, Material.BOOK, 1, 0, "§r보상 획득 개인 안내 메세지", new String[]{"§r⇒ 좌클릭시 변경. (전체 매세지는 송출)", "§r⇒ 설정: 송출 안함"});
		
		p.openInventory(i);
	}
	
	@EventHandler
	public void onPlayerEnter(PlayerJoinEvent e){
		Player p = e.getPlayer();
		File f = new File("plugins/MirGame/Setting/"+p.getUniqueId()+".setting");
		File dir = new File("plugins/MirGame/Setting");
		FileConfiguration setting = YamlConfiguration.loadConfiguration(f);
		if (!dir.exists()) { dir.mkdirs(); }
		try { 
			if (!f.exists()) {
				setting.save(f);
				setting.set("Speed.OldSlot", 5);
				setting.set("Speed.Slot", 5);
				setting.set("Speed.Sliding", 5);
				setting.set("Sound", true);
				setting.set("Broadcast", true);
				}
			setting.load(f);
		} catch (Exception ex){}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		ClickType c = e.getClick();
		Player p = (Player) e.getWhoClicked();
		File f = new File("plugins/MirGame/Setting/"+p.getUniqueId()+".setting");
		FileConfiguration setting = YamlConfiguration.loadConfiguration(f);
		try { 
			setting.load(f);
		} catch (Exception ex){}
		if (i == p.getInventory() && i.getName().contains(ChatColor.translateAlternateColorCodes('&', "&l미르 게임"))) {e.setCancelled(false);}
		else if (i == null) { return; }
		else if (i.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 설정"))){
			e.setCancelled(true);
			m.ci.menu(e);
			if (Click.getType() == Material.ANVIL && Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()){
				if (Click.getItemMeta().getDisplayName().equals("§r(구)슬롯 속도 설정")){
					long speed = setting.getLong("Speed.OldSlot");
					if (c == ClickType.LEFT && speed != 20){
						long a = speed + 1;
						setting.set("Speed.OldSlot", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 18, Material.ANVIL, 1, 0, "§r(구)슬롯 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.OldSlot")});
					} else if (c == ClickType.RIGHT && speed != 3){
						long a = speed - 1;
						setting.set("Speed.OldSlot", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 18, Material.ANVIL, 1, 0, "§r(구)슬롯 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.OldSlot")});
					} 
				} else if (Click.getItemMeta().getDisplayName().equals("§r슬롯 속도 설정")){
					long speed = setting.getLong("Speed.Slot");
					if (c == ClickType.LEFT && speed != 20){
						long a = speed + 1;
						setting.set("Speed.Slot", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 27, Material.ANVIL, 1, 0, "§r슬롯 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.Slot")});
					} else if (c == ClickType.RIGHT && speed != 3){
						long a = speed - 1;
						setting.set("Speed.Slot", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 27, Material.ANVIL, 1, 0, "§r슬롯 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.Slot")});
					} 
				} else if (Click.getItemMeta().getDisplayName().equals("§r슬라이딩 속도 설정")){
					long speed = setting.getLong("Speed.Sliding");
					if (c == ClickType.LEFT && speed != 20){
						long a = speed + 1;
						setting.set("Speed.Sliding", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 36, Material.ANVIL, 1, 0, "§r슬라이딩 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.Sliding")});
					} else if (c == ClickType.RIGHT && speed != 3){
						long a = speed - 1;
						setting.set("Speed.Sliding", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 36, Material.ANVIL, 1, 0, "§r슬라이딩 속도 설정", new String[]{"§r⇒ 좌클릭시 1씩 증가, 우클릭시 1씩 감소", "§r⇒ 범위: 3 ~ 20 (tick)", "§r⇒ 설정: "+setting.getLong("Speed.Sliding")});
					} 
				} else if (Click.getItemMeta().getDisplayName().equals("§r소리 설정")){
					boolean speed = setting.getBoolean("Sound");
					if (speed){
						boolean a = false;
						setting.set("Sound", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 20, Material.ANVIL, 1, 0, "§r소리 설정", new String[]{"§r⇒ 좌클릭시 변경", "§r⇒ 설정: 꺼짐"});
					} else if (!speed){
						boolean a = true;
						setting.set("Sound", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 20, Material.ANVIL, 1, 0, "§r소리 설정", new String[]{"§r⇒ 좌클릭시 변경", "§r⇒ 설정: 켜짐"});
					} 
				}
			} else if (Click.getType() == Material.BOOK && Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()){
				if (Click.getItemMeta().getDisplayName().equals("§r보상 획득 개인 안내 메세지")){
					boolean br = setting.getBoolean("Broadcast");
					if (br){
						boolean a = false;
						setting.set("Broadcast", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 29, Material.BOOK, 1, 0, "§r보상 획득 개인 안내 메세지", new String[]{"§r⇒ 좌클릭시 변경. (전체 매세지는 송출)", "§r⇒ 설정: 송출 안함"});
					} else {
						boolean a = true;
						setting.set("Broadcast", a);
						try {
							setting.save(f);
						} catch (Exception ex){}
						m.gi.setItem(i, 29, Material.BOOK, 1, 0, "§r보상 획득 개인 안내 메세지", new String[]{"§r⇒ 좌클릭시 변경. (전체 매세지는 송출)", "§r⇒ 설정: 송출함"});
					}
				}
			}
		}
	}
}
