package com.justagg.app.ui.home.ui.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.justagg.app.data.response.DummyResponse
import com.justagg.app.databinding.ItemContactBinding
import com.justagg.app.databinding.ItemSocialLinkBinding

class ContactsAdapter(
    private var mList: MutableList<DummyResponse>
) : RecyclerView.Adapter<ContactsAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemContactBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.run {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size

    inner class VH(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DummyResponse) {

            binding.civUser.setImageResource(item.icon)
            binding.tvName.text = item.name
            binding.tvDesignation.text = item.designation
            binding.tvDate.text = item.date

            if (item.status){
                binding.civStatus.visibility = View.VISIBLE
            } else {
                binding.civStatus.visibility = View.GONE
            }

        }

    }

}
