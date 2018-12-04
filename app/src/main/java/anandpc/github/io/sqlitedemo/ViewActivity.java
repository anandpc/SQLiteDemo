package anandpc.github.io.sqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private static final String DATA_LIST = "data";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        result = findViewById(R.id.result);

        Intent intent = getIntent();

        ArrayList<String> data = new ArrayList<>();
        data = intent.getStringArrayListExtra(DATA_LIST);

        result.setText(data.toString());

    }
}
