package hr.algebra.iamu_projekt.DAL

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.algebra.iamu_projekt.MODEL.BookItem

private const val DB_NAME = "items.db"
private const val DB_VERSION = 1
private const val TABLE_NAME = "items"
private val CREATE_TABLE = "create table $TABLE_NAME( " +
        "${BookItem::_id.name} integer primary key autoincrement, " +
        "${BookItem::title.name} text not null, " +
        "${BookItem::author.name} text not null, " +
        "${BookItem::rating.name} integer not null, " +
        "${BookItem::pages.name} integer not null, " +
        "${BookItem::published.name} integer not null, " +
        "${BookItem::description.name} text not null, " +
        "${BookItem::cover.name} text not null, " +
        "${BookItem::download.name} text not null " +
        ")"
private const val DROP_TABLE = "drop table $TABLE_NAME"

class BooksSqlHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), BooksRepository {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }
    override fun delete(selection: String?, selectionArgs: Array<String>?)
            = writableDatabase.delete(TABLE_NAME, selection, selectionArgs)

    override fun insert(values: ContentValues?)
            = writableDatabase.insert(TABLE_NAME, null, values)

    override fun query(
        projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = readableDatabase
        .query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

    override fun update(
        values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = writableDatabase.update(TABLE_NAME, values, selection, selectionArgs)
}