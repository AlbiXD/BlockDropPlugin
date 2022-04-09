package albi.Commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class DisableBlocksOnTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 1) {
			ArrayList<String> arguments = new ArrayList<String>();

			arguments.add("reload");
			arguments.add("create");
			arguments.add("delete");

			return arguments;
		}

		if (args[0].toLowerCase().equals("create")) {
			return new ArrayList<String>();
		} else if (args[0].toLowerCase().equals("delete")) {
			return new ArrayList<String>();
		}

		return null;
	}
}
