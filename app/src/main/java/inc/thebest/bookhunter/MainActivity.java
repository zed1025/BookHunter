package inc.thebest.bookhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*
    Base URL for GoogleBooks API
        https://www.googleapis.com/books/v1/volumes?q=
 */
public class MainActivity extends AppCompatActivity {

    private EditText searchText;
    private Button search;
    //Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setActionBar(toolbar);

        searchText = findViewById(R.id.et_search);
        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchKeyword = new String();
                searchKeyword = searchText.getText().toString();

                //Starting an intent to DisplayBooksActivity, with the searchKeyword String
                Intent i = new Intent(getApplicationContext(), DisplayBooksActivity.class);
                i.putExtra("searchString", searchKeyword);
                startActivity(i);
            }
        });

    }
}
