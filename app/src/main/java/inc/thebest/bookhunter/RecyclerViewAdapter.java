package inc.thebest.bookhunter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.CardView;
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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amitkumar on 07/01/18 at 08:56.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<Book> bookList;
    private Context context;

    //You need to create method and pass context of that activity here w8



    public RecyclerViewAdapter(Context context,List<Book> fetchedBooks) {
        this.context = context;
        this.bookList = fetchedBooks;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Book book = bookList.get(position);
        holder.book_name.setText(bookList.get(position).getmTitle());
        holder.book_author.setText(bookList.get(position).getmAuthors());
        holder.book_ISBN_13.setText(bookList.get(position).getmISBN_13());
        //Glide.with(holder.book_jacket.getContext()).load(book.getmImageLink()).into(holder.book_jacket);
        Picasso.with(context).load(book.getmImageLink()).into(holder.book_jacket);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getmBuyLink()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

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
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            book_jacket = itemView.findViewById(R.id.book_jacket);
            book_name = itemView.findViewById(R.id.tv_book_name);
            book_author = itemView.findViewById(R.id.tv_book_author);
            book_ISBN_13 = itemView.findViewById(R.id.tv_book_isbn);
            cv = itemView.findViewById(R.id.card_view);
        }
    }


}
