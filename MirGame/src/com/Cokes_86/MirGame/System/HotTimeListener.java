package com.Cokes_86.MirGame.System;

import java.time.LocalDateTime;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.Cokes_86.MirGame.MirGame;

public class HotTimeListener implements Listener {
	MirGame m;
	
	public HotTimeListener(MirGame m) {
		this.m = m;
	}
	
	@EventHandler
	public void OnCreatureSpawn(CreatureSpawnEvent e) {
		LocalDateTime now = LocalDateTime.now();
	    int hour = now.getHour();
	    
	    if (hour >= 21 && hour < 23 && !m.hottime) { // 21:00 ~ 23:00
	    	m.hottime = true;
	    	Bukkit.broadcastMessage("§6[§9미르 게임§6]§r 핫타임이 시작되었습니다! (적용대상: (구)슬롯)");
	    } else if (hour >= 23 && hour < 21 && m.hottime) {
	    	Bukkit.broadcastMessage("§6[§9미르 게임§6]§r 핫타임이 끝났습니다!");
			m.hottime = false;
	    }
	}
}
