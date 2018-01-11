package inc.thebest.bookhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayBooksActivity extends AppCompatActivity {

    //private TextView t1;
    private List<Book> fetchedBooks;
    final String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);

        String url = makeUrl();
        fetchedBooks = new ArrayList<>();
        //t1 = findViewById(R.id.t1);

        //code for RecyclerView
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),fetchedBooks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.v("Url: ", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String title, author, isbn, imageL, buyL;
                        try{
                            JSONArray itemsArray = response.getJSONArray("items");
                            for(int i=0; i<itemsArray.length(); i++){

                                JSONObject iThObj = itemsArray.getJSONObject(i);
                                JSONObject volumeInfo = iThObj.getJSONObject("volumeInfo");
                                JSONObject saleInfo = iThObj.getJSONObject("saleInfo");


                                title = volumeInfo.getString("title");
                                author = volumeInfo.getJSONArray("authors").getString(0);
                                isbn = volumeInfo.getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier");
                                imageL = volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");
                                buyL = saleInfo.getString("buyLink");

                                fetchedBooks.add(new Book(title, author, isbn, imageL, buyL));
                                //fetchedBooks.add(new Book(title, author, isbn, imageL));
                                recyclerViewAdapter.notifyDataSetChanged();


//                                t1.setText(response.toString());
                                Log.v("title: ", title);
                                Log.v("author: ", author);
                                Log.v("isbn: ", isbn);
                                Log.v("imageLink: ", imageL);
                            }
                        }catch(JSONException e){
                                e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("onErrorResponse()", "Error in this method");
                    }
                });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);


        //hardcoded values
        /*
        Book b1 = new Book("Harry Potte and The Socers Stone", "J.K.Rowling", "1988456789");
        Book b2 = new Book("Harry Potte and The Socers Stone", "J.K.Rowling", "1988456789");
        Book b3 = new Book("Harry Potte and The Socers Stone", "J.K.Rowling", "1988456789");
        Book b4 = new Book("Harry Potte and The Socers Stone", "J.K.Rowling", "1988456789");
        Book b5 = new Book("Harry Potte and The Socers Stone", "J.K.Rowling", "1988456789");
        Book b6 = new Book("Harry Potte and The Socers Stone", "J.K.Rowling", "1988456789");
        fetchedBooks.add(b1);
        fetchedBooks.add(b2);
        fetchedBooks.add(b3);
        fetchedBooks.add(b4);
        fetchedBooks.add(b5);
        fetchedBooks.add(b6);
        recyclerViewAdapter.notifyDataSetChanged();
        */

        //fetching JSON, Image data from the API using Volley


    }

    private String makeUrl(){
        //intent contains the string searched by the user.
        Intent i = getIntent();
        String[] temp = i.getStringExtra("searchString").split(" ");
        StringBuilder tempUrl = new StringBuilder(baseUrl);
        for(int j=0; j<temp.length; j++){
            tempUrl.append(temp[j]);
            tempUrl.append("+");
        }
        String url = tempUrl.toString();
        return url.substring(0, url.length()-1);
    }
}
