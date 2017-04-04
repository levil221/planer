package pl.levil.model;

/**
 * Created by ${levil} on 2017-03-31.
 */

public enum Months {
    Styczeń(31,1),
    Luty(28,2),
    Marzec(31,3),
    Kwiecień(30,4),
    Maj(31,5),
    Czerwiec(30,6),
    Lipiec(31,7),
    Sierpień(31,8),
    Wrzesien(30,9),
    Październik(31,10),
    Listopad(30,11),
    Grudzień(31,12);

    private int days;
    private int index;
    public int getDays() {
        return days;
    }

    public int getIndex() {
        return index;
    }

    private Months(int days, int index) {
        this.days = days;
        this.index = index;
    }
}
