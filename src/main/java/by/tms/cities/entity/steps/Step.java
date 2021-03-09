package by.tms.cities.entity.steps;

import by.tms.cities.entity.Player;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;

public class Step {
    private int numberStep;
    private String word;
    private Player player;

    public Step(String word, Player player, int numberStep) {
        this.numberStep = numberStep;
        this.word = word;
        this.player = player;
    }

    public Step(String word) {
        this.word = word;
    }

    public Step() {
    }

    public String getWord() {
        return word;
    }

    @XmlAttribute(name = "number")
    public int getNumberStep() {
        return numberStep;
    }

    public void setNumberStep(int numberStep) {
        this.numberStep = numberStep;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @XmlElement(name = "name")
    private String getNameUser() {
        return player.getName();
    }

    @XmlTransient
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(word, step.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
