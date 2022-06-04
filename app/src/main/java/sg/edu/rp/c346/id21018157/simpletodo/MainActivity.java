package sg.edu.rp.c346.id21018157.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView tvToDo;
    EditText etTask;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTasks;
    Spinner spinner;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToDo = findViewById(R.id.textViewToDo);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.listViewTasks);
        spinner = findViewById(R.id.spinner);


        alTasks = new ArrayList<>();


        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etTask.getText().toString();
                alTasks.add(newTask);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int taskIndex = Integer.parseInt(etTask.getText().toString());
                alTasks.remove(taskIndex);
                aaTask.notifyDataSetChanged();

            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aaTask.clear();
                aaTask.notifyDataSetChanged();

            }
        });

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = alTasks.get(position);

                //enhancement 2 - "You don't have any task to remove"
//                if (aaTask.isEmpty()) { //enhancement 2 - "You don't have any task to remove"
//                    String taskPos = alTasks.get(position);
//                    Toast.makeText(MainActivity.this, taskPos, Toast.LENGTH_LONG).show();
//                }

                //enhancement 2 - "Wrong Index Number"
//                if (task.contains(etTask.getText())) {
//                    Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_LONG).show();
//                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btnDelete.setEnabled(false); //case 1 of spinner - remove
                        btnAdd.setEnabled(true);
                        btnDelete.setTextColor(Color.GRAY);
                        btnAdd.setTextColor(Color.BLACK);
                        break;

                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        btnAdd.setTextColor(Color.GRAY);
                        btnDelete.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}