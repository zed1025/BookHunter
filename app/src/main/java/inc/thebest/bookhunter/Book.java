package inc.thebest.bookhunter;

/**
 * Created by amitkumar on 07/01/18 at 01:26.
 */

public class Book {
    String mTitle;
    String mAuthor;
    String mPublisher;
    String mISBN_13;
    String mImageLink;
    String mBuyLink;

    public Book(String mTitle, String mAuthors, String mPublisher, String mISBN_13, String mImageLink, String mBuyLink) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthors;
        this.mPublisher = mPublisher;
        this.mISBN_13 = mISBN_13;
        this.mImageLink = mImageLink;
        this.mBuyLink = mBuyLink;
    }

    //for use
    public Book(String mTitle, String mAuthor, String mISBN_13, String mImageLink) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mISBN_13 = mISBN_13;
        this.mImageLink = mImageLink;
    }

    public Book(String mTitle, String mAuthor, String mISBN_13) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mISBN_13 = mISBN_13;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthors() {
        return mAuthor;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public String getmISBN_13() {
        return mISBN_13;
    }

    public String getmImageLink() {
        return mImageLink;
    }

    public String getmBuyLink() {
        return mBuyLink;
    }
}
