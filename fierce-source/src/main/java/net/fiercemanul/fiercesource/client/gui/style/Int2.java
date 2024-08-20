package net.fiercemanul.fiercesource.client.gui.style;

public record Int2(int i1, int i2) {

    public static Int2 same(int i) {
        return new Int2(i, i);
    }
}
