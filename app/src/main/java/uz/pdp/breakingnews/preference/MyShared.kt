package uz.pdp.breakingnews.preference

import android.content.Context
import android.content.SharedPreferences

object MyShared {

    val myShared = MyShared
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    fun getInstance(context: Context): MyShared {
        if (sharedPreferences == null) {
            sharedPreferences =context.getSharedPreferences("file", Context.MODE_PRIVATE)
        }
        return myShared
    }

    fun getList(str: String): String = sharedPreferences?.getString(str, "")!!

    fun setList(str: String, text: String) {
        editor = sharedPreferences?.edit()
        editor?.putString(str, text)?.commit()
    }
}