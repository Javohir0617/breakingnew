package uz.pdp.breakingnews.dagger.di.modul

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.pdp.breakingnews.dagger.data.dao.ArticleDao
import uz.pdp.breakingnews.dagger.data.database.MyDatabaseHelper
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context = context

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): MyDatabaseHelper {
        return Room.databaseBuilder(context, MyDatabaseHelper::class.java, "my_db")
            .allowMainThreadQueries()
            .build()

    }

    @Provides
    @Singleton
    fun providesDao(myDatabaseHelper: MyDatabaseHelper): ArticleDao = myDatabaseHelper.getHelper()

}