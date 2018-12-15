package com.Cokes_86.MirGame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Cokes_86.MirGame.SelectBox.Box;
import com.Cokes_86.MirGame.SelectBox.BoxGui;
import com.Cokes_86.MirGame.SelectBox.CorrectBox;
import com.Cokes_86.MirGame.SelectBox.RandomBox;
import com.Cokes_86.MirGame.SelectBox.SelectBox;
import com.Cokes_86.MirGame.StartSystem.ClickListener;
import com.Cokes_86.MirGame.StartSystem.StartGame;
import com.Cokes_86.MirGame.UI.GameInventory;
import com.Cokes_86.MirGame.UI.Sliding;

import net.milkbowl.vault.economy.Economy;

public class MirGame extends JavaPlugin{
	
	public GameInventory gi = new GameInventory(this);
	public ClickListener ci = new ClickListener(this);
	public GameCoins gc = new GameCoins();
	public StartGame sg = new StartGame(this);
	public boolean hottime = false;
	public Sliding sl = new Sliding(this);
	
	public BoxGui bg = new BoxGui(this);
	
	public ArrayList<Box> boxs = new ArrayList<>();
	
	public Economy eco = null;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(ci,this);
		getServer().getPluginManager().registerEvents(bg,this);
		setupEconomy();
		
		boxs.add(new SelectBox("test",new ItemStack[]{new ItemStack(Material.DIAMOND_SWORD,1)}));
		boxs.add(new SelectBox("§l슬롯 보상 <배드락>",new ItemStack[]{gc.getEye(5),gc.get완두콩(30),new ItemStack(Material.BEACON,1)}));
		boxs.add(new RandomBox("§l슬롯 보상 <나무>",new ItemStack[]{gc.getCoin(30), gc.get완두콩(5)}));
		boxs.add(new CorrectBox("§l슬롯 보상 <네더의 별>",new ItemStack[]{new ItemStack(Material.BEACON,1), new ItemStack(Material.TOTEM,5)}));
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
			if (label.equals("미르게임") && args.length == 0){
				gi.openMain(p);
			} else if (label.equals("미르게임") && args.length>0 && p.isOp()){
				if (args[0].equals("핫타임") && args.length == 1){
					if (!hottime){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r 핫타임이 시작되었습니다!");
						hottime = true;
					} else {
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r 핫타임이 끝났습니다!");
						hottime = false;
					}
				}
			} else if (label.equals("테스트") && args.length == 0 && p.isOp()){
				p.getInventory().addItem(boxs.get(3).getBox());
				p.getInventory().addItem(boxs.get(1).getBox());
				p.getInventory().addItem(boxs.get(2).getBox());
			}
		} else {
			System.out.println("[미르게임] 콘솔로는 실행할 수 없습니다.");
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
