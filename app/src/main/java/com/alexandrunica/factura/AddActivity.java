package com.alexandrunica.factura;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private TextView start;
    private TextView end;
    private FacturaModel facturaModel;
    private int currentClick;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Calendar myCalendar = Calendar.getInstance();

        facturaModel = new FacturaModel();

        Spinner furnizor = (Spinner) findViewById(R.id.furnizor_edittext);
        start = (TextView) findViewById(R.id.start_edittext);
        end = (TextView) findViewById(R.id.end_edittext);
        EditText pret = (EditText) findViewById(R.id.pret_edittext);
        Button save = (Button) findViewById(R.id.save);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                currentClick = start.getId();
                showHistory();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                currentClick = end.getId();
                showHistory();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnizor.setOnItemSelectedListener(this);
        furnizor.setAdapter(adapter);
    }


    @Override
    public void onDateSet (DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate (final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        if (currentClick == R.id.start_edittext) {
            start.setText(dateFormat.format(calendar.getTime()));
            facturaModel.setStartDate(calendar.getTime().toString());
        } else {
            end.setText(dateFormat.format(calendar.getTime()));
            facturaModel.setEndDate(calendar.getTime().toString());
        }
    }

    private void showHistory () {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        DatePickerFragment historyFramgnet = new DatePickerFragment();
        historyFramgnet.show(fragmentTransaction, DatePickerFragment.TAG);
    }

    @Override
    public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
        facturaModel.setFurnizor((String) adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected (AdapterView<?> adapterView) {

    }
}
