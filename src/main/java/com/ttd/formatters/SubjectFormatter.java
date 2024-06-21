/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.formatters;

import com.ttd.pojo.Subject;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author DELL
 */
public class SubjectFormatter implements Formatter<Subject> {

    @Override
    public String print(Subject subject, Locale locale) {
        return String.valueOf(subject.getId());
    }

    @Override
    public Subject parse(String subjectId, Locale locale) throws ParseException {
        return new Subject(Integer.valueOf(subjectId));
    }

}
