package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;

public class CommandDashboard extends ListenerAdapter {

	public CommandDashboard(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {

		HashMap<String, Integer> dashboard = new HashMap<>();
		for(Object objects : Objects.requireNonNull(Main.main.getConfig().getList("money"))) {
			String userID = (String) objects;

			try {
				dashboard.put(Objects.requireNonNull(Main.jda.getUserById(userID)).getAsTag(), Main.getMoney(Objects.requireNonNull(Main.jda.getUserById(userID))));
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		Map<String, Integer> dashboardSort = sortByValue(dashboard);

		StringBuilder board = new StringBuilder();
		for (Map.Entry<String, Integer> en : dashboardSort.entrySet()) {
			board.append("\n**").append(en.getKey()).append("**: ").append(en.getValue());
		}

		UtilsCommands.replyOrSend(board.toString(), event1, event2);

	}
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
				new LinkedList<>(hm.entrySet());
 
        // Sort the list
        list.sort(Map.Entry.comparingByValue());
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
