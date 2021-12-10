package me.pesekjak.melody.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class ItemLoader implements AudioLoadResultHandler {

    private final TrackScheduler scheduler;

    public ItemLoader(TrackScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public TrackScheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        this.scheduler.queue(audioTrack);
    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {

    }

    @Override
    public void noMatches() {

    }

    @Override
    public void loadFailed(FriendlyException e) {

    }

}
