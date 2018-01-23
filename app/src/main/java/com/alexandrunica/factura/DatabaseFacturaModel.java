package com.alexandrunica.factura;

import android.provider.BaseColumns;

/**
 * Created by Nica on 1/23/2018.
 */

public class DatabaseFacturaModel {

    private DatabaseFacturaModel(){

    }

    public static class FacturaEntry implements BaseColumns {
        public static final String TABLE_NAME = "facturi";
        public static final String COLUMN_FURNIZOR = "furnizor";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_END = "end";
        public static final String COLUMN_PRET = "pret";

    }
}
