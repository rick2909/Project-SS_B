package com.example.project_ss_b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class Rapportage extends AppCompatActivity {
    public String FILE_NAME = "example"+".txt";

    EditText qName;
    EditText qDescription2;
    EditText qMedication2;
    EditText qNotes2;
    TextView qDescription;
    TextView qMedication;
    TextView qNotes;
    TextView qDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapportage);
        //Date
        Calendar cal = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());

        qDate = findViewById(R.id.Date);
        qDate.setText(currentDate);

        //Naming Textviews and Editviews
        qDescription = findViewById(R.id.Description);
        qMedication = findViewById(R.id.Medication);
        qNotes = findViewById(R.id.Notes);
        qDescription2 = findViewById(R.id.Description2);
        qMedication2 = findViewById(R.id.Medication2);
        qNotes2 = findViewById(R.id.Notes2);
        qName = findViewById(R.id.Name);

        //Button
        Button qSave = (Button) findViewById(R.id.Save);

    }
    public void save(View v) { //convert to string for writing
        String ntext = qName.getText().toString();
        String dtext = qDescription.getText().toString();
        String d2text = qDescription2.getText().toString();
        String mtext = qMedication.getText().toString();
        String m2text = qMedication2.getText().toString();
        String xtext = qNotes.getText().toString();
        String x2text = qNotes2.getText().toString();
        String vtext = qDate.getText().toString();

        FileOutputStream fos = null;

        Character enter = '\n';
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(vtext.getBytes());
            fos.write(enter);
            fos.write(enter);
            fos.write(ntext.getBytes());
            fos.write(enter);
            fos.write(enter);
            fos.write(dtext.getBytes());
            fos.write(enter);
            fos.write(d2text.getBytes());
            fos.write(enter);
            fos.write(enter);
            fos.write(mtext.getBytes());
            fos.write(enter);
            fos.write(m2text.getBytes());
            fos.write(enter);
            fos.write(enter);
            fos.write(xtext.getBytes());
            fos.write(enter);
            fos.write(x2text.getBytes());

            qName.getText().clear();
            qDescription2.getText().clear();
            qMedication2.getText().clear();
            qNotes2.getText().clear();

            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        File oldfile = new File(FILE_NAME);
        File newfile = new File(ntext);

        oldfile.renameTo(newfile);

        if(oldfile.renameTo(newfile)){
            System.out.println("Rename Successful");
        }else{
            System.out.println("Rename Unsuccessful");
        }
    }

}

