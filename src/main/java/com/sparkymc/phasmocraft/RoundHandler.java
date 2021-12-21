package com.sparkymc.phasmocraft;

import com.sparkymc.phasmocraft.objects.Round;
import com.sparkymc.phasmocraft.objects.RoundStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RoundHandler implements Iterable<Round> {

    private final Set<Round> rounds = new HashSet<>();
    private Location roundEndLocation = Bukkit.getWorlds().get(0).getSpawnLocation();

    public Set<Round> getRounds() {
        return rounds;
    }

    @Override
    public Iterator<Round> iterator() {
        return rounds.iterator();
    }

    /**
     * Gets the lowest free round ID currently available.
     * @return The lowest unused round ID.
     */
    private int getFreeRoundId() {
        Set<Integer> ids = new HashSet<>();
        for(var i = 0; i < 500; i++) ids.add(i);
        for (var round : this) {
            ids.remove(round.getId());
        }
        return ids.stream().min(Comparator.comparingInt(Integer::intValue)).orElseThrow();
    }

    /**
     * Starts a new round and returns it.
     * @param host The host of the round.
     * @return The round.
     */
    public Round startRound(Player host) {
        var id = getFreeRoundId();
        var world = Bukkit.createWorld(new WorldCreator("phasmocraft_lobby" + id));

        var round = new Round(this, id, host, world);
        round.setStatus(RoundStatus.IN_LOBBY);

        return round;
    }

    public Location getRoundEndLocation() {
        return roundEndLocation;
    }

    public void setRoundEndLocation(Location roundEndLocation) {
        this.roundEndLocation = roundEndLocation;
    }
}
