package Loto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bag {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 90;
    private static Bag bag;

    private static final ArrayList<Integer> array_0_90 = new ArrayList<>(90);
    private int current_turn = -1;
    private static int current_value = 0;

    public static Bag getInstance(){
        if (bag==null){
            bag = new Bag();
        }
        return bag;
    }

    private Bag() {
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            array_0_90.add(i);
        }
        Collections.shuffle(array_0_90);
    }

    public static ArrayList<Integer> getArray_0_90() {
        return array_0_90;
    }

    public static int getCurrent_value() {
        return current_value;
    }

    public int shuffle() throws Exception {
        current_turn++;
        if (current_turn!=MAX_VALUE) {
            current_value = array_0_90.get(current_turn);
            return current_value;
        } else {
            throw new Exception("it's over!");
        }
    }


}
