package fr.eni.enchere.controller.converter;

 

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

 

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;

 

@Component

public class StringToDateConverter implements Converter<String, Date> {

 

    @Override

    public Date convert(String date) {

        try {

            return date == null || date.isBlank() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(date);

        } catch (ParseException e) {

            throw new RuntimeException(e);

        }

    }

 

}
