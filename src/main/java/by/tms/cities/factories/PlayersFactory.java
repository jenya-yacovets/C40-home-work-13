package by.tms.cities.factories;

import by.tms.cities.entity.Player;
import by.tms.cities.util.Input;

import java.util.ArrayDeque;
import java.util.Deque;

public class PlayersFactory {
    public static Deque<Player> create() {

        Deque<Player> players = new ArrayDeque();

        int countPlayers = Input.getInt("Введите количество игроков. Минимум 2, максимум 5:");
        if (countPlayers < 2 || countPlayers > 5) {
            System.out.println("Введите число от 2 до 5. Повторите попытку.");
            return create();
        }

        for(int i=1; i<=countPlayers; i++) {
            String namePlayer = Input.getString(String.format("Введите имя %d-го игрока:", i));
            players.offer(new Player(namePlayer.trim()));
        }
        return players;
    }
}
