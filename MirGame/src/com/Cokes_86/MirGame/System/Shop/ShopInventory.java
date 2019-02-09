package com.Cokes_86.MirGame.System.Shop;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
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
		m.u.setItem(i, 18, Material.GOLD_NUGGET, 1, 0, "��r��ȭ 1�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 1�� ����","��r�� ����: 1000��"});
		m.u.setItem(i, 19, Material.GOLD_NUGGET, 10, 0, "��r��ȭ 10�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 10�� ����","��r�� ����: 9500��"});
		m.u.setItem(i, 20, Material.GOLD_NUGGET, 64, 0, "��r��ȭ 64�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 64�� ����","��r�� ����: 60000��"});
		m.u.setItem(i, 21, Material.IRON_NUGGET, 1, 0, "��r�ʿ��� ����", new String[]{"��r�� ��Ŭ���� �ʿ��� ����", "��r�� ����: ĸ�� 5��"});
		m.u.setItem(i, 22, Material.SLIME_BALL, 10, 0, "��r�ϵ��� 10�� ����", new String[]{"��r�� ��Ŭ���� �ϵ��� 10�� ����", "��r�� ����: ĸ�� 1��"});
		m.u.setItem(i, 23, Material.EYE_OF_ENDER, 1, 0, "��rĸ�� ����", new String[]{"��r�� ��Ŭ���� ĸ�� ����", "��r�� ����: �ϵ��� 10��"});
		m.u.setItem(i, 24, Material.STICK, 1, 0, "��r���� ���� ����", new String[]{"��r�� ��Ŭ���� ���� ���� ����", "��r�� ����: ������ ���� 1��", "��r�� ���� ���� öȸ�� ���� �߰�"});
		//����
		m.u.setItem(i, 27, Material.DIAMOND_SWORD, 1, 0, "��r��4���ε� �巡�� �����̾� ��r����", new String[]{"��r�� ��Ŭ���� ���ε� �巡�� �����̾� ����", "��r�� ����: ĸ�� 15��, �ϵ��� 60��"});
		m.u.setItem(i, 28, Material.DIAMOND_SWORD, 1, 0, "��rĿ�� ���ھ������� Į ����", new String[]{"��r�� ��Ŭ���� Ŀ�� ���ھ������� Į ����", "��r�� ����: �ϵ��� 20��"});
		m.u.setItem(i, 29, Material.BOW,1 ,0, "��rĿ�� ����������� Ȱ ����",new String[]{"��r�� ��Ŭ���� Ŀ�� ����������� Ȱ ����", "��r�� ����: �ϵ��� 30��"});
		//�ʿ�
		m.u.setPotionItem(i, 36, 1, "��r�ʿ��� ��ȣ ���� ����", new String[] {"��r�� ��Ŭ���� �ʿ��� ��ȣ ���� ����", "��r�� ����: �ϵ��� 30��"}, Color.AQUA);
		m.u.setPotionItem(i, 37, 1, "��rȮ�� ��� ���� ����", new String[] {"��r�� ��Ŭ���� Ȯ�� ��� ���� ����", "��r�� ����: �ϵ��� 50��"}, Color.WHITE);
		//��Ÿ
		m.u.setItem(i, 45, Material.WORKBENCH, 1, 0, "��r�ռ�", new String[]{"��r�� ��Ŭ���� �ռ� â���� �̵�"});
		m.u.setItem(i, 46, Material.ANVIL, 1, 0, "��r�ʿ�", new String[]{"��r�� ��Ŭ���� �ʿ� â���� �̵�"});
		m.u.setItem(i, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
		
		if (p.getName().equals("Cokes_86")){ //�׽�Ʈ ��ǰ
			//��ȭ
			
			//����
			
			//�ʿ�
			
			//��Ÿ
			
		}
		
		p.openInventory(i);
	}
	
	public boolean ShopRecipe() {
		return false;
	}
	
	public void getItemUsingVault(Player p, ItemStack get, double money) {
		if (m.eco.getBalance(p) >= money){
			Inventory playerinv = p.getInventory();
			m.eco.withdrawPlayer(p, money);
			p.sendMessage("��6[��9�̸� ���ӡ�6]��r "+get.getItemMeta().getDisplayName()+" ��r"+get.getAmount()+"���� �����Ͽ����ϴ�.");
			playerinv.addItem(get);
		} else {
			p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���� �����մϴ�.");
		}
	}
	
	public void getItemUsingIngradient(Player p, ItemStack get, ItemStack[] input) {
		List<ItemStack> list = Arrays.asList(input);
		boolean[] ok = new boolean[list.size()];
		int[] stack = new int[list.size()];
		boolean result = true;
		Inventory i = p.getInventory();
		for (int a=0;a<list.size();a++) {
			ok[a] = false; stack[a] = 0;
		}
		for (int s=0;s<36;s++){
			ItemStack is = i.getItem(s);
			if (is != null) {
				for (int a = 0;a<list.size();a++) {
					if (!ok[a] && is.hasItemMeta() && is.getItemMeta().hasDisplayName() && is.getItemMeta().getDisplayName().equals(list.get(a).getItemMeta().getDisplayName()) && is.getAmount() >= list.get(a).getAmount()) {
						ok[a] = true; stack[a] = s;
					}
				}
			}
		}
		for (int a=0;a<list.size();a++) {
			if (!ok[a]) result = false;
		}
		
		if (result) {
			for (int a=0;a<list.size();a++) {
				if (i.getItem(stack[a]).getAmount() == list.get(a).getAmount()) i.setItem(stack[a],null);
				else {
					ItemStack o = i.getItem(stack[a]);
					ItemStack bak = list.get(a);
					bak.setAmount(o.getAmount() - list.get(a).getAmount());
					i.setItem(stack[a], bak);
				}
			}
			
			p.sendMessage("��6[��9�̸� ���ӡ�6]��r "+get.getItemMeta().getDisplayName()+" ��r"+get.getAmount()+"���� �����Ͽ����ϴ�.");
			i.addItem(get);
		} else {
			p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��ᰡ �����մϴ�.");
		}
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
						getItemUsingVault(p,m.gc.getCoin(1),1000);
						m.u.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 10�� ����")){
						getItemUsingVault(p,m.gc.getCoin(10),9500);
						m.u.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 64�� ����")){
						getItemUsingVault(p,m.gc.getCoin(64),60000);
						m.u.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					}
				}
				else if (Click.getType() == Material.WORKBENCH){
					m.mr.fug.openReadyFusion(p);
				}
				else if (Click.getType() == Material.ANVIL){
					m.mr.fug.openReadyUpgrade(p);
				}
				else if (Click.getType() == Material.IRON_NUGGET){
					getItemUsingIngradient(p, m.mr.gr.UpgradeStone(1), new ItemStack[] {m.gc.getEye(5)});
				}
				else if (Click.getType() == Material.BOW){
					getItemUsingIngradient(p, m.mr.gr.CrokersBow(0), new ItemStack[] {m.gc.getBean(30)});
				}
				else if (Click.getType() == Material.DIAMOND_SWORD){
					if (Click.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾� ��r����")){
						getItemUsingIngradient(p, m.mr.gr.DragonSlayer(0), new ItemStack[] {m.gc.getEye(15), m.gc.getBean(60)});
					} else if (Click.getItemMeta().getDisplayName().equals("��rĿ�� ���ھ������� Į ����")){
						getItemUsingIngradient(p, m.mr.gr.WatermelonSword(0), new ItemStack[] {m.gc.getBean(20)});
					}
				}
				else if (Click.getItemMeta().getDisplayName().equals("��r�ϵ��� 10�� ����")) {
					getItemUsingIngradient(p, m.gc.getBean(10), new ItemStack[] {m.gc.getEye(1)});
				}
				else if (Click.getItemMeta().getDisplayName().equals("��rĸ�� ����")) {
					getItemUsingIngradient(p, m.gc.getEye(1), new ItemStack[] {m.gc.getBean(10)});
				}
				else if (Click.getType() == Material.POTION) {
					if (Click.getItemMeta().getDisplayName().equals("��r�ʿ��� ��ȣ ���� ����")) {
						getItemUsingIngradient(p, m.mr.gr.RerollStone(), new ItemStack[] {m.gc.getBean(30)});
					} else if (Click.getItemMeta().getDisplayName().equals("��rȮ�� ��� ���� ����")) {
						getItemUsingIngradient(p, m.mr.gr.SuccessUp(), new ItemStack[] {m.gc.getBean(50)});
					}
				}
				else if (Click.getType() == Material.STICK) {
					getItemUsingIngradient(p, m.gc.getTool(1), new ItemStack[] {m.gc.getCloth(1)});
				}
			}
		}
	}
}
