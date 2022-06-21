package fr.djredstone.allcraft0rDiscordBot.commands.music;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.djredstone.allcraft0rDiscordBot.Utils;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;

public class CommandNow {

    public CommandNow(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        Guild guild;
        User user;
        if (event1 != null) {
            guild = event1.getGuild();
            user = event1.getAuthor();
        }
        else {
            assert event2 != null;
            guild = event2.getGuild();
            user = event2.getUser();
        }
        assert guild != null;

        if (PlayerManager.getInstance().getMusicManager(guild).audioPlayer.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
            embed.setDescription(Emoji.fromMarkdown("\uD83D\uDED1") + " Il n'y a pas de musique en ce moment");
            UtilsCommands.replyOrSend(embed, event1, event2);
            return;
        }

        final AudioTrack track = PlayerManager.getInstance().getMusicManager(guild).audioPlayer.getPlayingTrack();

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
        embed.setDescription(Emoji.fromMarkdown("\uD83D\uDCCB") + " Informations de la musique en cours :");
        embed.addField("Titre", "**" + track.getInfo().title + "**", true);
        embed.addField("Auteur", "**" + track.getInfo().author + "**", true);
        embed.addField("Durée", "**" + Utils.getTimestamp(track.getInfo().length) + "**", true);
        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
