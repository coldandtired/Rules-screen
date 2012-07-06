package me.coldandtired.rules_screen;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Accept_button extends GenericButton
{
	Main plugin;
	
	public Accept_button(Main plugin) 
	{
		this.plugin = plugin;
	}

	@Override
	public
	void onButtonClick(ButtonClickEvent event)
	{
		SpoutPlayer p = event.getPlayer();
		p.getMainScreen().closePopup();
		plugin.new_players.remove(p.getName());
	}
}
