package Loto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Players {
    private Carton carton1;
    private Carton carton2;
    private final int countOfCartons;

    private int remainingUsesButtonCheck = 3;
    private final JButton buttonCheck = new JButton("Посмотреть последние 4 скинутых бочонка, осталось: " + remainingUsesButtonCheck);
    private final ArrayList<Integer> arr_0_90 = Bag.getArray_0_90();


    public Carton getCarton1() {
        return carton1;
    }

    public Carton getCarton2() {
        return carton2;
    }

    public Players(int i) {
        countOfCartons=i;
        switch (i) {
            case 1 -> carton1 = new Carton();
            case 2 -> {
                carton1 = new Carton();
                carton2 = new Carton();
            }
        }
        buttonCheck.addActionListener(e -> {
            btnCheck();
        });
    }

    public void btnCheck() {
        if (remainingUsesButtonCheck>0){
            remainingUsesButtonCheck--;

            int cur_ind = arr_0_90.indexOf(Bag.getCurrent_value());
            ArrayList<Integer> listOfLast_3_values = new ArrayList<>(3);

            try{
                for (int j = cur_ind-1; j >= cur_ind - 4; j--) {
                        listOfLast_3_values.add(arr_0_90.get(j));
                }
            } finally {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(
                            Main.jFrame,
                            listOfLast_3_values.toString()
                    );
                });
                buttonCheck.setText("Посмотреть последний скинутый бочонок, осталось: " + remainingUsesButtonCheck);
            }
        }
    }

    public int getRemainingUsesButtonCheck() {
        return remainingUsesButtonCheck;
    }

    public JButton getButtonCheck() {
        return buttonCheck;
    }

    //todo: 16.01.2025 сейчас это нигде не используется - придумать что-то
    // Upd: используется в Main
    public boolean isWin(){
        switch (countOfCartons){
            case 2:
                for (CartonButton cb : carton2.getCartonButtons()){
                    if (cb.isEnabled()){
                        return false;
                    }
                }
            case 1:
                for (CartonButton cb : carton1.getCartonButtons()){
                    if (cb.isEnabled()){
                        return false;
                    }
                }
                return true;
        }
        return true;
    }
}
