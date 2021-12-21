package com.sparkymc.phasmocraft.objects;

import com.sparkymc.phasmocraft.Phasmocraft;
import com.sparkymc.phasmocraft.RoundHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a game round.
 */
public class Round {

    /**
     * The status of the round.
     */
    private RoundStatus status;

    /**
     * The difficulty of the round.
     */
    private Difficulty difficulty;

    /**
     * The round handler.
     */
    private final RoundHandler handler;

    /**
     * The participants of the round.
     */
    private final Set<Player> players = new HashSet<>();

    /**
     * The current EMF sources of the round. Added via {@link #triggerSource(EMFSource)}.
     */
    private final Set<EMFSource> emfSources = new HashSet<>();

    /**
     * The host of the round.
     */
    private final Player host;

    private final Location lobbyLocation;

    /**
     * The world that the round takes place in.
     */
    private final World world;

    private final int id;

    /**
     * Constructs a new Round with the given host.
     * @param id The round ID.
     * @param host The player that started the round that can change settings.
     * @param world The world generated in order to host this round. I like beans.
     */
    public Round(RoundHandler handler, int id, Player host, World world) {
        this.handler = handler;
        this.id = id;
        this.host = host;
        this.world = world;
        lobbyLocation = world.getSpawnLocation();
        addPlayer(host);
    }

    public RoundStatus getStatus() {
        return status;
    }

    public void setStatus(RoundStatus status) {
        if ((this.status == RoundStatus.IN_GAME || this.status == RoundStatus.ENDED) && status == RoundStatus.IN_LOBBY) {
            // log: cannot set the status of an in-game/ended round to lobby state.
            return;
        }
        if (status == RoundStatus.ENDED) {
            end();
        }
        this.status = status;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (status != RoundStatus.IN_LOBBY) {
            // log: cannot set the difficulty of a round that isn't currently in the lobby.
            return;
        }
        this.difficulty = difficulty;
    }

    public Player getHost() {
        return host;
    }

    public void triggerSource(EMFSource source) {
        emfSources.add(source);
        Bukkit.getScheduler().runTaskLater(Phasmocraft.getInstance(), () -> emfSources.remove(source), source.getFadeTime());
    }

    public Set<EMFSource> getEmfSources() {
        return emfSources;
    }

    public World getWorld() {
        return world;
    }

    public int getId() {
        return id;
    }

    public boolean addPlayer(Player player) {
        if (status != RoundStatus.IN_LOBBY) return false;
        player.teleport(lobbyLocation);
        return players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.teleport(handler.getRoundEndLocation());
    }

    private void end() {
        // something should happen here
    }

    public void start() {

    }
}
