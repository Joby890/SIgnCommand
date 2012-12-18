package com.admixhosting.plugins;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable() {
	}
	@EventHandler
	public void OnSignClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase("[command]")) {
					e.getPlayer().performCommand(s.getLine(1));
				}
			}
		}
	}
	@EventHandler
	public void OnSignPlace(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[command]")) {
			if (!e.getPlayer().hasPermission("signcommand.create")|| !(e.getPlayer().isOp())) {
				e.setCancelled(true);
			} else {
				e.getPlayer().sendMessage("SignCommand Created!");
			}
			
		}
	}

}
