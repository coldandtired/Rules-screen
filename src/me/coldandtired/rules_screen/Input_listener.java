package me.coldandtired.rules_screen;

import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Input_listener extends InputListener
{
	Main plugin;
	
	public Input_listener(Main plugin) 
	{
		this.plugin = plugin;
	}

	@Override
    public void onKeyReleasedEvent(KeyReleasedEvent event) 
	{
		SpoutPlayer p = event.getPlayer();
		if (!plugin.new_players.contains(p.getName())) return;
		
		if (event.getKey().name().equalsIgnoreCase("KEY_ESCAPE") && p.getMainScreen().getActivePopup() != null); plugin.show_rules(p);
    }
}
