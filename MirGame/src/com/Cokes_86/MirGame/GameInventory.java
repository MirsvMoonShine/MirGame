package com.Cokes_86.MirGame;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameInventory {
	
    final MirGame m;
	
	public GameInventory (MirGame main){
		this.m = main;
	}
	
	public void openMain(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ����");
		
		setMenu(i);
		
		p.openInventory(i);
	}
	
	public void openCoinShop(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - ���λ���");
		
		setMenu(i);
		setItem(i, 18, Material.GOLD_NUGGET, 1, 0, "��r��ȭ 1�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 1�� ����","��r�� ����: 1000��"});
		setItem(i, 19, Material.GOLD_NUGGET, 10, 0, "��r��ȭ 10�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 10�� ����","��r�� ����: 9500��"});
		setItem(i, 20, Material.GOLD_NUGGET, 64, 0, "��r��ȭ 64�� ����", new String[]{"��r�� ��Ŭ���� ��ȭ 64�� ����","��r�� ����: 60000��"});
		
		setItem(i, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
		
		p.openInventory(i);
	}
	
	public void openOldSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - (��)����");
		
		setMenu(i);
		setItem(i, 29, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 31, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 33, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		setItem(i, 46, Material.BOOK,1,0,"��r����",new String[]{"��r�� 1. ���ʿ� ��ȭ�� ���´�.", "��r�� 2. ������ ������ ������.", "��r�� 3. ���ʿ� ���̾ư� 3�� ������ ����.", "��r�� ��ȭ �� �ٸ� �������� ������ �۵� �ȵ�.", "��r�� ��ȭ�� ���� ü ����� �����."});
		
		setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		setItem(i, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
		
		p.openInventory(i);
	}
	
	public void openSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - ����");
		
		setMenu(i);
		setItem(i, 29, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 31, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 33, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		setItem(i, 46, Material.BOOK,1,0,"��r����",new String[]{"��r�� 1. ���ʿ� ��ȭ�� ���´�.", "��r�� 2. ������ ������ ������.", "��r�� 3. ���ʿ� ���� �������� 3�� ������ ����.", "��r�� ��ȭ �� �ٸ� �������� ������ �۵� �ȵ�.", "��r�� ��ȭ�� ���� ü ����� �����."});
		
		setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		setItem(i, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
		
		p.openInventory(i);
	}
	
	public void setMenu(Inventory i){
		ItemStack glass1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)1);
		ItemMeta glass1m = glass1.getItemMeta();
		glass1m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6[&9�̸� ����&6]"));
		glass1.setItemMeta(glass1m);
		
		ItemStack glass2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)3);
		ItemMeta glass2m = glass2.getItemMeta();
		glass2m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6[&9�̸� ����&6]"));
		glass2.setItemMeta(glass2m);
		
		i.setItem(9, glass1); i.setItem(11, glass1); i.setItem(13, glass1); i.setItem(15, glass1); i.setItem(17, glass1);
		i.setItem(10, glass2); i.setItem(12, glass2); i.setItem(14, glass2); i.setItem(16, glass2);
		i.setItem(45, glass1); i.setItem(47, glass1); i.setItem(49, glass1); i.setItem(51, glass1); i.setItem(53, glass1);
		i.setItem(46, glass2); i.setItem(48, glass2); i.setItem(50, glass2); i.setItem(52, glass2);
		for (int a = 18;a<45;a++){
			setItem(i,a, Material.STAINED_GLASS_PANE,1,10,"��6[��9�̸� ���ӡ�6]",null);
		}
		
		setItem(i, 0, Material.CHEST, 1, 0, "��r- ���λ��� -", new String[]{"��r�� ��Ŭ���� ���� �������� �̵��մϴ�."} );
		setItem(i, 1, Material.DIAMOND, 1, 0, "��a- (��)���� -", new String[]{"��r�� ��Ŭ���� (��)�������� �̵��մϴ�."});
		setItem(i, 2, Material.NETHER_STAR, 1, 0, "��b- ���� -", new String[]{"��r�� ��Ŭ���� �������� �̵��մϴ�."});
	}
	
	public void setItem(Inventory i, int slot, Material m, int ammount, int data, String name, String[] lore){
		ItemStack result = new ItemStack(m,ammount,(short)data);
		ItemMeta resultm = result.getItemMeta();
		resultm.setDisplayName(name);
		if (lore != null){
			List<String> lore2 = Arrays.asList(lore);
			resultm.setLore(lore2);
		}
		result.setItemMeta(resultm);
		
		i.setItem(slot, result);
	}
}
