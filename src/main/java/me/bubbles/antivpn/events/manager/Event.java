package me.bubbles.antivpn.events.manager;

import me.bubbles.antivpn.AntiVPN;
import org.bukkit.event.Listener;

public class Event implements Listener {

    public AntiVPN plugin;
    private Class event;

    public Event(AntiVPN plugin, Class event) {
        this.plugin=plugin;
        this.event=event;
    }

    public void onEvent(org.bukkit.event.Event event) {

    }

    public Class getEvent() {
        return event;
    }

}
