package by.tms.cities.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Steps {

    List<String> allSteps = new ArrayList<>();
    Map<String, List<String>> userSteps = new HashMap<>();

    public Steps() {
    }

    public List<String> getAllSteps() {
        return allSteps;
    }

    public Map<String, List<String>> getUserSteps() {
        return userSteps;
    }

    public void addStep(Player player, String word) {
        word = word.trim().toLowerCase();
        allSteps.add(word);
        List<String> userList = userSteps.get(player.getName());
        if(userList == null) userList = new ArrayList<>();
        userList.add(word);
        userSteps.put(player.getName(), userList);
    }
    public boolean checkRepetition(String word) {
        return allSteps.contains(word.trim().toLowerCase());
    }
}
