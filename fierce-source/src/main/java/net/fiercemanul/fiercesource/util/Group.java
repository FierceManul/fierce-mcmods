package net.fiercemanul.fiercesource.util;

public class Group<F, S> {


    protected F f;
    protected S s;

    public Group(F f, S s) {
        this.f = f;
        this.s = s;
    }

    public F getFirst() {
        return f;
    }

    public void setFirst(F f) {
        this.f = f;
    }

    public S getSecond() {
        return s;
    }

    public void setSecond(S s) {
        this.s = s;
    }


}
