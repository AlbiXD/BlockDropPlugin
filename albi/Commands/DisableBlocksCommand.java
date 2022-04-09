package albi.Commands;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import Main.albi.Main;
import net.md_5.bungee.api.ChatColor;

public class DisableBlocksCommand implements CommandExecutor {

	Plugin plugin = Main.getPlugin();

	Configuration config = plugin.getConfig();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		System.out.println(config.getConfigurationSection("settings").getKeys(true));
		if (args.length > 0) {
			/*
			 * Reloads Configuration
			 * 
			 * @commands: /db reload
			 */
			if (args[0].toLowerCase().equals("reload")) {
				if (sender.hasPermission("db.reload")) {
					plugin.reloadConfig();
					config = plugin.getConfig();
					
					sender.sendMessage(ChatColor.GREEN + "Configuration Reloaded!");
				} else // if no permission
					sender.sendMessage(ChatColor.RED + "Insufficient Permissions");

			}
			/*
			 * Creates permission node
			 * 
			 * @commands: /db create args
			 */
			else if (args[0].toLowerCase().equals("create")) {

				// Checks if permission
				if (sender.hasPermission("db.create")) {
					if (args.length == 2) {

						// checks if rank already exists
						if (config.contains("settings." + args[1])) {
							sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED
									+ " Rank already exists in config");
						} else {
							config.createSection("settings." + args[1]);
							config.addDefault("settings." + args[1] + "." + "disabledBlocks", new ArrayList<String>());
							sender.sendMessage(ChatColor.GREEN + "Rank successfully created!");
							plugin.saveConfig();

						}
					} else
						sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED
								+ " Invalid arguments specify rank please!");

				} else // if no permission
					sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED
							+ " Insufficient Permission");
			} else if (args[0].toLowerCase().equals("delete")) {
				if (sender.hasPermission("db.remove")) {
					if (args.length == 2) {

						if (!config.contains("settings." + args[1])) {
							sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR: " + ChatColor.RED + args[1]
									+ " does not exist");
						} else {

							config.set("settings." + args[1], null);
							plugin.saveConfig();
							sender.sendMessage("Rank removed successfully!");

						}
					} else
						sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED
								+ " Invalid arguments specify rank please!");

				} else
					sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED
							+ " Insufficient permission");
			}

		} else
			sender.sendMessage(
					ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED + " Insufficient arguments");

		return true;
	}

}