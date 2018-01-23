package com.alexandrunica.factura;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener   {

    private TextView start;
    private TextView end;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Calendar myCalendar = Calendar.getInstance();

        EditText furnizor = (EditText) findViewById(R.id.furnizor_edittext);
        start = (TextView) findViewById(R.id.start_edittext);
        end = (TextView) findViewById(R.id.end_edittext);
        EditText pret = (EditText) findViewById(R.id.pret_edittext);
        Button save = (Button) findViewById(R.id.save);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                showHistory();
            }
        });
    }


    @Override
    public void onDateSet (DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        start.setText(dateFormat.format(calendar.getTime()));
    }

    private void showHistory () {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        DatePickerFragment historyFramgnet = new DatePickerFragment();
        historyFramgnet.show(fragmentTransaction, DatePickerFragment.TAG);
    }
}
