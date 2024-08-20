package net.fiercemanul.fiercesource.client.gui.style;

public record Int4(int i1, int i2, int i3, int i4) {

    public static Int4 same(int i) {
        return new Int4(i, i, i, i);
    }

    public static Int4 two(int i1, int i2) {
        return new Int4(i1, i2, i1, i2);
    }
}
