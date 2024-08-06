package net.fiercemanul.fiercesource.client.gui.style;

public record Padding(int left, int top, int right, int bottom) {

    public static Padding same(int padding) {
        return new Padding(padding, padding, padding, padding);
    }

    public static Padding two(int left, int top) {
        return new Padding(left, top, left, top);
    }
}
