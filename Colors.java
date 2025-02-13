package Loto;

import java.awt.*;

public enum Colors {
    WITH_BARREL(new Color(0xFF1433C4,true)),
    ERROR_COLOR(new Color(0xB0FF0D0D,true));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
