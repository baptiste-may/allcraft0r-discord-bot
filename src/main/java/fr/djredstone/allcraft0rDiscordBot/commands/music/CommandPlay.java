package fr.djredstone.allcraft0rDiscordBot.commands.music;

import javax.annotation.Nullable;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandPlay {

    public CommandPlay(SlashCommandInteractionEvent event) {

        final GuildVoiceState memberVoiceState = Objects.requireNonNull(event.getMember()).getVoiceState();

        assert memberVoiceState != null;
        if (!memberVoiceState.inAudioChannel()) {
            event.reply("Tu dois être dans un channel vocal pour utiliser cette commande").setEphemeral(true).queue();
            return;
        }

        final AudioManager audioManager = Objects.requireNonNull(event.getGuild()).getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        assert memberChannel != null;

        String URL = getURL(Objects.requireNonNull(event.getOption("text")).getAsString());
        if (URL.equalsIgnoreCase("")) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
            embed.setColor(Color.RED);
            embed.setDescription("Aucune musique trouvé " + Emoji.fromMarkdown("⚠️"));
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
        }
        else {
            event.reply("Action effectué !").setEphemeral(true).queue();
            PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), URL);
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
