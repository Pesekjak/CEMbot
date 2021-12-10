package me.pesekjak.melody.audio;

import me.pesekjak.melody.Melody;
import net.dv8tion.jda.api.entities.Guild;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TrackPlayer {

    private List<Track> trackList;
    private int playing;

    public TrackPlayer() {
        this.trackList = new ArrayList<>();
    }

    public List<Track> getTrackList() {
        return this.trackList;
    }

    public int getPlayingIndex() {
        return this.playing-1;
    }

    public Track getPlayingTrack() {
        return this.trackList.get(this.playing-1);
    }

    public void start() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("songs.yaml");
        HashMap<String, ArrayList> songObj = yaml.load(inputStream);
        ArrayList<HashMap<String, String>> songList = songObj.get("songs");
        for(int i = 0; i < songList.size(); i++) {
            Track track = new Track(songList.get(i).get("name"), songList.get(i).get("url"));
            this.trackList.add(track);
        }
        Collections.shuffle(this.trackList);
        this.playing = 0;
        this.playNext();
    }

    public void playNext() {
        this.playYoutube(this.trackList.get(this.playing).getUrl());
        this.playing++;
        if (this.playing > this.trackList.size()) {
            this.playing = 0;
            Collections.shuffle(this.trackList);
        }
    }

    public void playYoutube(String url) {
        for(Guild guild : Melody.getPlayersManager().getActivePlayers().keySet()) {
            Melody.getPlayersManager().getActivePlayers().get(guild).getPlayerManager().loadItem(url, Melody.getPlayersManager().getActivePlayers().get(guild).getItemLoader());
        }
    }

}