package by.tms.cities.entity;

import by.tms.cities.config.SkipLetters;
import by.tms.cities.entity.steps.Step;
import by.tms.cities.entity.steps.Steps;
import by.tms.cities.exceptions.DuplicationWordException;
import by.tms.cities.exceptions.StopGameException;
import by.tms.cities.factories.PlayersFactory;
import by.tms.cities.util.Input;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Deque;

public class Game {
    private Deque<Player> players;
    private Steps steps;
    private String nextLitter = null;

    private static final String FILE_NAME = "report.xml";

    public Game() {
        this.players = PlayersFactory.create();
        steps = new Steps();
    }
    public void start() {
        System.out.println("=================== Добро пожаловать в игру города ===================\n--- Для остановки игры введите слово 'стоп' ---");
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

        if (word.toLowerCase().trim().equals("стоп")) throw new StopGameException();

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
            System.out.println("Ваш город введен не корректно или не существует, повторите попытку.");
            nextStep();
            return;
        }

        try {
            steps.addStep(word, player);
        } catch (DuplicationWordException e) {
            System.out.println("Данный город уже был, повторите попытку.");
            nextStep();
            return;
        }

        nextLitter = letter;
        players.removeFirst();
        players.addLast(player);
    }

    private void report() {
        int num = Input.getInt("Нужно ли создавать отчет о игре: \n1 - Да \n2 - Нет");
        if(num == 1) {

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Steps.class, Step.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(steps, new File(FILE_NAME));
                System.out.println(String.format("Отчет успешно сгенерирован и находится в файле - '%s'", FILE_NAME));
            } catch (JAXBException e) {
                System.out.println("При генерации отчета произогла ошибка (");
            }

        } else if (num == 2) {
            return;
        } else {
            System.out.println("Выберите вариант из предложеных. Повторите попытку.");
            report();
        }
    }
}
