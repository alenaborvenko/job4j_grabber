package ru.job4j.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;

public class SqlRuDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String parse) {
        Locale ru = new Locale("ru", "RU");
        if (parse.contains("вчера")) {
            return parseNotDay(1, parse);
        }
        if (parse.contains("сегодня")) {
            return parseNotDay(0, parse);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy',' HH:mm", ru);
        String[] months = {
                "янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"
        };
        DateFormatSymbols symbols = DateFormatSymbols.getInstance(ru);
        symbols.setMonths(months);
        simpleDateFormat.setDateFormatSymbols(symbols);
        LocalDateTime rsl = null;
        try {
            Date date = simpleDateFormat.parse(parse);
            rsl = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private LocalDateTime parseNotDay(int day, String parse) {
        String[] hourAndMinutes = parse.split(", ")[1].split(":");
        int hour = Integer.parseInt(hourAndMinutes[0]);
        int minutes = Integer.parseInt(hourAndMinutes[1]);
        return LocalDateTime.of(LocalDate.now().minusDays(day),
                LocalTime.of(hour, minutes));
    }
}
