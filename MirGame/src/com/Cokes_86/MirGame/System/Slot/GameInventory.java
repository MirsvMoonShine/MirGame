package com.Cokes_86.MirGame.System.Slot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Cokes_86.MirGame.MirGame;

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
	
	public void openOldSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - (��)����");
		
		setMenu(i);
		m.u.setItem(i, 29, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 31, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 33, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		m.u.setItem(i, 46, Material.BOOK,1,0,"��r����",new String[]{"��r�� 1. ���ʿ� ��ȭ�� ���´�.", "��r�� 2. ������ ������ ������.", "��r�� 3. ���ʿ� ���̾ư� 3�� ������ ����.", "��r�� ��ȭ �� �ٸ� �������� ������ �۵� �ȵ�.", "��r�� ��ȭ�� ���� ü ����� �����."});
		
		m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		m.u.setItem(i, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
		
		p.openInventory(i);
	}
	
	public void openSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - ����");
		
		setMenu(i);
		m.u.setItem(i, 28, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 30, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 32, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 34, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		m.u.setItem(i, 46, Material.BOOK,1,0,"��r����",new String[]{"��r�� 1. ���ʿ� ��ȭ�� ���´�.", "��r�� 2. ������ ������ ������.", "��r�� 3. ���ʿ� ���� �������� 3�� ������ ����.", "��r�� ��ȭ �� �ٸ� �������� ������ �۵� �ȵ�.", "��r�� ��ȭ�� ���� ü ����� �����."});
		
		m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		m.u.setItem(i, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
		
		p.openInventory(i);
	}
	
	public void openRewards(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - ���� ���");
		
		setMenu(i);
		
		for (int a= 0; a<m.boxs.size();a++){
			i.setItem(a+9, m.boxs.get(a).getBox(p));
		}
		
		p.openInventory(i);
	}
	
	public void setMenu(Inventory i){
		ItemStack glass1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)1);
		ItemMeta glass1m = glass1.getItemMeta();
		glass1m.setDisplayName("��6[��9�̸� ���ӡ�6]");
		glass1.setItemMeta(glass1m);
		
		ItemStack glass2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)3);
		ItemMeta glass2m = glass2.getItemMeta();
		glass2m.setDisplayName("��6[��9�̸� ���ӡ�6]");
		glass2.setItemMeta(glass2m);
		
		ItemStack glass3 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)0);
		ItemMeta glass3m =  glass3.getItemMeta();
		glass3m.setDisplayName("��6[��9�̸� ���ӡ�6]");
		glass3.setItemMeta(glass3m);
		
		for (int a = 0;a<9;a++){
			i.setItem(a, glass3);
		}
		
		i.setItem(9, glass1); i.setItem(11, glass1); i.setItem(13, glass1); i.setItem(15, glass1); i.setItem(17, glass1);
		i.setItem(10, glass2); i.setItem(12, glass2); i.setItem(14, glass2); i.setItem(16, glass2);
		i.setItem(45, glass1); i.setItem(47, glass1); i.setItem(49, glass1); i.setItem(51, glass1); i.setItem(53, glass1);
		i.setItem(46, glass2); i.setItem(48, glass2); i.setItem(50, glass2); i.setItem(52, glass2);
		for (int a = 18;a<45;a++){
			m.u.setItem(i,a, Material.STAINED_GLASS_PANE,1,10,"��6[��9�̸� ���ӡ�6]",null);
		}
		
		m.u.setItem(i, 0, Material.CHEST, 1, 0, "��r- �̸� ���� ���� -", new String[]{"��r�� ��Ŭ���� �̸� ���� �������� �̵��մϴ�."} );
		m.u.setItem(i, 1, Material.DIAMOND, 1, 0, "��a- (��)���� -", new String[]{"��r�� ��Ŭ���� (��)�������� �̵��մϴ�."});
		m.u.setItem(i, 2, Material.NETHER_STAR, 1, 0, "��b- ���� -", new String[]{"��r�� ��Ŭ���� �������� �̵��մϴ�."});
		m.u.setItem(i, 3, Material.REDSTONE_LAMP_OFF, 1, 0, "��2- �����̵� -", new String[]{"��r�� ��Ŭ���� �����̵����� �̵��մϴ�."});
		m.u.setItem(i, 4, Material.WOOL, 1, 0, "��4- ���� -", new String[]{"��r�� ��Ŭ���� �������� �̵��մϴ�.","��4�� ������. �̿�Ұ�."});
		m.u.setItem(i, 7, Material.ANVIL, 1, 0, "��r- ���� -", new String[]{"��r�� ��Ŭ���� �������� �̵��մϴ�."});
		m.u.setItem(i, 8, Material.TRAPPED_CHEST, 1, 0, "��r- �̸� ���� ���� ��� -", new String[]{"��r�� ��Ŭ���� �̸� ���� ���� ������� �̵��մϴ�."});
	}
}
