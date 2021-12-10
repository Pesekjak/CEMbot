package me.pesekjak.melody;

import me.pesekjak.melody.audio.*;
import me.pesekjak.melody.listeners.MessageListener;
import me.pesekjak.melody.listeners.ReadyListener;
import me.pesekjak.melody.listeners.VoiceJoinListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Melody {

    private final static String token = "OTE4NTQ5MDIxNzYzNzExMDM4.YbI3cA.1-RUCrZZO6LrGX8GcwJVSQh7lxY";
    private final static JDABuilder builder = defaultBuilder();
    private final static PlayersManager playersManager = new PlayersManager();
    private static JDA jda = null;

    public static void main(String[] args) throws LoginException {
        registerListeners();
        jda = builder.build();
    }

    public static JDABuilder getBuilder() {
        return builder;
    }

    public static PlayersManager getPlayersManager() { return playersManager; }

    public static JDA getJDA() {
        return jda;
    }

    public static String getID() {
        return jda.getSelfUser().getId();
    }

    private static JDABuilder defaultBuilder() {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.ACTIVITY);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.listening("Endless Melodies.."));
        builder.setStatus(OnlineStatus.IDLE);
        return builder;
    }

    private static void registerListeners() {
        builder.addEventListeners(new MessageListener());
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new VoiceJoinListener());
    }

}
