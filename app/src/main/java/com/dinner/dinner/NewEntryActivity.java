package com.dinner.dinner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class NewEntryActivity extends AppCompatActivity {

    public static final String INSERT_URL = "http://ignas.epizy.com/mobile/db.php";

    Dinner dinner;

    CheckBox PletBgCB;
    CheckBox PletNeCB;
    CheckBox PletReCB;
    CheckBox PletSrCB;

    CheckBox MedusMedCB;
    CheckBox MedusBevCB;
    CheckBox MedusJauCB;
    CheckBox MedusNerCB;
    CheckBox MeduDarMedCB;

    RadioGroup MotineleRG;

    EditText komentarasET;

    EditText seimosNrET;

    Button newEntryBtn;
    Button updateEntryBtn;
    Button deleteEntryBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        // checking if it's new or existing entry
        Intent intent = getIntent();
        dinner = (Dinner) intent.getSerializableExtra(AdapterDinner.ENTRY);

        newEntryBtn = findViewById(R.id.btn_create);
        updateEntryBtn = findViewById(R.id.btn_update);
        deleteEntryBtn = findViewById(R.id.btn_delete);

        if (dinner == null) { // new entry- values by default
            setTitle(R.string.new_entry_meniu_title);

 //           dinner = new Dinner(
   //                 -1, // because it's not in db
     //               getResources().getString(R.string.new_entry_pletimas_Baigta),
       //             getResources().getString(R.string.new_entry_pletimas_Nereikia),
        //            0,
         //           getResources().getStringArray(R.array.new_entry_seimos_nr).toString()
          //  );

            updateEntryBtn.setEnabled(false);
            deleteEntryBtn.setEnabled(false);
            newEntryBtn.setEnabled(true);
        } else { // existing entry- values by entry
            setTitle(R.string.existing_entry_meniu_title);

            //TODO: implement update, delete buttons
            updateEntryBtn.setEnabled(true);
            deleteEntryBtn.setEnabled(true);
            newEntryBtn.setEnabled(false);
        }

        PletBgCB = findViewById(R.id.checkPletimasBaigta);
        PletNeCB = findViewById(R.id.checkPletimasNereikia);
        PletReCB = findViewById(R.id.checkPletimasReikia);
        PletSrCB = findViewById(R.id.checkPletimasSiaurinti);

        MeduDarMedCB = findViewById(R.id.checkMedusReikiaMeduviu);
        MedusBevCB = findViewById(R.id.checkMedusBeveik);
        MedusJauCB = findViewById(R.id.checkMedusJau);
        MedusMedCB = findViewById(R.id.checkMedusMeduve);
        MedusNerCB = findViewById(R.id.checkMedusNera);

        MotineleRG = findViewById(R.id.new_entry_bites_motinele);

        komentarasET = findViewById(R.id.new_entry_bites_komentaras);

        seimosNrET = findViewById(R.id.seimosNr);

        setDataFromEntry(dinner);

        newEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dinner dinnerFromForm = getDataFromForm();
                insertToDB(dinnerFromForm);
                /*Toast.makeText(NewEntryActivity.this,
                        "Dinner type: " + dinner.getDinnerType() + "\n" +
                                "Delivery type: " + dinner.getDelivery() + "\n" +
                                "Price: " + dinner.getPrice() + "\n" +
                                "Payment: " + dinner.getPayment() + "\n",
                        Toast.LENGTH_SHORT).show();*/
            }
        });


        updateEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewEntryActivity.this,
                        "Needs to be implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });

        deleteEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewEntryActivity.this,
                        "Needs to be implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setDataFromEntry(Dinner dinner) {
        boolean isChecked = false;
        if (dinner.getPletimasType().
                contains(getResources().getString(R.string.new_entry_pletimas_Baigta))) {
            PletBgCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getPletimasType().
                contains(getResources().getString(R.string.new_entry_pletimas_Nereikia))) {
            PletNeCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getPletimasType().
                contains(getResources().getString(R.string.new_entry_pletimas_Reikia))) {
            PletReCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getPletimasType().
                contains(getResources().getString(R.string.new_entry_pletimas_Siaurinti))) {
            PletSrCB.setChecked(true);
            isChecked = true;
        }

        if (dinner.getMedus().
                contains(getResources().getString(R.string.new_entry_bites_medus_yra))) {
            MedusBevCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getMedus().
                contains(getResources().getString(R.string.new_entry_bites_medus_yra_daug))) {
            MedusJauCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getPletimasType().
                contains(getResources().getString(R.string.new_entry_bites_medus_deti_meduve))) {
            MedusMedCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getMedus().
                contains(getResources().getString(R.string.new_entry_bites_medus_pleciam))) {
            MeduDarMedCB.setChecked(true);
            isChecked = true;
        }
        if (dinner.getMedus().
                contains(getResources().getString(R.string.new_entry_bites_medus_nera))) {
            MedusNerCB.setChecked(true);
            isChecked = true;
        }
        if (!isChecked) { // was new entry - nothing to check
            MedusNerCB.setChecked(true); //// lets say it will be default value for new entry
        }

        komentarasET.setText(String.valueOf(dinner.getKomentaras()));

        seimosNrET.setText(String.valueOf(dinner.getSeimosNr()));

        if(!dinner.getMotinele(). // delivery - no
                equalsIgnoreCase(getResources().getString(R.string.new_entry_bites_Motinele_yra))){
            ((RadioButton)MotineleRG.getChildAt(0)).setChecked(true);
        } else { // delivery - yes
            ((RadioButton)MotineleRG.getChildAt(1)).setChecked(true);
            ((RadioButton)MotineleRG.getChildAt(2)).setChecked(true);
        }

        // Sets spinner value from object payment type


    }

    private Dinner getDataFromForm() {
        String pletimasTypes = "";
        if (PletBgCB.isChecked()) {
            pletimasTypes = pletimasTypes + PletBgCB.getText().toString() + " ";
        }
        if (PletNeCB.isChecked()) {
            pletimasTypes = pletimasTypes + PletNeCB.getText().toString() + " ";
        }
        if (PletReCB.isChecked()) {
            pletimasTypes = pletimasTypes + PletReCB.getText().toString() + " ";
        }
        if (PletSrCB.isChecked()) {
            pletimasTypes = pletimasTypes + PletSrCB.getText().toString() + " ";
        }

        String medus = "";
        if (MedusBevCB.isChecked()) {
            medus = medus + MedusBevCB.getText().toString() + " ";
        }
        if (MedusJauCB.isChecked()) {
            medus = medus + MedusJauCB.getText().toString() + " ";
        }
        if (MeduDarMedCB.isChecked()) {
            medus = medus + MeduDarMedCB.getText().toString() + " ";
        }
        if (MedusMedCB.isChecked()) {
            medus = medus + MedusMedCB.getText().toString() + " ";
        }
        if (MedusNerCB.isChecked()) {
            medus = medus + MedusNerCB.getText().toString() + " ";
        }

        int selectedMotineleType = MotineleRG.getCheckedRadioButtonId();
        RadioButton MotineleType = findViewById(selectedMotineleType);
        String selectedMotineleTypeBtnName = MotineleType.getText().toString();

        String Komentaras = komentarasET.getText().toString();

        String seimosNr = seimosNrET.getText().toString();

        Dinner dinner = new Dinner(pletimasTypes, selectedMotineleTypeBtnName, Komentaras, medus, seimosNr);

        return dinner;
    }

    private void insertToDB (Dinner dinner){
        class NewEntry extends AsyncTask<String, Void, String> {

            ProgressDialog loading;
            DB db = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NewEntryActivity.this,
                        getResources().getString(R.string.new_entry_database_info),
                        null, true, true);
            }

            @Override
            protected String doInBackground(String... strings) {
                // Pirmas string yra raktas, antras - reikšmė.
                HashMap<String, String> pietus = new HashMap<String, String>();
                pietus.put("pletimas", strings[2]);
                pietus.put("motinele", strings[1]);
                pietus.put("komentaras", strings[4]);
                pietus.put("seimosNr", strings[0]);
                pietus.put("medus", strings[3]);
                pietus.put("action", "insert");

                String result = db.sendPostRequest(INSERT_URL, pietus);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(NewEntryActivity.this,
                        s,
                        Toast.LENGTH_SHORT).show();
                Intent eitiIPaieskosLanga = new Intent(NewEntryActivity.this,SearchActivity.class);
                startActivity(eitiIPaieskosLanga);
            }

        }
        NewEntry newEntry = new NewEntry();
        newEntry.execute(
               dinner.getPletimasType(),
                dinner.getMotinele(),
                dinner.getKomentaras(),
                dinner.getMedus(),
                dinner.getSeimosNr()
        );

    }


}
