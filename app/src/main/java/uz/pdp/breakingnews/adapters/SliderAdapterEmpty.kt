package uz.pdp.breakingnews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.databinding.SliderItemEmptyBinding

class SliderAdapterEmpty(var context: Context, var list: List<String>) : RecyclerView.Adapter<SliderAdapterEmpty.VH>() {


    inner class VH(var binding: SliderItemEmptyBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(SliderItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            val article = list[position]

            Glide.with(context).load(article).placeholder(R.drawable.ic_launcher_background).into(bigImage)
        }
    }

    override fun getItemCount(): Int = list.size


}