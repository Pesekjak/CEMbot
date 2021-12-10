package me.pesekjak.melody.audio;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public class PlayersManager {

    private final HashMap<Guild, AudioPlayer> activePlayers = new HashMap<>();

    public PlayersManager() {
    }

    public HashMap<Guild, AudioPlayer> getActivePlayers() {
        return this.activePlayers;
    }

    public void addPlayer(Guild guild, AudioPlayer handler) {
        this.activePlayers.remove(guild);
        this.activePlayers.put(guild, handler);
    }

    public void removePlayer(Guild guild) {
        AudioPlayer guildPlayer = this.activePlayers.get(guild);
        guildPlayer.getGlobalPlayer().destroy();
        this.activePlayers.remove(guild);
    }

}
