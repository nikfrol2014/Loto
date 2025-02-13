package Loto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Main {
    static Players player_1 = new Players(2);//i - сколько картонок у него будет
    static JFrame jFrame;
    static JPanel startPanel = new JPanel();// стартовая панель (по идее это главное меню)
    static JPanel jPanel = new JPanel();// панель для самой игры
    static Bag shuffleBag = Bag.getInstance();

    public static void main(String[] args) {
        /*
         TODO: 09.04.2024 - сделано 3 класса - нужно разобраться с JFrame - допилить функционал:
         TODO: 09.04.2024 - добавить отображения двух игроков и их карточек
         TODO: 09.04.2024 - создать мышочек и добавить его отображение и взаимодействия с объектами в окне

         TODO: 16.01.2025 - реализована логика мышочка
         todo: 16.01.2025 - реализовать отображение карточки в виде массива кнопок с собственными событиями
          upd: карточки отображаются в виде массива кнопок, но сейчас лишь меняют цвет и становятся неактивными
          !реализовать! придумать какую-нибудь логику с этими карточками (кнопками в них) (функция проверки на победу реализована в Players)
         TODO: 16.01.2025 - реализовать игру меж двумя игроками
          upd: теперь кнопка окрашивается в синий только если ее значение совпадает с мышочком
         TODO: 19.01.2025 - что-то придумать на случай пропуска бочонка (есть вариант с ограниченным количеством нажатий на кнопку, позволяющую проверить, был ли этот бочонок или т.п.)
          upd: сейчас есть возможность узнать последние несколько бочонков - сделать какую-то логику, чтобы можно было восполнить пропуски на карточках
        */


        jFrame = getJFrame();
        jFrame.add(startPanel);

/****************************************************************************************
*                                Button 'start game'                                   *
***************************************************************************************/
        JButton startGameButton = new JButton("Start game");
        startPanel.add(startGameButton);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.remove(startGameButton);
                jFrame.remove(startPanel);
                jFrame.add(jPanel);
                jPanel.updateUI();

            }
        });
/***************************************************************************************/
/**                                  Button 'bagButton'                                 *
***************************************************************************************/
        JButton bagButton = new JButton("Shuffle");
        bagButton.setPreferredSize(new Dimension(75,50));
        jPanel.add(bagButton);
        bagButton.addActionListener(e -> {
            try {
                bagButton.setText(String.valueOf(shuffleBag.shuffle()));
            } catch (Exception ex) {
                bagButton.setText(ex.getMessage());
                bagButton.setEnabled(false);
            }

        });

/**************************************************************************************/

        jPanel.add(player_1.getCarton1().getjPanel());
        jPanel.add(player_1.getCarton2().getjPanel());
        jPanel.add(player_1.getButtonCheck());
        Judge judge = Judge.getInstance(player_1);


//        JPopupMenu jPopupMenu = new JPopupMenu("test");
//        jPanel.add(jPopupMenu);




        jFrame.setVisible(true);


/**                        Функция проверки на победу (сейчас только 1 игрока)                               */

        boolean gamerunning = true;
        while (gamerunning){
            if (player_1.isWin()){
                gamerunning=false;
                SwingUtilities.invokeLater(() -> {
                    int res = JOptionPane.showOptionDialog(
                            jFrame,
                            "Игрок 1 победил",
                            "Победа",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"Новая игра", "Выйти из игры"},
                            "Новая игра");
                    switch (res){
                        case 0 -> {
                            // todo: 19.06.2025 - сделать функцию новой игры
                            jPanel.add(startGameButton);
                            jPanel.updateUI();
                        }
                        case 1 -> jFrame.dispose();
                    }
                });
            }
        }
    }


    static JFrame getJFrame(){
        JFrame jFrame = new JFrame("Лото");
        jFrame.setSize(500,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }
}
