package mc.stoneskin.MCPI_Plugin;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PluginHelper {
	// private File file;
	private FileConfiguration config;
	private Plugin plugin;

	public PluginHelper(final Plugin plugin) {
		this.plugin = plugin;
		plugin.saveDefaultConfig();;
		config = plugin.getConfig();
	}

	public void ReloadConfig() {
		plugin.reloadConfig();
		config = plugin.getConfig();
	}

	public FileConfiguration getConfig() {
		return config;
	}

	private String[] FormateMessage(Player player, String msg) {
		msg = msg.replaceAll("&", "§").replaceAll("%p", player.getName());
		String lb = "\\n";
		final String[] s = msg.split(lb);
		return s;
	}

	public void SendMessage(Player player, String msg) {
		this.plugin.getLogger().info("player["+player.getName() +"] sending msg:"+msg);
		String[] outs = FormateMessage(player, msg);

		for (String s : outs) {
			player.sendMessage(s);
		}
	}

}