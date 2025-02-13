package Loto;

import java.awt.*;

// todo: 16.01.2025 скорее всего ничего не менять (этот класс тупо для нормального отображения кнопок на карточках)

public class GridCarton extends GridLayout {
    public GridCarton(int rows, int cols) {
        super(rows, cols);
    }

    public GridCarton(int rows, int cols, int hgap, int vgap) {
        super(rows, cols, hgap, vgap);
    }

}
