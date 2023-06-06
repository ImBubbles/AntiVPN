package me.bubbles.antivpn.events.manager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {

    private EventManager eventManager;

    public Listeners(EventManager eventManager) {
        this.eventManager=eventManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        eventManager.onEvent(e);
    }

}
