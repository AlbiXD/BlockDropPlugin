package Main.albi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import albi.Commands.DisableBlocksCommand;
import albi.Commands.DisableBlocksOnTab;
import albi.EventListener.EventListener;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	public static Main plugin;

	@Override
	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin Enabled v0.0.3 - @Albi");

		Bukkit.getPluginManager().registerEvents(new EventListener(), this);

		getConfig().options().copyDefaults(true);

		saveConfig();

		getCommand("db").setExecutor(new DisableBlocksCommand());
		getCommand("db").setTabCompleter(new DisableBlocksOnTab());

	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Plugin Disabled v0.0.3 - @Albi");
		plugin = null;
	}

	public static Main getPlugin() {
		return plugin;

	}
	

}
