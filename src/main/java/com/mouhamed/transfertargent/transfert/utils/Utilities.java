package com.mouhamed.transfertargent.transfert.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;

public class Utilities {

    private Utilities() {
    }

    // generate unique code for class Compte
    public static String genereCode(Long id) {
        if (id == null) {
            return "C-" + Timestamp.from(Instant.now()) + "00001";
        } else {
            return "C-" + Timestamp.from(Instant.now()) + new DecimalFormat("00000")
                    .format(id + 1);
        }

    }
}
