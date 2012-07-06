package me.coldandtired.rules_screen;

import java.io.File;

import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Spout_listener extends SpoutListener
{
	Main plugin;
	
	public Spout_listener(Main plugin)
	{
		this.plugin = plugin;
	}

	@Override
    public void onSpoutCraftEnable(SpoutCraftEnableEvent event) 
	{
		SpoutPlayer p = event.getPlayer();
		if (!p.isSpoutCraftEnabled()) return;

		File f = new File(p.getWorld().getName() + File.separator + "players" + File.separator + p.getName() + ".dat" );

		if (!f.exists() && !plugin.new_players.contains(p.getName())) plugin.new_players.add(p.getName());
		plugin.show_rules(p);
    }
}
