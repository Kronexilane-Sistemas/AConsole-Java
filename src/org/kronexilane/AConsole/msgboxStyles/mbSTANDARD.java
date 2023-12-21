package org.kronexilane.AConsole.msgboxStyles;

public class mbSTANDARD implements  msgBoxStyle {
    @Override
    public char getUpLeft() {
        return '/';
    }

    @Override
    public char getUpRight() {
        return '\\';
    }

    @Override
    public char getDownLeft() {
        return '\\';
    }

    @Override
    public char getDownRight() {
        return '/';
    }

    @Override
    public char getHorizontal() {
        return '-';
    }

    @Override
    public char getVertical() {
        return '|';
    }
}
