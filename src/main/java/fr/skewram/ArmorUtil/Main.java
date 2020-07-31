package fr.skewram.ArmorUtil;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("asp").setExecutor(new ArmorSpawn(this));

        System.out.println("[ArmorUtil] Ready !");
    }
}
