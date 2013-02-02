package net.arcanerealm.worldmotd.util;

import java.util.HashMap;
import net.arcanerealm.worldmotd.Motd;
import net.arcanerealm.worldmotd.WorldMOTD;
import net.arcanerealm.worldmotd.WorldMotdAPI;

/**
 *
 * @author Kenny
 */
public class SLAPI
{
    private static WorldMOTD plugin;
    
    public SLAPI(WorldMOTD instance)
    {
        SLAPI.plugin = instance;
    }
    
    public static void loadMotd()
    {
        HashMap<String,Motd> map = new HashMap<>();
        
        for(String s : WorldMotdAPI.getEnabledWorlds())
        {
            Motd motd = new Motd(plugin, s);
            map.put(s, motd);
        }
        WorldMotdAPI.setWorldMotdMap(map);
    }
    
}
