package main.java.autohopper;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class autohopper extends JavaPlugin implements Listener {
    private Map<UUID, Boolean> enabledPlayers = new HashMap<>();
    private Map<UUID, Block> selectedHoppers = new HashMap<>();
    private Map<UUID, BukkitTask> particleTasks = new HashMap<>();

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("autohopper").setExecutor(new CommandExecutor() {
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (!player.hasPermission("autohopper.use")) {
                        player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                        return true;
                    }

                    if (args.length > 0 && args[0].equalsIgnoreCase("enable")) {
                        enabledPlayers.put(player.getUniqueId(), true);
                        player.sendMessage(ChatColor.GREEN + "AutoHopper enabled!");
                    } else if (args.length > 0 && args[0].equalsIgnoreCase("disable")) {
                        enabledPlayers.put(player.getUniqueId(), false);
                        player.sendMessage(ChatColor.RED + "AutoHopper disabled!");

                        // Cancel the particle task if the player disables the plugin
                        BukkitTask task = particleTasks.remove(player.getUniqueId());
                        if (task != null) {
                            task.cancel();
                        }
                    }
                }
                return true;
            }
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (enabledPlayers.getOrDefault(player.getUniqueId(), false) && event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking() && player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.HOPPER) {
                if (block.equals(selectedHoppers.get(player.getUniqueId()))) {
                    // Player is clicking on the same hopper they have previously selected, unselect it
                    selectedHoppers.remove(player.getUniqueId());
                    player.sendMessage(ChatColor.YELLOW + "Hopper unselected!");

                    // Cancel the particle task
                    BukkitTask task = particleTasks.remove(player.getUniqueId());
                    if (task != null) {
                        task.cancel();
                    }
                } else {
                    // Player is selecting a new hopper
                    selectedHoppers.put(player.getUniqueId(), block);
                    player.sendMessage(ChatColor.YELLOW + "Hopper selected!");

                    // Cancel the previous particle task if one exists
                    BukkitTask previousTask = particleTasks.remove(player.getUniqueId());
                    if (previousTask != null) {
                        previousTask.cancel();
                    }

                    // Schedule a new particle task
                    BukkitTask task = getServer().getScheduler().runTaskTimer(this, () -> {
                        if (enabledPlayers.get(player.getUniqueId())) {
                            block.getWorld().spawnParticle(Particle.END_ROD, block.getLocation().add(0.5, 1, 0.5), 10);
                        }
                    }, 0L, 20L); // Run every second (20 ticks = 1 second in Minecraft)

                    particleTasks.put(player.getUniqueId(), task);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Block block = selectedHoppers.get(player.getUniqueId());
        if (block != null && enabledPlayers.getOrDefault(player.getUniqueId(), false) && player.isSneaking() && block.getLocation().distance(player.getLocation()) < 2) {
            Hopper hopper = (Hopper) block.getState();
            for (int i = 9; i < player.getInventory().getSize(); i++) { // Start from 9 to skip the hotbar
                ItemStack item = player.getInventory().getItem(i);
                if (item != null) {
                    HashMap<Integer, ItemStack> couldntCopy = hopper.getInventory().addItem(item);
                    if (couldntCopy.isEmpty()) {
                        player.getInventory().clear(i);
                    } else {
                        player.getInventory().setItem(i, couldntCopy.get(0));
                    }
                }
            }
        }
    }
}
