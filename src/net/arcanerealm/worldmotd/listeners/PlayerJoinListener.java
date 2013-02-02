package net.arcanerealm.worldmotd.listeners;

import java.util.List;
import net.arcanerealm.worldmotd.Motd;
import net.arcanerealm.worldmotd.WorldMotdAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author Kenny
 */
public class PlayerJoinListener implements Listener
{
    public PlayerJoinListener(){}
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();
        
        if(!player.hasPermission("worldmotd.login.motd.*"))
        {
            if(!player.hasPermission("worldmotd.login.motd."+player.getWorld().getName()))
            {
                return;
            }
        }
        
        if(!WorldMotdAPI.isGloablPlayerLogin())
        {
            if(!WorldMotdAPI.isWorldEnabled(world))
            {
                return;
            }
            
            Motd motd = WorldMotdAPI.getMotd(world);
            
            if(!motd.isPlayerLoginEnable())
            {
                return;
            }
            
            if(motd.isPlayerLoginClearChatEnabled())
            {
                WorldMotdAPI.clearChat(player);
            }
            
            List<String> motdList = motd.getLoginMotd();
            
            if(motdList.isEmpty())
            {
                System.out.println("Empty");
                return;
            }
            System.out.println("right..");
            
            for(String s : motdList)
            {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', WorldMotdAPI.parseMotdLine(s, player)));
            }
        }else
        {
            if(WorldMotdAPI.isGlobalPlayerLoginClearChat())
            {
                WorldMotdAPI.clearChat(player);
            }
            
            List<String> motdList = WorldMotdAPI.getGlobalPlayerLoginMotd();
            
            if(motdList.isEmpty())
            {
                System.out.println("Empty");
                return;
            }
            System.out.println("right..");
            
            for(String s : WorldMotdAPI.getGlobalPlayerLoginMotd())
            {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', WorldMotdAPI.parseMotdLine(s, player)));
            }
        }
    }

}
