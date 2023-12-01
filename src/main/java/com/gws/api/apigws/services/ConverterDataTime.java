package com.gws.api.apigws.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ConverterDataTime {

    public LocalTime StringToDateTime(String dateString){
        DateTimeFormatter dateFormato = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime dataFormatado = LocalTime.parse(dateString,dateFormato);

        return dataFormatado;
    }

    public LocalDate StringToDate(String dateString){
        DateTimeFormatter dateFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate dataFormatado = LocalDate.parse(dateString,dateFormato);

        return dataFormatado;
    }
}
