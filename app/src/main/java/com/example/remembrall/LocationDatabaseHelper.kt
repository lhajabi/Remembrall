package com.example.remembrall

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class LocationDatabaseHelper (private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(p0: SQLiteDatabase) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOCATION + " TEXT);"
        p0.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun addLocation(location: String) {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_LOCATION, location)
        val result = p0.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateData(id: String="1", location: String) {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_LOCATION, location)
        val result = p0.update(TABLE_NAME, cv, "id=?", arrayOf(id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData() {
        val p0 = this.writableDatabase
        p0.execSQL("DELETE FROM $TABLE_NAME")
    }

    companion object {
        private const val DATABASE_NAME = "LocationLibrary.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "location_library"
        private const val COLUMN_ID = "id"
        private const val COLUMN_LOCATION = "location"
    }
}