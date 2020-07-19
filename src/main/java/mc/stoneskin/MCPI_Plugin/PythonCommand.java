package mc.stoneskin.MCPI_Plugin;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PythonCommand implements CommandExecutor {
    private final PluginHelper _helper;

    public PythonCommand(final PluginHelper helper) {
        _helper = helper;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Bukkit.getServer().getLogger().info("Python command be called,sender: " + sender.getName() + " command:"
                + command.getName() + " label:" + label);
        if (args.length > 0 && (sender instanceof Player)) {
            Player player = (Player) sender;
            if ("reload".equals(args[0])) {
                _helper.ReloadConfig();
                _helper.SendMessage(player, "Run Python path reloaded. " );
                return true;
            } else {
               
                return RunPythonCommand(player, args);
            }

        }

        return false;
    }

    private boolean RunPythonCommand(Player player, String[] args) {
        // String cmd = "py C:\\Python\\test\\test.py";

        try {
            String cmd = _helper.getConfig().getString("py.python_command_template");
            cmd = cmd.replace("{name}", args[0]) + " " + player.getName();
            _helper.SendMessage(player, "Run python command: " + cmd);
            Process process = Runtime.getRuntime().exec(cmd);

            // todo:
            // // deal with OutputStream to send inputs
            // process.getOutputStream();

            // // deal with InputStream to get ordinary outputs
            // process.getInputStream();

            // // deal with ErrorStream to get error outputs
            // process.getErrorStream();
            // BufferedReader reader = new BufferedReader(new
            // InputStreamReader(process.getInputStream()));
            // String line;
            // while ((line = reader.readLine()) != null) {
            // System.out.println(line);
            // }
            // reader.close();
            return true;
        } catch (Exception e) {
            _helper.SendMessage(player, e.getMessage());
            e.printStackTrace();

        }
        return false;
    }
}