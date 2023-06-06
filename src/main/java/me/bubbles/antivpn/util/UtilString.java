package me.bubbles.antivpn.util;

import me.bubbles.antivpn.AntiVPN;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class UtilString {

    public static String colorFillPlaceholders(String message, AntiVPN plugin) {
        FileConfiguration config = plugin.getDefaultConfig().getFileConfiguration();
        return ChatColor.translateAlternateColorCodes('&',message
                .replace("%kickMessage%", config.getString("kickMessage"))
                .replace("%prefix%", config.getString("prefix"))
                .replace("%primary%", config.getString("primaryColor"))
                .replace("%secondary%", config.getString("secondaryColor"))
        );
    }
    
}
