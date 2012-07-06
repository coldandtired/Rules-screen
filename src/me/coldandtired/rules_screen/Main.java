package me.coldandtired.rules_screen;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Main extends JavaPlugin
{
	public Spout_listener spout_listener = new Spout_listener(this); 
	public Input_listener input_listener = new Input_listener(this); 
	Set<String> new_players = new HashSet<String>();
	FileConfiguration config;
	
	@Override
	public void onDisable() 
	{
		config = null;
		new_players = null;
		input_listener = null;
		spout_listener = null;
		Bukkit.getLogger().info("[Rules Screen] Stopped!");
	}

	@Override
	public void onEnable() 
	{
		config = getConfig();
		config.addDefault("accept_text", "Accept");
		config.addDefault("decline_text", "Decline");
		config.addDefault("kick_text", "You must accept the rules to play here!");
		config.addDefault("kick_on_decline", false);
		config.options().copyDefaults(true);
		saveConfig();
		File f = new File(getDataFolder() + File.separator + "rules.png");
		if (f.exists())
		{
			SpoutManager.getFileManager().addToPreLoginCache(this, f);
		}
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvent(Event.Type.CUSTOM_EVENT, spout_listener, Priority.Normal, this);
		pm.registerEvent(Event.Type.CUSTOM_EVENT, input_listener, Priority.Normal, this);
		Bukkit.getLogger().info("[Rules Screen] Version " + getDescription().getVersion() + " running!");
	}

	void show_rules(SpoutPlayer p)
	{
		if (!new_players.contains(p.getName())) return;
		GenericPopup popup = new GenericPopup();
		File f = new File(getDataFolder() + File.separator + "rules.png");
		if (f.exists())
		{
			GenericTexture t = new GenericTexture(f.getName());
			t.setX(5).setY(5).setHeight(210).setWidth(417);
			popup.attachWidget(this, t);
		}
		Accept_button accept = new Accept_button(this);
		accept.setX(10).setY(220).setWidth(100).setHeight(15);
		accept.setText(config.getString("accept_text"));
		popup.attachWidget(this, accept);
		
		if (config.getBoolean("kick_on_decline", false))
		{
			Decline_button decline = new Decline_button(this);		
			decline.setX(317).setY(220).setWidth(100).setHeight(15);
			decline.setText(config.getString("decline_text"));		
			popup.attachWidget(this, decline);
		}
		p.getMainScreen().attachPopupScreen(popup);
	}
}
