package fr.djredstone.allcraft0rDiscordBot.commands.economy.apero;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.ThreadChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.classes.money;

public class CommandAperoCreate {

    private static final HashMap<User, ThreadChannel> aperos = new HashMap<>();
    public static HashMap<User, ThreadChannel> getAperos() { return aperos; }

    public CommandAperoCreate(SlashCommandInteractionEvent event) throws SQLException {

        User user = event.getUser();
        if (aperos.containsKey(user)) {
            event.reply("Tu as déjà un apéro en cours !").queue();
            return;
        }

        int userMoney = money.get(user);
        if (userMoney < 1000) {
            event.reply("Il te faut au moins 1000 " + Main.getRedstoneEmoji() + " pour créer un apéro !").setEphemeral(true).queue();
            return;
        }

        money.remove(user, 1000);

        String aperoName = user.getName() + "'s apero";
        if (event.getOption("text") != null) aperoName = Objects.requireNonNull(event.getOption("text")).getAsString();
        TextChannel textChannel = event.getTextChannel();
        String finalAperoName = aperoName;
        event.getChannel().sendMessage("Et les gens ! **" + user.getAsTag() + "** vient de commencer un apéro ! \uD83D\uDD3D").queue((message) ->
                textChannel.createThreadChannel(finalAperoName, message.getIdLong()).queue((thread) -> {
                        event.reply("Action éffectuée !").setEphemeral(true).queue();
                        aperos.put(user, thread);
                }));
    }

}
