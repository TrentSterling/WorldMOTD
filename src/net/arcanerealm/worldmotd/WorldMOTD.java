package net.arcanerealm.worldmotd;

import java.io.File;
import net.arcanerealm.worldmotd.commands.WmMotd;
import net.arcanerealm.worldmotd.listeners.PlayerJoinListener;
import net.arcanerealm.worldmotd.listeners.ServerListPingListener;
import net.arcanerealm.worldmotd.listeners.SwitchWorldListener;
import net.arcanerealm.worldmotd.util.SLAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Kenny
 */
public class WorldMOTD extends JavaPlugin
{
    private WmMotd wmmotd = new WmMotd();
    private PlayerJoinListener pjl = new PlayerJoinListener();
    private ServerListPingListener slpl = new ServerListPingListener();
    private SwitchWorldListener swl = new SwitchWorldListener();
    private WorldMotdAPI api; 
    private SLAPI slapi;
    
    @Override
    public void onEnable()
    {
        createFiles();
        setupEvents();
        this.api = new WorldMotdAPI(this);
        this.slapi = new SLAPI(this);
        SLAPI.loadMotd();
    }
    
    @Override
    public void onDisable(){}
    
    private void setupEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this.pjl, this);
        pm.registerEvents(this.slpl, this);
        pm.registerEvents(this.swl, this);
        this.getCommand("wmotd").setExecutor(this.wmmotd);
    }
    
    private void createFiles()
    {
        File f = new File(this.getDataFolder()+File.separator+"config.yml");
        if(!f.exists())
        {
            this.saveDefaultConfig();
        }
    }
}
