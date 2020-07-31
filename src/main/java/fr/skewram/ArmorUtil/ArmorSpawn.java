package fr.skewram.ArmorUtil;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

public class ArmorSpawn implements CommandExecutor {
    Main instance;
    ArmorSpawn(Main instance){
        this.instance = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length){
            case 1:
                if(sender instanceof Player){
                    FileConfiguration config = this.instance.getConfig();
                    if(config.get(args[0]) == null){
                        sender.sendMessage(ChatColor.RED + "Nom inconnue");
                    }else{
                        ArmorStand armorStand = ((Player) sender).getWorld().spawn(((Player) sender).getLocation(), ArmorStand.class);
                        SpawnArmorStand(sender, args, config, armorStand);
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "En mode console vous devez spécifier les coordonnées x, y et z");
                }
                break;
            case 4:
                FileConfiguration config = this.instance.getConfig();
                Location location = new Location(this.instance.getServer().getWorld("world"), Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
                if(config.get(args[0]) == null){
                    sender.sendMessage(ChatColor.RED + "Nom inconnue");
                }else{
                    ArmorStand armorStand = ((Player) sender).getWorld().spawn(location, ArmorStand.class);
                    SpawnArmorStand(sender, args, config, armorStand);
                }
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Commande incorrect ! \n /asp <name> [x] [y] [z]");
                break;
        }
        return true;
    }

    private void SpawnArmorStand(CommandSender sender, String[] args, FileConfiguration config, ArmorStand armorStand) {
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setHeadPose(new EulerAngle(
                MathUtils.degToRad(config.getDouble(args[0]+".head.u")),
                MathUtils.degToRad(config.getDouble(args[0]+".head.v")),
                MathUtils.degToRad(config.getDouble(args[0]+".head.w"))
        ));
        armorStand.setBodyPose(new EulerAngle(
                MathUtils.degToRad(config.getDouble(args[0]+".chest.u")),
                MathUtils.degToRad(config.getDouble(args[0]+".chest.v")),
                MathUtils.degToRad(config.getDouble(args[0]+".chest.w"))
        ));
        armorStand.setLeftArmPose(new EulerAngle(
                MathUtils.degToRad(config.getDouble(args[0]+".arms.left.u")),
                MathUtils.degToRad(config.getDouble(args[0]+".arms.left.v")),
                MathUtils.degToRad(config.getDouble(args[0]+".arms.left.w"))
        ));
        armorStand.setRightArmPose(new EulerAngle(
                MathUtils.degToRad(config.getDouble(args[0]+".arms.right.u")),
                MathUtils.degToRad(config.getDouble(args[0]+".arms.right.v")),
                MathUtils.degToRad(config.getDouble(args[0]+".arms.right.w"))
        ));
        armorStand.setLeftLegPose(new EulerAngle(
                MathUtils.degToRad(config.getDouble(args[0]+".legs.left.u")),
                MathUtils.degToRad(config.getDouble(args[0]+".legs.left.v")),
                MathUtils.degToRad(config.getDouble(args[0]+".legs.left.w"))
        ));
        armorStand.setRightLegPose(new EulerAngle(
                MathUtils.degToRad(config.getDouble(args[0]+".legs.right.u")),
                MathUtils.degToRad(config.getDouble(args[0]+".legs.right.v")),
                MathUtils.degToRad(config.getDouble(args[0]+".legs.right.w"))
        ));
        armorStand.setVisible(true);
        sender.sendMessage(ChatColor.GREEN + "Spawn de l'ArmorStand " + args[0]);
    }
}
