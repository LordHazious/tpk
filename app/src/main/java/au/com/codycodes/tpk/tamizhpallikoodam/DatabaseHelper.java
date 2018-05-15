package au.com.codycodes.tpk.tamizhpallikoodam;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "tpk";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create words table
        String CREATE_WORDS_TABLE = "CREATE TABLE words ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "english TEXT, "+
                "tamil TEXT, "+
                "image INTEGER, "+
                "audio INTEGER, "+
                "category TEXT )";

        // create words table
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older words table if existed
        db.execSQL("DROP TABLE IF EXISTS words");

        // create fresh words table
        this.onCreate(db);
    }

    // Words table name
    private static final String tWords = "words";

    // Words Table Column names
    private static final String kID = "id";
    private static final String kEnglish = "english";
    private static final String kTamil = "tamil";
    private static final String kImage = "image";
    private static final String kAudio = "audio";
    private static final String kCategory = "category";

    private static final String[] columns = {kID,kEnglish,kTamil,kImage,kAudio,kCategory};

    public void addWord(Word word){

        // for logging
        Log.d("addWord", word.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(kEnglish, word.getDefaultTranslation()); // get Default Translation
        values.put(kTamil, word.getTamilTranslation()); // get Tamil Translation
        values.put(kImage, word.getImageResourceId()); // get Image resource id
        values.put(kAudio, word.getAudioResourceId()); // get Audio Resource id
        values.put(kCategory, word.getCategory()); // get word category

        db.insert(tWords,null, values);

        db.close();
    }

    public ArrayList<Word> getWords(String category){
        ArrayList<Word> words = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(tWords, columns," category = ?", new String[] { String.valueOf(category) },null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                words.add(new Word(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5)));
            } while (cursor.moveToNext());
        }

        db.close();

        return words;
    }

    public void populateWords() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlCount = "SELECT count(*) FROM words";
        Cursor cursor = db.rawQuery(sqlCount, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 0) {

            // Colors //
            addWord(new Word("white", "வெள்ளை", R.drawable.color_white, R.raw.color_white, "Colors"));
            addWord(new Word("gray", "சாம்பல்", R.drawable.color_gray, R.raw.color_gray, "Colors"));
            addWord(new Word("black", "கருப்பு", R.drawable.color_black, R.raw.color_black, "Colors"));
            addWord(new Word("red", "சிவப்பு", R.drawable.color_red, R.raw.color_red, "Colors"));
            addWord(new Word("blue", "நீலமான", R.drawable.color_blue, R.raw.color_white, "Colors"));
            addWord(new Word("yellow", "மஞ்சள்", R.drawable.color_yellow, R.raw.color_white, "Colors"));
            addWord(new Word("green", "பச்சை", R.drawable.color_green, R.raw.color_green, "Colors"));
            addWord(new Word("brown", "பழுப்பு", R.drawable.color_brown, R.raw.color_brown, "Colors"));

        }
        db.close();
    }
}