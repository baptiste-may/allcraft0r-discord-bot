package fr.djredstone.botdiscord.commands;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandDashboard extends ListenerAdapter {
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
	
	public void onGuildMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "dashboard")) {
			
			HashMap<String, Integer> dashboard = new HashMap<>();
			for(Object objects : Main.main.getConfig().getList("money")) {
				String userID = (String) objects;
				
				try {
					dashboard.put(Main.jda.getUserById(userID).getAsTag(), Main.getMoney(Main.jda.getUserById(userID)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			Map<String, Integer> dashboardSort = sortByValue(dashboard);
			
			String board = "";
			for (Map.Entry<String, Integer> en : dashboardSort.entrySet()) {
	            board = board + "\n**" + en.getKey() + "**: " + en.getValue();
	        }
			
			event.getChannel().sendMessage(board).queue();
			
		}
	}

}
