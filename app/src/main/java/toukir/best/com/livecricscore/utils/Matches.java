package toukir.best.com.livecricscore.utils;

/**
 * Created by toukirul on 6/6/2017.
 */

public class Matches {

    private String unique_id;
    private String date;
    private String team_one;
    private String team_two;
    private String match_started;
    private String squad;
    private String toss_winner_team;

    public String getToss_winner_team() {
        return toss_winner_team;
    }

    public void setToss_winner_team(String toss_winner_team) {
        this.toss_winner_team = toss_winner_team;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam_one() {
        return team_one;
    }

    public void setTeam_one(String team_one) {
        this.team_one = team_one;
    }

    public String getTeam_two() {
        return team_two;
    }

    public void setTeam_two(String team_two) {
        this.team_two = team_two;
    }

    public String getMatch_started() {
        return match_started;
    }

    public void setMatch_started(String match_started) {
        this.match_started = match_started;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }
}
