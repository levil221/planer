package pl.levil.model;

/**
 * Created by ${levil} on 2017-03-31.
 */

public enum Months {
    styczeń(31,1),
    luty(28,2),
    marzec(31,3),
    kwiecień(30,4),
    maj(31,5),
    czerwiec(30,6),
    lipiec(31,7),
    sierpień(31,8),
    wrzesien(30,9),
    październik(31,10),
    listopad(30,11),
    grudzień(31,12);

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
