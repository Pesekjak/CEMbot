package me.pesekjak.melody.listeners;

import me.pesekjak.melody.Melody;
import me.pesekjak.melody.audio.AudioPlayer;
import me.pesekjak.melody.handlers.AudioPlayerSendHandler;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import me.pesekjak.melody.utils.ReplyUtil;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromType(ChannelType.TEXT)) return;

        Message message = event.getMessage();
        String[] args = message.getContentRaw().split(" ");

        if (!args[0].equalsIgnoreCase("!melody")) return;

        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();

        if (args.length == 1) {
            message.replyEmbeds(ReplyUtil.getHelpPage()).queue();
            return;
        }

        if (args[1].equalsIgnoreCase("join")) {
            if (event.getMember().getVoiceState().getChannel() == null) {
                ReplyUtil.ErrorReply(channel, message, "No Voice Channel", "You need to be connected to the voice channel to run this command.");
                return;
            }
            AudioChannel voiceChannel = event.getMember().getVoiceState().getChannel();
            AudioManager audioManager = guild.getAudioManager();
            AudioPlayer player = new AudioPlayer(voiceChannel);
            AudioPlayerSendHandler handler = player.getHandler();
            audioManager.setSendingHandler(handler);
            audioManager.openAudioConnection(voiceChannel);
            Melody.getPlayersManager().addPlayer(guild, player);
            Melody.getPlayersManager().getActivePlayers().get(guild).getPlayerManager().loadItem(Melody.getPlayersManager().getActivePlayers().get(guild).getTrackPlayer().getPlayingTrack().getUrl(), Melody.getPlayersManager().getActivePlayers().get(guild).getItemLoader());
            ReplyUtil.SuccessReaction(channel, message);
            return;
        }

        else if (args[1].equalsIgnoreCase("playing")) {
            String[] videoID = Melody.getPlayersManager().getActivePlayers().get(guild).getTrackPlayer().getPlayingTrack().getUrl().split("=");
            String thumbnail = "https://img.youtube.com/vi/" + videoID[1] + "/mqdefault.jpg";
            ReplyUtil.Reply(channel, message, "Current song", "Name: **" + Melody.getPlayersManager().getActivePlayers().get(guild).getTrackPlayer().getPlayingTrack().getName() + "**\nLink: " + Melody.getPlayersManager().getActivePlayers().get(guild).getTrackPlayer().getPlayingTrack().getUrl(), thumbnail);
            return;
        }

        message.replyEmbeds(ReplyUtil.getHelpPage()).queue();
    }

}
