package com.example.linah_alkhurayyif_studyappsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_cardview.view.*

class StudyAdapter(private val activityKotlin: KotlinReview?=null, private val activityAndroid: AndroidReview?=null, private val carditems: ArrayList<StudyMaterials>, val tableName:String): RecyclerView.Adapter<StudyAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_cardview,
                parent,
                false ))}
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
val item = carditems[position]
        holder.itemView.apply {
            Titletext.text = item.topics
            Detailstext.text= item.descriptions

                Card.setOnClickListener{
                    if(tableName=="kotlinTable"){
                        customAlert(activityKotlin!!,item.topics,item.descriptions)
                    }else{
                        customAlert(activityAndroid!!,item.topics,item.descriptions)
                    }
                }
                delete.setOnClickListener{
                    if(tableName=="kotlinTable"){
                        activityKotlin!!.delete_Data(StudyMaterials(item.id,item.topics,item.descriptions))
                    }else{
                        activityAndroid!!.delete_Data(StudyMaterials(item.id,item.topics,item.descriptions))
                    }

                }
                edit.setOnClickListener{
                    if(tableName=="kotlinTable"){
                        activityKotlin!!.raiseDialog(StudyMaterials(item.id,item.topics,item.descriptions))
                    }else{
                        activityAndroid!!.raiseDialog(StudyMaterials(item.id,item.topics,item.descriptions))
                    }
                   }
        }
    }
    override fun getItemCount() = carditems.size}