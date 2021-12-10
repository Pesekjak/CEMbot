package me.pesekjak.melody.listeners;

import me.pesekjak.melody.Melody;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class VoiceLeaveListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        if(event.getMember().getId().equals(Melody.getID())) {
            Guild guild = event.getGuild();
            Melody.getPlayersManager().removePlayer(guild);
        }
    }
}
