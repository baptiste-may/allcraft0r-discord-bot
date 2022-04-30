package fr.djredstone.botdiscord.commands.music;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.music.PlayerManager;

public class CommandPause {

    public CommandPause(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

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
        final AudioPlayer player = PlayerManager.getInstance().getMusicManager(guild).audioPlayer;
        if (player.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
            embed.setDescription(Emoji.fromMarkdown("\uD83D\uDED1") + " Il n'y a pas de musique en ce moment");
            UtilsCommands.replyOrSend(embed, event1, event2);
            return;
        }

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
        player.setPaused(!player.isPaused());
        if (player.isPaused()) embed.setDescription(Emoji.fromMarkdown("⏸") + " La musique en cours a été mise en pause !");
        else embed.setDescription(Emoji.fromMarkdown("▶️") + " La musique en cours a été remise en route !");
        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
