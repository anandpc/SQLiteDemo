package anandpc.github.io.sqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DB_NAME = "data";
    private static final String DATA_LIST = "data";

    Button save,view,update,delete;
    EditText roll,name;
    MyDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = findViewById(R.id.roll);
        name = findViewById(R.id.name);
        save = findViewById(R.id.save);
        view = findViewById(R.id.view);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        dbHelper = new MyDbHelper(this,DB_NAME,null,1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(roll.getText().toString(),name.getText().toString());
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void viewData() {

        Intent intent = new Intent(getApplicationContext(),ViewActivity.class);
        ArrayList<String> data = new ArrayList<>();

        Cursor cursor = dbHelper.view();
        while (cursor.moveToNext()){
            String res = cursor.getString(1)+" : "+cursor.getString(2);
            data.add(res);
        }
        intent.putStringArrayListExtra(DATA_LIST,data);
        startActivity(intent);
    }

    private void saveData(String roll, String name) {
        if(dbHelper.Save(roll,name)){
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Error While Saving",Toast.LENGTH_SHORT).show();
        }
    }
}
