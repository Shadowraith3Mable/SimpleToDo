package sg.edu.rp.c346.id21022328.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Task;
    Button Add;
    Button Clear;
    ArrayList<String> tasklist;
    ArrayAdapter thetask;
    ListView List;
    String Gettext;
    Spinner spinner2;
    Button Delete;
    String GetIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task = findViewById(R.id.task);
        Add = findViewById(R.id.Add);
        Clear = findViewById(R.id.Clear);
        List = findViewById(R.id.List);
        spinner2 = findViewById(R.id.spinner);
        Delete = findViewById(R.id.Delete);


        tasklist = new ArrayList<String>();

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Delete.setEnabled(false);
                        Add.setEnabled(true);
                        Clear.setEnabled(true);

                        break;
                    case 1:
                        Add.setEnabled(false);
                        Clear.setEnabled(true);
                        Delete.setEnabled(true);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


                Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Gettext = Task.getText().toString();
                        tasklist.add(Gettext);

                        thetask.notifyDataSetChanged();

                    }
                });


        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thetask.clear();
                Task.getText().clear();
                thetask.notifyDataSetChanged();
                
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gettext = Task.getText().toString();
                int toInt = Integer.parseInt(Gettext);
                if (tasklist.size() == 0){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }
                else if(toInt >= 0 && toInt <= tasklist.size()){
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();

                }
                tasklist.remove(toInt);

                thetask.notifyDataSetChanged();
            }
        });


        thetask = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tasklist);
        List.setAdapter(thetask);


    }
}