package fr.skewram.ArmorUtil;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        PluginManager pm = getServer().getPluginManager();

        getCommand("asp").setExecutor(new ArmorSpawn(this));
        pm.registerEvents(new ArmorSpawn(this), this);

        System.out.println("[ArmorUtil] Ready !");
    }
}
