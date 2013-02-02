package net.arcanerealm.worldmotd;

import java.util.HashMap;
import java.util.List;
import net.arcanerealm.worldmotd.util.VConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class WorldMotdAPI 
{
    private WorldMOTD plugin;
    private VConfig config;
    
    private static boolean isGlobal;
    private static boolean isGlobalSwitchWorld;
    private static boolean isGlobalPlayerLogin;
    private static boolean isGlobalSwitchWorldClearChat;
    private static boolean isGlobalPlayerLoginClearChat;
    private static List<String> globalSwitchWorld;
    private static List<String> globalPlayerLogin;
    private static List<String> enabledWorlds;
    
    private static HashMap<String,Motd> worldToMotd = new HashMap<>();
    
    public WorldMotdAPI(WorldMOTD instance)
    {
        this.plugin = instance;
        this.config = new VConfig(plugin.getDataFolder().getAbsolutePath(), "config.yml", plugin);
        
        WorldMotdAPI.isGlobal = config.getConfig().getBoolean("global.enable");
        WorldMotdAPI.isGlobalSwitchWorld = config.getConfig().getBoolean("global.events.switch-world.enable");
        WorldMotdAPI.isGlobalSwitchWorldClearChat = config.getConfig().getBoolean("global.events.switch-world.clear-chat");
        WorldMotdAPI.globalSwitchWorld = config.getConfig().getStringList("global.events.switch-world.motd");
        WorldMotdAPI.isGlobalPlayerLogin = config.getConfig().getBoolean("global.events.on-player-login.enable");
        WorldMotdAPI.isGlobalPlayerLoginClearChat = config.getConfig().getBoolean("global.events.on-player-login.clear-chat");
        WorldMotdAPI.globalPlayerLogin = config.getConfig().getStringList("global.events.on-player-login.motd");
        WorldMotdAPI.enabledWorlds = config.getConfig().getStringList("world-enabler");
    }
    
    public static HashMap<String,Motd> getWorldMotdMap()
    {
        return WorldMotdAPI.worldToMotd;
    }
    
    public static void setWorldMotdMap(HashMap<String,Motd> map)
    {
        WorldMotdAPI.worldToMotd = map;
    }
    
    public static boolean isWorldEnabled(String world)
    {
        for(String s : enabledWorlds)
        {
            if(s.equalsIgnoreCase(world))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isGlobalEnable()
    {
        return WorldMotdAPI.isGlobal;
    }
    
    public static boolean isGlobalSwitchWorld()
    {
        return WorldMotdAPI.isGlobalSwitchWorld;
    }
    
    public static boolean isGloablPlayerLogin()
    {
        return WorldMotdAPI.isGlobalPlayerLogin;
    }
    
    public static boolean isGlobalSwitchWorldClearChat()
    {
        return WorldMotdAPI.isGlobalSwitchWorldClearChat;
    }
    
    public static boolean isGlobalPlayerLoginClearChat()
    {
        return WorldMotdAPI.isGlobalPlayerLoginClearChat;
    }
    
    public static List<String> getGlobalSwitchWorldMotd()
    {
        return WorldMotdAPI.globalSwitchWorld;
    }
    
    public static List<String> getGlobalPlayerLoginMotd()
    {
        return WorldMotdAPI.globalPlayerLogin;
    }
    
    public static List<String> getEnabledWorlds()
    {
        return WorldMotdAPI.enabledWorlds;
    }
    
    public static Motd getMotd(String world)
    {
        return WorldMotdAPI.worldToMotd.get(world);
    }
    
    public static void clearChat(Player p )
    {
        for(int i = 0; i < 121; i++)
        {
            p.sendMessage("");
        }
    }
    
    public static String parseMotdLine(String line, Player player)
    {
        String output = line.replaceAll("%player%", player.getDisplayName()).replaceAll("%playerlist%", WorldMotdAPI.getPlayerList()).replaceAll("%totalplayers%", WorldMotdAPI.getTotalPlayers()+"").replaceAll("%world%", player.getWorld().getName());
        return output;
    }
    
    public static String getPlayerList()
    {
        String output = "";
        boolean first = true;
        
        for(Player p : Bukkit.getOnlinePlayers())
        {
            if(!first)
            {
                output += ", ";
            }else
            {
                first = false;
            }
            output += p.getDisplayName();
        }
        return output;
    }
    
    public static Integer getTotalPlayers()
    {
        int i = 0;
        for(Player p : Bukkit.getOnlinePlayers())
        {
            i++;
        }
        return i;
    }
}
