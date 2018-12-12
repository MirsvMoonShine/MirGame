package com.Cokes_86.MirGame;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Cokes_86.MirGame.SelectBox.BoxGui;

import net.milkbowl.vault.economy.Economy;

public class MirGame extends JavaPlugin{
	
	public GameInventory gi = new GameInventory(this);
	public ClickListener ci = new ClickListener(this);
	public GameCoins gc = new GameCoins();
	public StartGame sg = new StartGame(this);
	public boolean hottime = false;
	
	public BoxGui bg = new BoxGui();
	
	public Economy eco = null;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(ci,this);
		getServer().getPluginManager().registerEvents(bg,this);
		setupEconomy();
	}
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }

        return (eco != null);
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			Player p = (Player) sender;
			if (label.equals("�̸�����") && args.length == 0){
				gi.openMain(p);
			} else if (label.equals("�̸�����") && args.length>0 && p.isOp()){
				if (args[0].equals("��Ÿ��") && args.length == 1){
					if (!hottime){
						Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��Ÿ���� ���۵Ǿ����ϴ�!");
						hottime = true;
					} else {
						Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��Ÿ���� �������ϴ�!");
						hottime = false;
					}
				}
			} else if (label.equals("�׽�Ʈ") && args.length == 1){
				if (args[0].equals("����")){
					bg.BoxOpenReady(p, new ItemStack[]{gc.get�ϵ���(20),gc.getCoin(64)}, "���� ���� (����)");
				} else if (args[0].equals("Ȯ��")){
					bg.BoxOpenConfirm(p, gc.get�ϵ���(20), "���� ���� (����)");
				}
			}
		} else {
			System.out.println("[�̸�����] �ַܼδ� ������ �� �����ϴ�.");
		}
		return false;
	}
	
	public void firework(Player p){
		Firework fw = (Firework) p.getWorld().spawn(p.getLocation(),Firework.class);
		FireworkMeta fm = fw.getFireworkMeta();
		FireworkEffect fe = FireworkEffect.builder().flicker(true).with(Type.BALL).withColor(Color.GREEN).build();
		fm.addEffect(fe);
		fw.setFireworkMeta(fm);
	}
}
