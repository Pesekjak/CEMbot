package me.pesekjak.melody.audio;

import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import me.pesekjak.melody.Melody;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer audioPlayer;
    private final com.sedmelluq.discord.lavaplayer.player.AudioPlayer player;
    private AudioTrack track;

    public TrackScheduler(AudioPlayer player) {
        this.audioPlayer = player;
        this.player = player.getGlobalPlayer();
    }

    public com.sedmelluq.discord.lavaplayer.player.AudioPlayer getPlayer() {
        return this.player;
    }

    public AudioTrack getTrack() {
        return this.track;
    }

    @Override
    public void onTrackEnd(com.sedmelluq.discord.lavaplayer.player.AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(this.audioPlayer.getVoiceChannel().getMembers().size() == 1) {
            player.destroy();
            return;
        }
        Melody.getPlayersManager().getActivePlayers().get(this.audioPlayer.getVoiceChannel().getGuild()).getTrackPlayer().playNext();
    }

    public void queue(AudioTrack track) {
        this.track = track;
        this.player.playTrack(track);
    }

}
