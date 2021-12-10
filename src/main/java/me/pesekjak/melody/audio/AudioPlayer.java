package me.pesekjak.melody.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import me.pesekjak.melody.handlers.AudioPlayerSendHandler;
import net.dv8tion.jda.api.entities.AudioChannel;

public class AudioPlayer {

    private final AudioPlayerManager playerManager;
    private final com.sedmelluq.discord.lavaplayer.player.AudioPlayer globalPlayer;
    private final AudioPlayerSendHandler handler;
    private final TrackScheduler trackScheduler;
    private final ItemLoader itemLoader;
    private final AudioChannel voiceChannel;
    private final TrackPlayer trackPlayer;

    public AudioPlayer(AudioChannel voiceChannel) {
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        this.globalPlayer = playerManager.createPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(this);
        globalPlayer.addListener(trackScheduler);
        this.handler = new AudioPlayerSendHandler(this.globalPlayer);
        this.trackScheduler = new TrackScheduler(this);
        this.itemLoader = new ItemLoader(this.trackScheduler);
        this.voiceChannel = voiceChannel;
        this.trackPlayer = new TrackPlayer();
        this.trackPlayer.start();
    }

    public AudioPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public com.sedmelluq.discord.lavaplayer.player.AudioPlayer getGlobalPlayer() {
        return this.globalPlayer;
    }

    public AudioPlayerSendHandler getHandler() {
        return this.handler;
    }

    public TrackScheduler getTrackScheduler() {
        return this.trackScheduler;
    }

    public ItemLoader getItemLoader() {
        return this.itemLoader;
    }

    public AudioChannel getVoiceChannel() {
        return this.voiceChannel;
    }

    public TrackPlayer getTrackPlayer() {
        return this.trackPlayer;
    }

}
