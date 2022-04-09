package albi.EventListener;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import Main.albi.Main;

public class EventListener implements Listener {

	static Plugin plugin = Main.getPlugin();
	static Configuration config = plugin.getConfig();
	public static String[] configList = config.getConfigurationSection("settings").getKeys(true)
			.toArray(new String[config.getConfigurationSection("settings").getKeys(true).size()]);
	public static ArrayList<String> ranks = new ArrayList<String>(Arrays.asList(configList));
	public static String playerRank;
	public static List<String> blocks = new ArrayList<String>();

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		//
		Player p = e.getPlayer();
		configList = config.getConfigurationSection("settings").getKeys(true)
				.toArray(new String[config.getConfigurationSection("settings").getKeys(true).size()]);
		ranks = new ArrayList<String>(Arrays.asList(configList));
		checkPermission(p, ranks);
		blocks = config.getStringList("settings." + playerRank + ".disabledBlocks");

		Block block = e.getBlock();

		if (blocks.contains(block.getType().toString())) {
			block.setType(Material.AIR);
		}

	}

	public boolean checkPermission(Player p, ArrayList<String> ranks) {
		for (int i = 0; i < ranks.size(); i++) {
			if (p.hasPermission("db." + ranks.get(i))) {
				playerRank = ranks.get(i);
				return true;
			}
		}
		return false;

	}

}
