package com.example.motionlayouttelegram.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.motionlayouttelegram.R
import com.example.motionlayouttelegram.databinding.MainFragmentBinding


class MainFragment : Fragment() {
    private lateinit var mViewModel: MainViewModel
    private var mBinding: MainFragmentBinding? = null
    private lateinit var mMediaAdapter: MediaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        mBinding?.model = mViewModel
        mBinding?.lifecycleOwner = viewLifecycleOwner

        mViewModel.name.postValue("Rainbow")
        mViewModel.imgUrl.postValue("https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/rainbow.jpg")

        val dataSource = mutableListOf<MediaAdapter.ItemData>()
        dataSource.add(MediaAdapter.ItemData("Flying in the Light","flying_in_the_light.jpg"))
        dataSource.add(MediaAdapter.ItemData("Caterpillar",  "caterpillar.jpg"))
        dataSource.add(MediaAdapter.ItemData("Look Me in the Eye", "look_me_in_the_eye.jpg"))
        dataSource.add(MediaAdapter.ItemData("Flamingo", "flamingo.jpg"))
        dataSource.add(MediaAdapter.ItemData("Rainbow", "rainbow.jpg"))
        dataSource.add(MediaAdapter.ItemData("Over there", "over_there.jpg"))
        dataSource.add(MediaAdapter.ItemData("Jelly Fish 2", "jelly_fish_2.jpg"))
        dataSource.add(MediaAdapter.ItemData("Lone Pine Sunset", "lone_pine_sunset.jpg"))

        mMediaAdapter = MediaAdapter(viewLifecycleOwner)
        mBinding?.recyclerMedia?.adapter = mMediaAdapter

        val gridLayoutManager = GridLayoutManager(context, 3)
        mBinding?.recyclerMedia?.layoutManager = gridLayoutManager
        mBinding?.recyclerMedia?.addItemDecoration(
            GridSpacingItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.grid_item_spacing
                )
            )
        )
        mBinding?.recyclerMedia?.setHasFixedSize(true)
        mMediaAdapter.setModelData(dataSource)

        mBinding?.motionLayout?.setTransitionListener(
            object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}
                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                }
                override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
            }
        )
        mBinding?.motionLayout?.setTransition(R.id.step2, R.id.step1)
        mBinding?.motionLayout?.transitionToEnd()
        mBinding?.motionLayout?.transitionToStart()

        return mBinding!!.root
    }

    companion object {
        fun newInstance() = MainFragment()

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                Glide.with(view.context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view)
        }
    }
}