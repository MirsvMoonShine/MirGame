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
	    	Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��Ÿ���� ���۵Ǿ����ϴ�! (������: (��)����)");
	    } else if (hour >= 23 && hour < 21 && m.hottime) {
	    	Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��Ÿ���� �������ϴ�!");
			m.hottime = false;
	    }
	}
}
