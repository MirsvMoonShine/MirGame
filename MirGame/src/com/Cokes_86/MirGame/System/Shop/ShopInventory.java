package com.Cokes_86.MirGame.System.Shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.Cokes_86.MirGame.MirGame;

public class ShopInventory implements Listener{
	MirGame m;
	
	public ShopInventory(MirGame m) {
		this.m = m;
	}
	
	public void openCoinShop(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - ����");
		
		m.gi.setMenu(i);
		//��ȭ
		m.gi.setItem(i, 18, Material.GOLD_NUGGET, 1, 0, "��r��ȭ 1�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 1�� ����","��r�� ����: 1000��"});
		m.gi.setItem(i, 19, Material.GOLD_NUGGET, 10, 0, "��r��ȭ 10�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 10�� ����","��r�� ����: 9500��"});
		m.gi.setItem(i, 20, Material.GOLD_NUGGET, 64, 0, "��r��ȭ 64�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 64�� ����","��r�� ����: 60000��"});
		m.gi.setItem(i, 21, Material.IRON_NUGGET, 1, 0, "��r�ʿ��� ����", new String[]{"��r�� ��Ŭ���� �ʿ��� ����", "��r�� ����: ĸ�� 5��"});
		m.gi.setItem(i, 22, Material.SLIME_BALL, 10, 0, "��r�ϵ��� 10�� ����", new String[]{"��r�� ��Ŭ���� �ϵ��� 10�� ����", "��r�� ����: ĸ�� 1��"});
		m.gi.setItem(i, 23, Material.EYE_OF_ENDER, 1, 0, "��rĸ�� ����", new String[]{"��r�� ��Ŭ���� ĸ�� ����", "��r�� ����: �ϵ��� 10��"});
		//����
		m.gi.setItem(i, 27, Material.DIAMOND_SWORD, 1, 0, "��r��4���ε� �巡�� �����̾� ��r����", new String[]{"��r�� ��Ŭ���� ���ε� �巡�� �����̾� ����", "��r�� ����: ĸ�� 15��, �ϵ��� 60��"});
		m.gi.setItem(i, 28, Material.DIAMOND_SWORD, 1, 0, "��rĿ�� ���ھ������� Į ����", new String[]{"��r�� ��Ŭ���� Ŀ�� ���ھ������� Į ����", "��r�� ����: �ϵ��� 20��"});
		m.gi.setItem(i, 29, Material.BOW,1 ,0, "��rĿ�� ����������� Ȱ ����",new String[]{"��r�� ��Ŭ���� Ŀ�� ����������� Ȱ ����", "��r�� ����: �ϵ��� 30��"});
		//�׿�
		m.gi.setItem(i, 45, Material.WORKBENCH, 1, 0, "��r�ռ�", new String[]{"��r�� ��Ŭ���� �ռ� â���� �̵�"});
		m.gi.setItem(i, 46, Material.ANVIL, 1, 0, "��r�ʿ�", new String[]{"��r�� ��Ŭ���� �ʿ� â���� �̵�"});
		m.gi.setItem(i, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
		
		if (p.isOp()){ //�׽�Ʈ ��ǰ
			
		}
		
		p.openInventory(i);
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - ����"))){
			e.setCancelled(true);
			m.ci.menu(e);
			if (Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()) {
				
				if (Click.getType() == Material.GOLD_NUGGET){
					if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 1�� ����")){
						if (m.eco.getBalance(p) >= 1000.00){
							Inventory playerinv = p.getInventory();
							playerinv.addItem(m.gc.getCoin(1));
							m.eco.withdrawPlayer(p, 1000);
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��ȭ 1����r�� �����Ͽ����ϴ�.");
							
							m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
						}
					} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 10�� ����")){
						if (m.eco.getBalance(p) >= 9500.00){
							Inventory playerinv = p.getInventory();
							playerinv.addItem(m.gc.getCoin(10));
							m.eco.withdrawPlayer(p, 9500);
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��ȭ 10����r�� �����Ͽ����ϴ�.");
							
							m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
						}
					} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 64�� ����")){
						if (m.eco.getBalance(p) >= 60000.00){
							Inventory playerinv = p.getInventory();
							playerinv.addItem(m.gc.getCoin(64));
							m.eco.withdrawPlayer(p, 60000);
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��ȭ 64����r�� �����Ͽ����ϴ�.");
							
							m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
						}
					}
				}
				else if (Click.getType() == Material.WORKBENCH){
					e.setCancelled(true);
					m.mr.fug.openReadyFusion(p);
				}
				else if (Click.getType() == Material.ANVIL){
					e.setCancelled(true);
					m.mr.fug.openReadyUpgrade(p);
				}
				else if (Click.getType() == Material.IRON_NUGGET){
					Inventory playeriv = p.getInventory();
					boolean eye = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
							if (!eye && stack.getItemMeta().getDisplayName().equals("��dĸ��") && stack.getAmount()>= 5){
								eye = true;
							}
						}
						
						if (eye){
							playeriv.addItem(m.mr.gr.UpgradeStone(1));
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e�ʿ�����r�� �����Ͽ����ϴ�.");
							if (stack.getAmount() == 5) playeriv.setItem(s,null);
							else {
								ItemStack o = stack;
								stack.setAmount(o.getAmount()-5);
							}
							eye = false;
							break;
						}
					}
				}
				else if (Click.getType() == Material.BOW){
					Inventory playeriv = p.getInventory();
					boolean ball = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
							if (!ball && stack.getItemMeta().getDisplayName().equals("��a�ϵ���") && stack.getAmount()>= 30){
								ball = true;
							}
						}
						
						if (ball){
							playeriv.addItem(m.mr.gr.CrokersBow(0));
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r Ŀ�� ��3����������� Ȱ��r�� �����Ͽ����ϴ�.");
							if (stack.getAmount() == 30) playeriv.setItem(s,null);
							else {
								ItemStack o = stack;
								stack.setAmount(o.getAmount()-30);
							}
							ball = false;
							break;
						}
					}
				}
				else if (Click.getType() == Material.DIAMOND_SWORD){
					if (Click.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾� ��r����")){
						Inventory playeriv = p.getInventory();
						boolean eye = false;
						boolean bean = false;
						for (int s=0;s<36;s++){
							ItemStack stack = playeriv.getItem(s);
							
							if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
								if (!eye && stack.getItemMeta().getDisplayName().equals("��dĸ��") && stack.getAmount()>= 15){
									eye = true;
									if (stack.getAmount() == 15) playeriv.setItem(s,null);
									else if (stack.getAmount() > 15) {
										ItemStack o = stack;
										stack.setAmount(o.getAmount()-15);
									}
								}
								if (!bean && stack.getItemMeta().getDisplayName().equals("��a�ϵ���") && stack.getAmount()>= 60){
									bean = true;
									if (stack.getAmount() == 60) playeriv.setItem(s,null);
									else if (stack.getAmount() > 60) {
										ItemStack o = stack;
										stack.setAmount(o.getAmount()-60);
									}
								}
							}
							
							if (eye && bean){
								playeriv.addItem(m.mr.gr.DragonSlayer(0));
								p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��4���ε� �巡�� �����̾��r�� �����Ͽ����ϴ�.");
								break;
							}
						}
					} else if (Click.getItemMeta().getDisplayName().equals("��rĿ�� ���ھ������� Į ����")){
						Inventory playeriv = p.getInventory();
						boolean bean = false;
						for (int s=0;s<36;s++){
							ItemStack stack = playeriv.getItem(s);
							
							if (!bean && stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
									stack.getItemMeta().getDisplayName().equals("��a�ϵ���") && stack.getAmount() >= 20) bean = true;
							
							if (bean){
								playeriv.addItem(m.mr.gr.WatermelonSword(0));
								p.sendMessage("��6[��9�̸� ���ӡ�6]��r Ŀ�� ��r��2���ھ������� Į��r�� �����Ͽ����ϴ�.");
								if (stack.getAmount() == 20){
									playeriv.setItem(s, null);
								} else if (stack.getAmount()>20){
									ItemStack s2 = stack;
									stack.setAmount(s2.getAmount() -20);
								}
								break;
							}
						}
					}
				}
				else if (Click.getItemMeta().getDisplayName().equals("��r�ϵ��� 10�� ����")) {
					Inventory playeriv = p.getInventory();
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
								stack.getItemMeta().getDisplayName().equals("��dĸ��") && stack.getAmount() >= 1) {
							if (stack.getAmount() == 1) playeriv.setItem(s, null);
							else {
								ItemStack s2 = stack;
								stack.setAmount(s2.getAmount() - 1);
							}
							
							playeriv.addItem(m.gc.getBean(10));
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��a�ϵ����r 10���� �����Ͽ����ϴ�.");
							break;
						}
						
					}
				}
				else if (Click.getItemMeta().getDisplayName().equals("��rĸ�� ����")) {
					Inventory playeriv = p.getInventory();
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
								stack.getItemMeta().getDisplayName().equals("��a�ϵ���") && stack.getAmount() >= 10) {
							if (stack.getAmount() == 10) playeriv.setItem(s, null);
							else {
								ItemStack s2 = stack;
								stack.setAmount(s2.getAmount() - 10);
							}
							
							playeriv.addItem(m.gc.getEye(1));
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��dĸ����r�� �����Ͽ����ϴ�.");
							break;
						}
						
					}
				}
				
			}
		}
	}
}
