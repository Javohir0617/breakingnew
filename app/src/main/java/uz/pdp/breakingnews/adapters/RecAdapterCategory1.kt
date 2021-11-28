package uz.pdp.breakingnews.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.breakingnews.R

import uz.pdp.breakingnews.databinding.ItemCategoryBinding
import uz.pdp.breakingnews.databinding.ItemCategoryDesignBinding
import uz.pdp.breakingnews.madels.adap.RVClass

class RecAdapterCategory1(var list: List<RVClass>, var listener: OnCardClicked): RecyclerView.Adapter<RecAdapterCategory1.VH>() {

    interface OnCardClicked {
        fun onclick(rvClass: Int, checked: Boolean)
    }

    inner class VH(var binding: ItemCategoryDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
                card.setBackgroundResource(R.drawable.category_border_ischecked)
            } else {
                card.setBackgroundResource(R.drawable.category_border)
            }

        }
    }

    override fun getItemCount(): Int = list.size
}