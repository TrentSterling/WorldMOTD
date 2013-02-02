package net.arcanerealm.worldmotd;

import java.util.List;
import net.arcanerealm.worldmotd.util.VConfig;

/**
 *
 * @author Kenny
 */
public class Motd 
{
    private WorldMOTD plugin;
    private VConfig config;
    private String world;
    private boolean isSwitchWorld;
    private boolean isPlayerLogin;
    private boolean isSwitchWorldClearChat;
    private boolean isPlayerLoginClearChat;
    private List<String> swtichWorldMotd;
    private List<String> playerLoginMotd;
    
    public Motd(WorldMOTD instance, String world)
    {
        this.plugin = instance;
        this.config = new VConfig(plugin.getDataFolder().getAbsolutePath(), "config.yml", plugin);
        this.world = world;
        this.isSwitchWorld = config.getConfig().getBoolean(world+".switch-world.enable");
        this.swtichWorldMotd = config.getConfig().getStringList(world+".switch-world.motd");
        this.isSwitchWorldClearChat = config.getConfig().getBoolean(world+".switch-world.clear-chat");
        this.isPlayerLogin = config.getConfig().getBoolean(world+".on-player-login.enable");
        this.playerLoginMotd = config.getConfig().getStringList(world+".on-player-login.motd");
        this.isPlayerLoginClearChat = config.getConfig().getBoolean(world+".on-player-login.clear-chat");
        
        if(this.playerLoginMotd.isEmpty())
        {
            System.out.println("Empty motd");
        }
    }
    
    public Motd(){}
    
    public List<String> getLoginMotd()
    {
        return this.playerLoginMotd;
    }
    
    public List<String> getSwitchWorldMotd()
    {
        return this.swtichWorldMotd;
    }
    
    public boolean isSwitchWorldEnabled()
    {
        return this.isSwitchWorld;
    }
    
    public boolean isPlayerLoginEnable()
    {
        return this.isPlayerLogin;
    }
    
    public boolean isSwitchWorldClearChatEnabled()
    {
        return this.isSwitchWorldClearChat;
    }
    
    public boolean isPlayerLoginClearChatEnabled()
    {
        return this.isPlayerLoginClearChat;
    }
    
    public void setLoginMotd(List<String> motd)
    {
        this.playerLoginMotd = motd;
    }
    
    public void setSwitchWorldMotd(List<String> motd)
    {
        this.swtichWorldMotd = motd;
    }
    
    public void setSwitchWorldEnabled(boolean value)
    {
        this.isSwitchWorld = value;
    }
    
    public void setLoginEnabled(boolean value)
    {
        this.isPlayerLogin = value;
    }
    
    public void setSwitchWorldClearChat(boolean value)
    {
        this.isSwitchWorldClearChat = value;
    }
    
    public void setLoginClearChat(boolean value)
    {
        this.isPlayerLoginClearChat = value;
    }
}
