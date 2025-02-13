package Loto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CartonButton extends JButton {
    int i,j,value;

    public CartonButton(Integer i, Integer j, Integer value) {
        super(value.toString());
        this.i=i;
        this.j=j;
        this.value=value;
        addActionListener(e ->{
            Judge.lastEvent = (CartonButton) e.getSource();//////////////////////

            if (Judge.isCorrectTapping()) {
                this.setBackground(Colors.WITH_BARREL.getColor());
                // костыль (не факт, что он работает) - иногда кнопка становится неактивной при нажатии, но не окрашивается
                Timer timer = new Timer(20, t -> {
                    this.setEnabled(false);
                });
                timer.setRepeats(false);
                timer.start();

            } else {
                    Color currentColor = this.getBackground();
                    // обычный тред.слип не работает, т.к. события в отдельном потоке, поэтому пользуйся таймером
                    Timer timer = new Timer(100, t -> {
                        this.setBackground(currentColor);
                    });
                    this.setBackground(Colors.ERROR_COLOR.getColor());
                    // тут мы и запускаем таймер
                    timer.setRepeats(false);
                    timer.start();
            }
        });
    }

    @Override
    public String toString() {
        return "CartonButton{" +
                "i=" + i +
                ", j=" + j +
                ", value=" + value +
                '}';
    }
}
