package com.sparkymc.phasmocraft.objects;

import com.sparkymc.phasmocraft.Phasmocraft;
import com.sparkymc.phasmocraft.RoundHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static com.sparkymc.phasmocraft.Utils.log;
import static com.sparkymc.phasmocraft.Utils.range;

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
        this.status = RoundStatus.IN_LOBBY;
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

    /**
     * Ran by the round handler to tick the round and all the items actively in the round.
     */
    public void tickRound() {
        if (range(0, 100) > 95) {
            EMFSource source;
            triggerSource(source = new EMFSource(host.getWorld().getBlockAt(host.getLocation()), EMFCauses.GHOST_EVENT, range(2, 6), 30, 100));
            log("Creating EMF Source - Location: %s, Cause: %s, Level: %s, Range: %s, Fade Time: %s", source.getSource().getLocation(), source.getCause(), source.getLevel(), source.getRange(), source.getFadeTime());
        }
    }

    private void end() {
        // something should happen here
        // ur mom hahahahahhahahaha
        // you're a bean grandma
    }

    public void start() {

    }

    public Set<Player> getPlayers() {
        return players;
    }
}
