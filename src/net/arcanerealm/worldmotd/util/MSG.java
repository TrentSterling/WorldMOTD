package net.arcanerealm.worldmotd.util;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class MSG 
{
    public static void noPermMessage(Player player, String cmd)
    {
        player.sendMessage(ChatColor.RED+"You don't have permission.");
        Bukkit.getLogger().log(Level.INFO, player.getName()+" was denied access to "+cmd);
    }
    
    
}
