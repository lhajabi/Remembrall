package com.example.remembrall

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ERRAND + " TEXT, " +
                COLUMN_DESC + " TEXT);"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addErrand(errand: String, desc: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ERRAND, errand)
        cv.put(COLUMN_DESC, desc)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun updateData(id: String, errand: String, description: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ERRAND, errand)
        cv.put(COLUMN_DESC, description)
        val result = db.update(TABLE_NAME, cv, "id=?", arrayOf(id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneRow(row_id: String) {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "id=?", arrayOf(row_id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

    companion object {
        private const val DATABASE_NAME = "ErrandLibrary.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "my_library"
        private const val COLUMN_ID = "id"
        private const val COLUMN_ERRAND = "errand"
        private const val COLUMN_DESC = "description"
    }
}