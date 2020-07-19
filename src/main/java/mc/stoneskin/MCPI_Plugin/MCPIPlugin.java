package mc.stoneskin.MCPI_Plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class MCPIPlugin extends JavaPlugin {
    public void onEnable() {
	   
        final PluginHelper helper = new PluginHelper(this);
        this.getCommand("py").setExecutor(new PythonCommand(helper)); 
        this.getLogger().fine("Python MCPI Plugin Activated!"); 
    }

}