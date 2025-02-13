package Loto;
import javax.swing.*;
import java.util.*;
public class Carton {
    private final int[][] cells = new int[3][9];
    private final GridCarton gridCarton = new GridCarton(3,9);
    private final JPanel jPanel = new JPanel();
    private final ArrayList<CartonButton> cartonButtons = new ArrayList<>(cells.length);

    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 90;
    private static final int NUM_IN_STRING = 5;
    private static final int CELLS_IN_STRING = 9;
    private static final int CELLS_EMPTY = 4;
    private static final double CHANCE_EMPTY = (double) CELLS_EMPTY/CELLS_IN_STRING;
    private static final int EMPTY_SIGN = 0;

    private int[] Pull_Empty(){
        int[] empty_cells = new int[CELLS_IN_STRING];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < CELLS_IN_STRING; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i = 0; i < CELLS_IN_STRING; i++) {
            empty_cells[i] = 1;
        }
        for (int i = 0; i < CELLS_EMPTY; i++) {
            empty_cells[list.get(i)] = EMPTY_SIGN;
        }
        return empty_cells;
    }
    /**return set of (first,last), where key = first and value = last*/
    private TreeMap<Integer,Integer> Set_First_Last(){
        TreeMap<Integer,Integer> set_First_Last = new TreeMap<>();
        set_First_Last.put(1,9);
        set_First_Last.put(10,19);
        set_First_Last.put(20,29);
        set_First_Last.put(30,39);
        set_First_Last.put(40,49);
        set_First_Last.put(50,59);
        set_First_Last.put(60,69);
        set_First_Last.put(70,79);
        set_First_Last.put(80,90);
        return set_First_Last;
    }

    // TODO: 09.04.2024 - можно добавить обработку генерации, чтобы в колоннах не было повторяющихся значений
    private int[][] Fill_Cell(){
        TreeMap<Integer,Integer> first_last = Set_First_Last();
        int j = 0;
        for (int i = 0; i < 3; i++) {
            cells[i] = Pull_Empty();
            for (Map.Entry<Integer,Integer> set: first_last.entrySet()) {
                if (cells[i][j]!=EMPTY_SIGN){
                    cells[i][j] = (int) (Math.random()*(set.getValue()- set.getKey()+1))+ set.getKey();
                }
                j++;
            }
            j = 0;
        }
        return cells;
    }

    public ArrayList<CartonButton> getCartonButtons() {
        return cartonButtons;
    }

    public JPanel getjPanel() {
        jPanel.setLayout(gridCarton);
        for (CartonButton cb : cartonButtons){
            jPanel.add(cb);
        }
        return jPanel;
    }

    //генерирует игровую картонку
    public Carton() {
        Fill_Cell();
        for (int i = 0; i < 3;i++){
            for (int j = 0; j < 9; j++) {
                CartonButton cartonButton = new CartonButton(i,j,cells[i][j]);
                if (cells[i][j]==0){
                    cartonButton.setEnabled(false);
                    cartonButton.setText("");
                }
                cartonButtons.add(cartonButton);
            }
        }

    }
}
