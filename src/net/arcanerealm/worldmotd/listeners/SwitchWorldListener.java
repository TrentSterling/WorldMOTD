package net.arcanerealm.worldmotd.listeners;

import java.util.List;
import net.arcanerealm.worldmotd.Motd;
import net.arcanerealm.worldmotd.WorldMotdAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 *
 * @author Kenny
 */
public class SwitchWorldListener implements Listener
{
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event)
    {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();
        
        if(!player.hasPermission("worldmotd.switchworld.motd.*"))
        {
            if(!player.hasPermission("worldmotd.switchworld.motd."+world))
            {
                return;
            }
        }
        
        if(!WorldMotdAPI.isGlobalSwitchWorld())
        {
            if(!WorldMotdAPI.isWorldEnabled(world))
            {
                return;
            }
            
            Motd motd = WorldMotdAPI.getMotd(world);
            
            if(!motd.isSwitchWorldEnabled())
            {
                return;
            }
            
            if(motd.isSwitchWorldClearChatEnabled())
            {
                WorldMotdAPI.clearChat(player);
            }
            
            List<String> motdList = motd.getSwitchWorldMotd();
            
            if(motdList.isEmpty())
            {
                return;
            }
            
            for(String s : motdList)
            {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', WorldMotdAPI.parseMotdLine(s, player)));
            }
        }
    }
    
}
