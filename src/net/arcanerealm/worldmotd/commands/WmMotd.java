package net.arcanerealm.worldmotd.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class WmMotd implements CommandExecutor
{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args)
    {
        
        if(lbl.equalsIgnoreCase("wmotd"))
        {
            if(args.length == 0)
            {
                Player player = (Player) sender;
                Location loc = Bukkit.getWorld("world_nether").getSpawnLocation();
                player.teleport(loc);
                return true;
            }
            if(args[0].equalsIgnoreCase("t"))
            {
                Player player = (Player) sender;
                Location loc = Bukkit.getWorld("world").getSpawnLocation();
                player.teleport(loc);
                return true;
            }
        }
        return false;
    }

}
