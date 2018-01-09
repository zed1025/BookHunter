package inc.thebest.bookhunter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;

/**
 * Created by amitkumar on 07/01/18 at 08:56.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<Book> bookList;

    public RecyclerViewAdapter(List<Book> fetchedBooks) {
        this.bookList = fetchedBooks;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.book_name.setText(bookList.get(position).getmTitle());
        holder.book_author.setText(bookList.get(position).getmAuthors());
        holder.book_ISBN_13.setText(bookList.get(position).getmISBN_13());
//        final ImageRequest imageRequest = new ImageRequest(book.getmImageLink(),
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        holder.book_jacket.setImageBitmap(response);
//                    }
//                },
//                0, 0, ImageView.ScaleType.CENTER_CROP, null,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//        MySingleton.getInstance(this).addToRequestQueue(imageRequest);
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrow, parent, false));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    //inner ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView book_jacket;
        TextView book_name;
        TextView book_author;
        TextView book_ISBN_13;

        public ViewHolder(View itemView) {
            super(itemView);

            book_jacket = itemView.findViewById(R.id.book_jacket);
            book_name = itemView.findViewById(R.id.tv_book_name);
            book_author = itemView.findViewById(R.id.tv_book_author);
            book_ISBN_13 = itemView.findViewById(R.id.tv_book_isbn);
        }
    }
}
