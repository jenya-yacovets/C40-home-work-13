package by.tms.cities.entity;

import by.tms.cities.config.SkipLetters;
import by.tms.cities.exceptions.StopGameException;
import by.tms.cities.factories.PlayersFactory;
import by.tms.cities.util.Input;

import java.util.Deque;

public class Game {
    private Deque<Player> players;
    private Steps steps;
    private String nextLitter = null;

    public Game() {
        this.players = PlayersFactory.create();
        steps = new Steps();
    }
    public void start() {
        System.out.println("=================== Добро пожаловать в игру города ===================");
        System.out.println("--- Для остановки игры введите слово 'стоп' ---");
        run();
        report();
        System.out.println("=================== Прощайте дорогие игроки  ===================");
    }

    private void run() {
        try {
            while (true) {
                nextStep();
            }
        } catch (StopGameException e) {}
    }

    private void nextStep() throws StopGameException {
        Player player = players.peekFirst();
        String word = Input.getString(String.format("Сейчас ходит %s, тебе город на " + ((nextLitter == null) ? "любую букву" : "'%s'") + ":", player, nextLitter));

        if (word.equals("стоп")) throw new StopGameException();

        String[] letterArr = word.trim().toLowerCase().split("");

        if(nextLitter != null && !nextLitter.equals(letterArr[0])) {
            System.out.println(String.format("Введненный город не подходит, нужен город на букву '%s', повторите попытку.", nextLitter));
            nextStep();
            return;
        }

        String letter = null;

        for (int i=letterArr.length-1; i>0; i--) {
            if (!SkipLetters.check(letterArr[i])) {
                letter = letterArr[i];
                break;
            }
        }

        if(letter == null) {
            System.out.println("Ваш город введ не корректно или не существует, повторите попытку.");
            nextStep();
            return;
        }

        if (steps.checkRepetition(word)) {
            System.out.println("Данный город уже был, повторите попытку.");
            nextStep();
            return;
        }

        nextLitter = letter;
        steps.addStep(player, word);
        players.removeFirst();
        players.addLast(player);
    }

    private void report() {
        System.out.println("Тут будем делать отчет");
    }
}
