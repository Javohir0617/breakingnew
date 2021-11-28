package uz.pdp.breakingnews.dagger.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.pdp.breakingnews.dagger.data.dao.ArticleDao
import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class MyDatabaseHelper: RoomDatabase() {

    abstract fun getHelper(): ArticleDao

}