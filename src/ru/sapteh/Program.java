package ru.sapteh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Program {
    public static void main(String[] args) throws IOException, ParseException {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        Date date=calendar.getTime();

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите дату экзамена: ");
        String strDateExz= buffer.readLine();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateExz=dateFormat.parse(strDateExz);

        int quantityDay = (int)((dateExz.getTime()-date.getTime())/1000/24/60/60);
        int quantityMonth=0;
        int quantityYear=0;
        
        if (quantityDay>=30){
            quantityMonth=quantityDay / 30;
            quantityDay = quantityDay % 30;
        }
        if (quantityMonth>=12){
            quantityYear=quantityMonth / 12;
            quantityMonth=quantityMonth % 12;
        }
        if (quantityDay<=-30){
            quantityMonth=quantityDay / 30;
            quantityDay=quantityDay % 30;
        }
        if (quantityMonth<=-12){
            quantityYear=quantityMonth / 12;
            quantityMonth=quantityMonth % 12;
        }

        String result = switch (dateExz.compareTo(date)){
            case 0 -> "Сегодня экзамен";
            case 1 -> "До экзамена осталось " + quantityDay + addStr(quantityDay) +","+
                    quantityMonth + addMonth(quantityMonth) + "," + quantityYear+
                    addYear(quantityYear);
            case -1 -> "Экзамен был " + Math.abs(quantityDay) + addStr(quantityDay) +","+
                    Math.abs(quantityMonth)+ addMonth(quantityMonth)+
                    "," + Math.abs(quantityYear) + addYear(quantityYear) +" назад";
            default -> "Not input";
        };
        System.out.println(result);
    }
    public static String addStr(int quantityDay){
        quantityDay=quantityDay % 10;
        return switch (Math.abs(quantityDay)) {
            case 1 -> " день";
            case 2, 3, 4 -> " дня";
            default -> " дней";
        };
    }
    public static String addMonth(int quantityMonth){
        quantityMonth=quantityMonth % 10;
        return switch (Math.abs(quantityMonth)) {
            case 1 -> " месяц";
            case 2, 3, 4 -> " месяца";
            default -> " месяцев";
        };
    }
    public static String addYear(int quantityYear){
        quantityYear = quantityYear % 10;
        return switch (Math.abs(quantityYear)) {
            case 1 -> " год";
            case 2, 3, 4 -> " года";
            default -> " лет";
        };
    }
}
