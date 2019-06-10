package ru.ariona;

public class SimpleWeather {

    private int temp;
    private String description;

    public SimpleWeather(int temp, String description) {
        this.temp = temp;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Температура воздуха  " +
                 temp +
                " градусов, " + description;
    }
}
