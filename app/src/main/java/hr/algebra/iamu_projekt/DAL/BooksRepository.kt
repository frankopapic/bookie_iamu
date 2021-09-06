package hr.algebra.iamu_projekt.DAL

import android.content.ContentValues
import android.database.Cursor

interface BooksRepository {
    fun delete(selection: String?, selectionArgs: Array<String>?): Int

    fun insert(values: ContentValues?): Long

    fun query(
        projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor?

    fun update(
        values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int
}