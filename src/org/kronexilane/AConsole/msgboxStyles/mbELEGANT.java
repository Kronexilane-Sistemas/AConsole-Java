package org.kronexilane.AConsole.msgboxStyles;

public class mbELEGANT implements msgBoxStyle{
    @Override
    public char getUpLeft() {
        return '*';
    }

    @Override
    public char getUpRight() {
        return '*';
    }

    @Override
    public char getDownLeft() {
        return getUpLeft();
    }

    @Override
    public char getDownRight() {
        return getUpRight();
    }

    @Override
    public char getHorizontal() {
        return '=';
    }

    @Override
    public char getVertical() {
        return ' ';
    }
}
