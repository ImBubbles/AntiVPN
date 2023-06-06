package me.bubbles.antivpn.events;

import me.bubbles.antivpn.AntiVPN;
import me.bubbles.antivpn.events.manager.Event;
import me.bubbles.antivpn.util.UtilString;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Login extends Event {

    public Login(AntiVPN plugin) {
        super(plugin, PlayerJoinEvent.class);
    }

    @Override
    public void onEvent(org.bukkit.event.Event event) {

        // yes i used an event manager + listener class for one event :P
        // "sustainability" my ass

        PlayerJoinEvent e = (PlayerJoinEvent) event;
        if(e.getPlayer().hasPermission(plugin.getName().toLowerCase()+"."+"bypass")) { // give this to like youtubers or people who need it idk
            return;
        }
        // normally if you try pinging localhost I believe it would work? which mean it would probably detect it as a vpn
        // and we don't want that so we have this
        // also the address isn't gonna be null if the player isnt but apparently it can be so i have the try and catch just in case
        try {
            if (e.getPlayer().getAddress().getAddress().equals(InetAddress.getLocalHost())) {
                return;
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return;
        }

        new Thread(() -> { // it will take a second to kick anyone with a vpn and also multiple seconds to check if they dont

            try (Socket socket = new Socket(e.getPlayer().getAddress().getAddress(), 80)) {

                // found this out by using ping in command prompt. if it's a vpn it'll be valid and if not then it obv won't.
                // will have some issues sometimes like if someone is hosting something on port 80 like a website or database or server or anything
                // but just have them make a ticket in like a discord or something and give them permission "antivpn.bypass"

                // I used to have the kicked line not have the bukkit runnable but it was asynchronoyuuspiisu or wtv so i had to make a runnable in a thread kms

                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    public void run() {
                        e.getPlayer().kickPlayer(UtilString.colorFillPlaceholders("%kickMessage%", plugin));
                    }
                });

            } catch (IOException exc) {
                // poop
                // most likely not vpn
            }
        }).start();

    }

}