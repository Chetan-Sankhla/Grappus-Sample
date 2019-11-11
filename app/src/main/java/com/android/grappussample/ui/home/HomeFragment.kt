package com.android.grappussample.ui.home

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.grappussample.R
import com.android.grappussample.ui.plan.PlanActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnTouchListener, View.OnDragListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        competitionImageView.setOnClickListener { }
        competitionImageView.setOnLongClickListener { view ->
            val item = ClipData.Item(view?.tag.toString())
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

            val dragData = ClipData(view?.tag.toString(), mimeTypes, item)
            val myShadow = View.DragShadowBuilder(competitionImageView)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(dragData, myShadow, view, 0)
            } else {
                view.startDrag(dragData, myShadow, view, 0)
            }

            true
        }

        trainingImageView.setOnClickListener { }
        trainingImageView.setOnLongClickListener { view ->
            val item = ClipData.Item(view?.tag.toString())
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

            val dragData = ClipData(view?.tag.toString(), mimeTypes, item)
            val myShadow = View.DragShadowBuilder(competitionImageView)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(dragData, myShadow, view, 0)
            } else {
                view.startDrag(dragData, myShadow, view, 0)
            }

            true
        }

        restImageView.setOnClickListener { }
        restImageView.setOnLongClickListener { view ->
            val item = ClipData.Item(view?.tag.toString())
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

            val dragData = ClipData(view?.tag.toString(), mimeTypes, item)
            val myShadow = View.DragShadowBuilder(competitionImageView)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(dragData, myShadow, view, 0)
            } else {
                view.startDrag(dragData, myShadow, view, 0)
            }

            true
        }

        competitionImageView.setOnTouchListener(this)
        trainingImageView.setOnTouchListener(this)
        restImageView.setOnTouchListener(this)
        addImageView.setOnDragListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {

        val mShadow = View.DragShadowBuilder(view)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

        when (view.id) {
            R.id.competitionImageView -> {
                val item = ClipData.Item("Competition")
                val data = ClipData("Competition", mimeTypes, item)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, mShadow, view, 0)
                } else {
                    view.startDrag(data, mShadow, view, 0)
                }
            }

            R.id.trainingImageView -> {

                val item = ClipData.Item("Training")
                val data = ClipData("Training", mimeTypes, item)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, mShadow, null, 0)
                } else {
                    view.startDrag(data, mShadow, null, 0)
                }
            }

            R.id.restImageView -> {

                val item = ClipData.Item("Rest")
                val data = ClipData("Rest", mimeTypes, item)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, mShadow, null, 0)
                } else {
                    view.startDrag(data, mShadow, null, 0)
                }
            }
        }
        return false
    }

    override fun onDrag(view: View, dragEvent: DragEvent): Boolean {

        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                (view as ImageView).setColorFilter(Color.YELLOW)


                view.invalidate()
                return true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                val clipData = dragEvent.clipDescription.label.toString()

                when (clipData) {
                    "Competition" -> {
                        (view as ImageView).setColorFilter(
                            ContextCompat.getColor(
                                context!!,
                                android.R.color.holo_green_light
                            ), PorterDuff.Mode.MULTIPLY
                        )
                    }
                    "Training" -> {
                        (view as ImageView).setColorFilter(
                            ContextCompat.getColor(
                                context!!,
                                android.R.color.holo_orange_dark
                            ), PorterDuff.Mode.MULTIPLY
                        )
                    }
                    "Rest" -> {
                        (view as ImageView).setColorFilter(
                            ContextCompat.getColor(
                                context!!,
                                android.R.color.holo_blue_light
                            ), PorterDuff.Mode.MULTIPLY
                        )
                    }
                }
                view.invalidate()
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                return true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                (view as ImageView).clearColorFilter()
                view.setColorFilter(Color.YELLOW)


                view.invalidate()
                return true
            }
            DragEvent.ACTION_DROP -> {

                view.invalidate()

                val clipData = dragEvent.clipDescription.label.toString()
                val intent = Intent(activity!!, PlanActivity::class.java)
                intent.putExtra("Type", clipData)
                startActivity(intent)

                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                return true
            }
            else -> return false
        }

    }
}