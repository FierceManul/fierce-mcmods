package net.fiercemanul.fiercesource.client.gui.style;

public record IntXY(int x, int y) {

    public static IntXY same(int i) {
        return new IntXY(i, i);
    }
}
