package Loto;

import java.awt.event.ActionEvent;

public class Judge {
    private static Judge instance;
    private Players player;
    public static CartonButton lastEvent;

    // Singleton
    public static Judge getInstance(Players player) {
        if (instance == null) {
            instance = new Judge(player);
        }
        return instance;
    }

    private Judge(Players player) {
        this.player = player;
    }

    // проверяет, соответствует ли число в мешке нажатой кнопке на картонке
    public static boolean isCorrectTapping(){
        return Bag.getCurrent_value() == lastEvent.value;
    }
}
