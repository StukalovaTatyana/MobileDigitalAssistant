package ru.asu.mobiledigitalassistant.ui.formrecordinganevent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import ru.asu.mobiledigitalassistant.MainActivity;
import ru.asu.mobiledigitalassistant.R;
import ru.asu.mobiledigitalassistant.pojo.Client;
import ru.asu.mobiledigitalassistant.pojo.Event;
import ru.asu.mobiledigitalassistant.pojo.Faculty;
import ru.asu.mobiledigitalassistant.pojo.Forms;
import ru.asu.mobiledigitalassistant.pojo.Group;

public class FormRecordingAnEventActivity extends AppCompatActivity {

    private FormRecordingAnEventViewModel mViewModel;
    private Group selectedGroup;
    private List<Group> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_recording_an_event);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Запись");
        addListenerOnButton();
        mViewModel = new FormRecordingAnEventViewModel();
        Spinner spinner = findViewById(R.id.faculty);
        List<Faculty> faculties = mViewModel.getFaculties();
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                faculties);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                groupList = mViewModel.getFaculty(faculties.get(position).getId());
                ArrayAdapter groupAdapter = new ArrayAdapter(
                        getBaseContext(),
                        android.R.layout.simple_spinner_item,
                        groupList);
                Spinner groupSpinner = findViewById(R.id.group);
                groupSpinner.setAdapter(groupAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        Spinner groupSpinner = findViewById(R.id.group);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGroup = groupList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addListenerOnButton() {
        Button button = findViewById(R.id.buttonSend);
        button.setOnClickListener(
                v -> {
                    Forms forms = new Forms();

                    Event event = new Event();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
                    event.setId(getIntent().getIntExtra("id", 0));
                    event.setNameEvent(getIntent().getStringExtra("name"));
                    event.setDescription(getIntent().getStringExtra("description"));
                    /*try {
                        event.setBeginDate(simpleDateFormat.parse(getIntent().getStringExtra("dateBegin")));
                        event.setEndDate(simpleDateFormat.parse(getIntent().getStringExtra("dateEnd")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/

                    Client client = new Client();
                    EditText surname = findViewById(R.id.surname);
                    client.setSurname(surname.getText().toString());
                    EditText name = findViewById(R.id.name);
                    client.setName(name.getText().toString());
                    EditText patronumic = findViewById(R.id.patronumic);
                    client.setPatronymic(patronumic.getText().toString());
                    EditText phone = findViewById(R.id.phone);
                    client.setPhone(phone.getText().toString());
                    client.setGroup(selectedGroup);

                    forms.setClient(client);
                    forms.setEvent(event);
//                    forms.setCreateDate(new Date().toInstant());

                    Forms form = mViewModel.createForm(forms);
                    if (form != null) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Вы успешно зарегистрировались!", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(FormRecordingAnEventActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}