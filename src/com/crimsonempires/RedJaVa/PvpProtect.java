package com.crimsonempires.RedJaVa;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PvpProtect extends JavaPlugin implements Listener{
	String prefix = ChatColor.WHITE + "[" + ChatColor.RED + "PvpArena" + ChatColor.WHITE + "] ";
	ConfigLoader loader;
	List<PvpArena> arenas;
	List<String> banCmds;
	
	
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		loader = new ConfigLoader();
		arenas = new ArrayList<PvpArena>();
		List<String> textArenas = loader.getArenaConfig();
		for(int i = 0; i < textArenas.size(); i ++){
			String[] coords = textArenas.get(i).split(" ");
			double x1 = Double.parseDouble(coords[0]);
			double z1 = Double.parseDouble(coords[1]);
			double x2 = Double.parseDouble(coords[2]);
			double z2 = Double.parseDouble(coords[3]);
			arenas.add(new PvpArena(new Cuboid(x1,z1,x2,z2)));
		}
		banCmds = loader.getCommandConfig();
	}
	
	@Override
	public void onDisable(){
		
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event){
		Player player;
		try{
			player = event.getPlayer();
		}catch(Exception e){
			return;
		}
		if(player == null)
			return;
		Location location = player.getLocation();
		for(int i = 0; i < arenas.size(); i ++){
			if(arenas.get(i).getProtection().inCuboid(location.getX(), location.getY(), location.getZ())){
				if(player.isFlying() && !player.hasPermission("pvpprotect.pvpprotect")){
					player.setFlying(false);
					player.sendMessage(prefix + "You are not allowed to fly in here");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fly " + player.getPlayerListName());
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
		Player player;
		try{
			player = event.getPlayer();
		}catch(Exception e){
			return;
		}
		if(player == null)
			return;
		Location location = player.getLocation();
		boolean inArena = false;
		for(int i = 0; i < arenas.size(); i ++){
			if(arenas.get(i).getProtection().inCuboid(location.getX(), location.getY(), location.getZ())){
				inArena = true;
				break;
			}
		}
		if(!inArena)
			return;
		String message = event.getMessage();
		String[] cmd = message.split(" ");
		for(int i = 0; i < banCmds.size(); i ++){
			if(cmd[0].equalsIgnoreCase(banCmds.get(i)) && !player.hasPermission("pvpprotect.pvpprotect")){
				event.setCancelled(true);
				player.sendMessage(prefix + "You cant use that command in here");
				return;
			}
		}
	}
}
