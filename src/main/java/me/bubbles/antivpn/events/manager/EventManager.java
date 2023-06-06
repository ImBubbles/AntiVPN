package me.bubbles.antivpn.events.manager;

import me.bubbles.antivpn.AntiVPN;
import me.bubbles.antivpn.events.Login;

import java.util.Collections;
import java.util.HashSet;

public class EventManager {

    private AntiVPN plugin;
    private HashSet<Event> events = new HashSet<>();

    public EventManager(AntiVPN plugin) {
        this.plugin=plugin;
        registerListener(); // REGISTER EVENT LISTENERS
        Collections.addAll(this.events, // REGISTER EVENT HANDLERS
                new Login(plugin)
        );
    }

    public void onEvent(org.bukkit.event.Event event) {
        for(Event e : events) {
            if(e.getEvent().equals(event.getClass())) {
                e.onEvent(event);
            }
        }
    }

    public void registerListener() {
        plugin.getServer().getPluginManager().registerEvents(new Listeners(this),plugin);
    }

}
