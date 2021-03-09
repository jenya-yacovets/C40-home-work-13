package by.tms.cities.entity.steps;

import by.tms.cities.entity.Player;
import by.tms.cities.exceptions.DuplicationWordException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Steps {

    private List<Step> allSteps;
    private int numberStep;

    public Steps() {
        allSteps = new ArrayList<>();
        numberStep = 1;
    }

    @XmlElement(name = "step")
    public List<Step> getAllSteps() {
        return allSteps;
    }

    public void addStep(String word, Player player) throws DuplicationWordException {
        word = word.trim().toLowerCase();

        if(allSteps.contains(new Step(word))) {
            throw new DuplicationWordException();
        }

        allSteps.add(new Step(word, player, numberStep));
        numberStep++;
    }
}
