package uz.pdp.breakingnews.screens

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.databinding.ActivityWelcomeBinding
import uz.pdp.breakingnews.preference.MyShared

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private var myShared = MyShared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window?.statusBarColor = Color.WHITE
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myShared = MyShared.getInstance(this)
        binding.apply {

            cardGetStart.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, FavouriteActivity::class.java))
            }

        }
    }
}