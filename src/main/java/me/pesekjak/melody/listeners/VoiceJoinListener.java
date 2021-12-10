package me.pesekjak.melody.listeners;

import me.pesekjak.melody.Melody;
import me.pesekjak.melody.audio.AudioPlayer;
import me.pesekjak.melody.handlers.AudioPlayerSendHandler;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nonnull;

public class VoiceJoinListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        for(Member member : event.getChannelJoined().getMembers()) {
            if(member.getId().equals(Melody.getID())) {
                Guild guild = event.getGuild();
                if(Melody.getPlayersManager().getActivePlayers().get(event.getGuild()) == null) {
                    AudioChannel voiceChannel = event.getMember().getVoiceState().getChannel();
                    AudioManager audioManager = guild.getAudioManager();
                    AudioPlayer player = new AudioPlayer(voiceChannel);
                    AudioPlayerSendHandler handler = player.getHandler();
                    audioManager.setSendingHandler(handler);
                    audioManager.openAudioConnection(voiceChannel);
                    Melody.getPlayersManager().addPlayer(guild, player);
                    Melody.getPlayersManager().getActivePlayers().get(guild).getPlayerManager().loadItem(Melody.getPlayersManager().getActivePlayers().get(guild).getTrackPlayer().getPlayingTrack().getUrl(), Melody.getPlayersManager().getActivePlayers().get(guild).getItemLoader());
                }

            }
        }
    }

}
