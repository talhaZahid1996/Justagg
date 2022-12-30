package com.justagg.app.ui.home.ui.analytics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.justagg.app.data.response.DummyResponse
import com.justagg.app.databinding.ItemSocialLinkBinding

class AnalyticsAdapter(
    private var mList: MutableList<DummyResponse>
) : RecyclerView.Adapter<AnalyticsAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemSocialLinkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.run {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size

    inner class VH(val binding: ItemSocialLinkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DummyResponse) {

            binding.ivSocial.setImageResource(item.icon)
            binding.tvSocialName.text = item.name

        }

    }

}
