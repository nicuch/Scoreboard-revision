package rien.bijl.Scoreboard.r.board;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import rien.bijl.Scoreboard.r.Main;
import rien.bijl.Scoreboard.r.Session;
import rien.bijl.Scoreboard.r.board.App;
import rien.bijl.Scoreboard.r.board.Row;
import rien.bijl.Scoreboard.r.board.slimboard.Slimboard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rien on 22-10-2018.
 */
public class ScoreboardHolder {

    private App app;
    public Player player;

    private Slimboard slim;

    public ScoreboardHolder(App app, Player player)
    {
        this.app = app;
        this.player = player;

        slim = new Slimboard(Session.plugin, player, app.getRows().size());

        app.registerHolder(this);
    }

    public void update()
    {

        slim.setTitle(app.getTitle().getLine());

        int count = 0;
        HashMap<Integer, String> lines = new HashMap<>();
        for(Row row : app.getRows())
        {
            String line = row.getLine();
            if(row.placeholders) {
                // Check if the PAPI plugin is enabled and the string has a placeholder
                if(Session.enabled_dependencies.contains(Session.dependencies[0]) && org.bukkit.Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") &&
                    PlaceholderAPI.containsPlaceholders(line)) {
                    line = PlaceholderAPI.setPlaceholders(player, line);
                }
            }
            slim.setLine(count, line);
            count++;
        }
    }

}
