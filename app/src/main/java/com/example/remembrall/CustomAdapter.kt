package com.example.remembrall

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter internal constructor(
    private val activity: Activity,
    private val context: Context,
    private val id: ArrayList<*>,
    private val errand: ArrayList<*>,
    private val description: ArrayList<*>
) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.id_txt.text = id[position].toString()
        holder.errand_txt.text = errand[position].toString()
        holder.description_txt.text = description[position].toString()
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", id[position].toString())
            intent.putExtra("errand", errand[position].toString())
            intent.putExtra("description", description[position].toString())
            activity.startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int {
        return id.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var id_txt: TextView
        var errand_txt: TextView
        var description_txt: TextView
        var mainLayout: LinearLayout

        init {
            id_txt = itemView.findViewById(R.id.book_id_txt)
            errand_txt = itemView.findViewById(R.id.book_title_txt)
            description_txt = itemView.findViewById(R.id.book_author_txt)
            mainLayout = itemView.findViewById(R.id.mainLayout)
            //Animate Recyclerview
            val translateAnim = AnimationUtils.loadAnimation(
                context, R.anim.translate_anim
            )
            mainLayout.animation = translateAnim
        }
    }
}