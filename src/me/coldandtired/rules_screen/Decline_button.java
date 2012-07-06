package me.coldandtired.rules_screen;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

public class Decline_button extends GenericButton
{
	Main plugin;
	
	public Decline_button(Main plugin) 
	{
		this.plugin = plugin;
	}

	@Override
	public
	void onButtonClick(ButtonClickEvent event)
	{
		event.getPlayer().kickPlayer(plugin.config.getString("kick_text"));
	}
}
