package uz.pdp.breakingnews

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import uz.pdp.breakingnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window?.statusBarColor = Color.WHITE

        binding.apply {
            val navController = (supportFragmentManager.findFragmentById(R.id.my_support_nav) as NavHostFragment).navController
            NavigationUI.setupWithNavController(bottomMain, navController)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_support_nav).navigateUp()
    }

    fun hide() {
        binding.bottomMain.visibility = View.GONE
    }

    fun show() {
        binding.bottomMain.visibility = View.VISIBLE
    }

}