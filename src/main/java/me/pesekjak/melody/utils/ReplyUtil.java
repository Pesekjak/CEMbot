package me.pesekjak.melody.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import javax.annotation.Nullable;
import java.awt.*;

public class ReplyUtil {

    public static void ErrorReply(MessageChannel channel, Message message, String errorName, String errorMessage) {
        ErrorReaction(channel, message);
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(new Color(250, 100, 60));
        embedBuilder.setTitle("Error - " + errorName);
        embedBuilder.setDescription(errorMessage);
        MessageEmbed embed = embedBuilder.build();
        message.replyEmbeds(embed).queue();
    }

    public static void ErrorReaction(MessageChannel channel, Message message) {
        message.addReaction("U+1F4A2").queue();
    }

    public static void Reply(MessageChannel channel, Message message, String title, String textMessage, @Nullable String image) {
        SuccessReaction(channel, message);
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(new Color(150, 200, 250));
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(textMessage);
        if(!(image == null)) embedBuilder.setImage(image);
        MessageEmbed embed = embedBuilder.build();
        message.replyEmbeds(embed).queue();
    }

    public static void SuccessReaction(MessageChannel channel, Message message) {
        message.addReaction("U+1F3B5").queue();
    }

    public static MessageEmbed getHelpPage() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(new Color(150, 200, 250));
        builder.setTitle("Available Commands");
        builder.setDescription("`!melody join`\n → *Connects to the channel of the user who executed the command.*\n\n`!melody playing`\n → *Shows current name and link of the song.*");
        return builder.build();
    }

}
