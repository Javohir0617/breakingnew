package uz.pdp.breakingnews.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import uz.pdp.breakingnews.databinding.ItemCategoryBinding
import uz.pdp.breakingnews.madels.adap.RVClass

class RecAdapterCategory(var list: List<RVClass>, var listener: OnCardClicked): RecyclerView.Adapter<RecAdapterCategory.VH>() {

    interface OnCardClicked {
        fun onclick(rvClass: Int, checked: Boolean)
    }

    inner class VH(var binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            val item = list[position]

            txt.text = item.text

            card.setOnClickListener {
                item.isChecked = !item.isChecked
                listener.onclick(position, item.isChecked)
            }

            if (item.isChecked) {
                card.setCardBackgroundColor(Color.parseColor("#475AD7"))
            } else {
                card.setCardBackgroundColor(Color.parseColor("#F3F4F6"))
            }

        }
    }

    override fun getItemCount(): Int = list.size
}