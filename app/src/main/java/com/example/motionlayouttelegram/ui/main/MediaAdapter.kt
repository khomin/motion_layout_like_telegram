package com.example.motionlayouttelegram.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.motionlayouttelegram.R
import com.example.motionlayouttelegram.databinding.ItemMediaBinding

class MediaAdapter(private var lifecycleOwner: LifecycleOwner)
    : RecyclerView.Adapter<MediaAdapter.MyViewHolder>() {
    private var mDataList = ArrayList<ItemMediaModel>()

    companion object {
        const val thumbnailUri = "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/"
        const val fullUri = "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/"
    }

    data class ItemData(
        val description: String?,
        val imgUri: String?
    )

    fun setModelData(data: MutableList<ItemData>) {
        mDataList.clear()
        for(it in data) {
            val modelItem = ItemMediaModel()
            modelItem.mediaDescription.postValue(it.description)
            modelItem.imageUri.postValue(thumbnailUri + it.imgUri)
            mDataList.add(modelItem)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMediaBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_media,
            parent,
            false
        )
        binding.lifecycleOwner = lifecycleOwner
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (mDataList.size >= position) {
            val model = mDataList[position]
            holder.bind(model)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    class MyViewHolder(
        private val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        private var model: ItemMediaModel? = null

        fun bind(model: ItemMediaModel) {
            this.model = model
            binding.model = model
            binding.executePendingBindings()

//            Glide.with(binding.mediaItemImage).load(model.)
        }
    }
}