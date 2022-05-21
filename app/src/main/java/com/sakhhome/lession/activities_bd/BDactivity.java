package com.sakhhome.lession.activities_bd;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sakhhome.lession.R;

import androidx.annotation.Nullable;

public class BDactivity extends Activity implements View.OnClickListener {

    EditText edID, edName, edEmail;
    Button btnReadBD, btnWriteBD, btnClearBD, btnUpdateBD, btnDeleteBD;
    DBHelper dbHelper;

    TextView txtAllDataBD;

    private static final String DATABASE = "mybase";
    private static final String TABLE = "test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

        edID = findViewById(R.id.edID);
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);

        txtAllDataBD = findViewById(R.id.txtAllDataBD);

        btnReadBD = findViewById(R.id.btnReadBD);
        btnReadBD.setOnClickListener(this);

        btnWriteBD = findViewById(R.id.btnWriteBD);
        btnWriteBD.setOnClickListener(this);

        btnClearBD = findViewById(R.id.btnClearBD);
        btnClearBD.setOnClickListener(this);

        btnUpdateBD = findViewById(R.id.btnUpdateBD);
        btnUpdateBD.setOnClickListener(this);

        btnDeleteBD = findViewById(R.id.btnDeleteBD);
        btnDeleteBD.setOnClickListener(this);

        dbHelper = new DBHelper(this, DATABASE, null, 1);
    }

    @Override
    public void onClick(View view) {

        ContentValues cv = new ContentValues();

        String name = edName.getText().toString();
        String email = edEmail.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String id = edID.getText().toString();

        switch (view.getId()){
            case R.id.btnReadBD:
                Cursor c = db.query(TABLE, null, null, null, null,null, null);
                if (c.moveToFirst()){
                    int indexId = c.getColumnIndex("id");
                    int indexName = c.getColumnIndex("name");
                    int indexEmail = c.getColumnIndex("email");
                    String text = "";
                    do{
                        String idC = "id: " + c.getString(indexId);
                        String nameC = "name: " + c.getString(indexName);
                        String emailC = "email: " +c.getString(indexEmail);
                        text += idC + ", " + nameC +", "+ emailC + "\n";

                        txtAllDataBD.setText(text);
                    }
                    while (c.moveToNext());
                }
                break;
            case R.id.btnWriteBD:
                cv.put("name", name);
                cv.put("email", email);

                long rowId = db.insert(TABLE, null, cv);
                Log.d("BASE", "id = " + rowId);
                break;
            case R.id.btnClearBD:
                db.delete(TABLE, null, null);
                break;
            case R.id.btnUpdateBD:
                if (id.equalsIgnoreCase(""))return;

                cv.put("name", name);
                cv.put("email", email);
                int updateCount = db.update(TABLE, cv, "id = ?", new String[]{id});
                Log.d("BASE", "update count = " + updateCount);
                break;
            case R.id.btnDeleteBD:
                if (id.equalsIgnoreCase(""))return;

                int delCount = db.delete(TABLE, "id = "+ id, null);
                Log.d("BASE", "delete count = " + delCount);
                break;
        }

        edName.setText("");
        edEmail.setText("");

        db.close();
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("BASE", "table = " + TABLE);
            db.execSQL("create table " +TABLE +" (id integer primary key autoincrement, name text, email text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }
    }
}
