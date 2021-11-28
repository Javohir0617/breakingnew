package uz.pdp.breakingnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.databinding.FragmentProfileBinding
import uz.pdp.breakingnews.preference.MyShared

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    var myShared = MyShared

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myShared = MyShared.getInstance(requireContext())

        binding.apply {

            val boolean = myShared.getList("theme")
            if (boolean == "1" ) {
                sw.isChecked = true
            } else {
                sw.isChecked = false
            }

            sw.setOnCheckedChangeListener { view, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    myShared.setList("theme", "1")
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    myShared.setList("theme", "0")
                }
            }

        }
    }
}