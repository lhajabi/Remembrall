package com.example.remembrall

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter internal constructor(
    private val activity: Activity,
    private val context: Context,
    private val id: ArrayList<*>,
    private val act: ArrayList<*>,
    private val Desc: ArrayList<*>
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.idTxt.text = id[position].toString()
        holder.actTxt.text = act[position].toString()
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("id", id[position].toString())
            intent.putExtra("Act", act[position].toString())
            intent.putExtra("Description", Desc[position].toString())
            activity.startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int {
        return id.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var idTxt: TextView
        var actTxt: TextView
        var mainLayout: LinearLayout

        init {
            idTxt = itemView.findViewById(R.id.book_id_txt)
            actTxt = itemView.findViewById(R.id.book_title_txt)
            mainLayout = itemView.findViewById(R.id.mainLayout)
            //Animate Recyclerview
            val translateAnim = AnimationUtils.loadAnimation(
                context, R.anim.translate_anim
            )
            mainLayout.animation = translateAnim
        }
    }
}
