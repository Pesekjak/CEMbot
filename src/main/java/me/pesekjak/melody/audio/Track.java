package me.pesekjak.melody.audio;

public class Track {

    private final String name;
    private final String url;

    public Track(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

}
