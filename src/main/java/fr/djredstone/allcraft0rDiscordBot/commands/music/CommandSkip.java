package fr.djredstone.allcraft0rDiscordBot.commands.music;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.classes.music.GuildMusicManager;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;

public class CommandSkip {

    public CommandSkip(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

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
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        if (musicManager.audioPlayer.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
            embed.setDescription(Emoji.fromMarkdown("\uD83D\uDED1") + " Il n'y a pas de musique en ce moment");
            UtilsCommands.replyOrSend(embed, event1, event2);
            return;
        }

        musicManager.scheduler.nextTrack();

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
        embed.setDescription(Emoji.fromMarkdown("⏩") + " Musique suivante !");
        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
