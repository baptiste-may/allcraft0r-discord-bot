package fr.djredstone.botdiscord.commands.music;

import javax.annotation.Nullable;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.music.PlayerManager;

public class CommandPlay {

    public CommandPlay(@Nullable String option, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        final TextChannel channel;
        final Member member;
        final Guild guild;
        if (event1 != null) {
            channel = event1.getTextChannel();
            member = event1.getMember();
            guild = event1.getGuild();
        } else {
            assert event2 != null;
            channel = event2.getTextChannel();
            member = event2.getMember();
            guild = event2.getGuild();
        }

        assert member != null;
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        assert memberVoiceState != null;
        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("Tu dois être dans un channel vocal pour utiliser cette commande").queue();
            return;
        }

        assert guild != null;
        final AudioManager audioManager = guild.getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        assert memberChannel != null;

        String URL = getURL(option);
        if (URL.equalsIgnoreCase("")) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(member.getUser());
            embed.setColor(Color.RED);
            embed.setDescription("Aucune musique trouvé ⚠️");
            UtilsCommands.replyOrSend(embed, event1, event2);
        }
        else {
            if (event1 != null) event1.getMessage().delete().queue();
            else event2.reply("Action effectué !").setEphemeral(true).queue();
            PlayerManager.getInstance().loadAndPlay(channel, URL);
        }

    }

    private static String getURL(@Nullable String option) {
        if (option == null) return "";
        String link = String.join(" ", option);
        if (isURL(link)) {
            return link;
        } else {
            return "ytsearch:" + link;
        }
    }

    private static boolean isURL(String URL) {
        try {
            new URI(URL);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}
