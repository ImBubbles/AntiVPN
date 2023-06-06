package me.bubbles.antivpn;

import me.bubbles.antivpn.events.manager.EventManager;
import me.bubbles.antivpn.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiVPN extends JavaPlugin {

    private EventManager eventManager;
    private Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // CONFIG
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        this.config=new Config(this,"config.yml");

        // EVENTS
        eventManager=new EventManager(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Config getDefaultConfig() {
        return config;
    }

}
