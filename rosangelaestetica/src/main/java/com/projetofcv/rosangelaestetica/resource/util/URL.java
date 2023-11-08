package com.projetofcv.rosangelaestetica.resource.util;

import java.time.LocalDate;

public class URL {

    public static LocalDate decodeDate( String dateString){
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }
}
