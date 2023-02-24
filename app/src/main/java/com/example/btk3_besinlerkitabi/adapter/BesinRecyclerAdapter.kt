package com.example.btk3_besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.btk3_besinlerkitabi.R
import com.example.btk3_besinlerkitabi.databinding.BesinRecyclerRowBinding
import com.example.btk3_besinlerkitabi.model.Besin
import com.example.btk3_besinlerkitabi.util.gorselIndir
import com.example.btk3_besinlerkitabi.util.placeholderYap
import com.example.btk3_besinlerkitabi.view.BesinListesiFragmentDirections

class BesinRecyclerAdapter(val besinListesi:ArrayList<Besin>) : RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>(), BesinClickListener{


    class BesinViewHolder(var view: BesinRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.besin_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<BesinRecyclerRowBinding>(inflater,R.layout.besin_recycler_row,parent,false)
        return BesinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {

        holder.view.besin = besinListesi[position]
        holder.view.listener = this


    /*var isim = holder.itemView.findViewById<TextView>(R.id.isim)
        var kalori = holder.itemView.findViewById<TextView>(R.id.kalori)
        kalori.text = besinListesi.get(position).besinKalori
        isim.text = besinListesi.get(position).besinIsim

        holder.itemView.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.findViewById<ImageView>(R.id.imageView).gorselIndir(besinListesi.get(position).besinGorsel,
            placeholderYap(holder.itemView.context)
        )*/
    }

    fun besinListesiGuncelle(yeniBesinListesi:List<Besin>){
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }

    override fun besinTiklandi(view: View) {
        val uuid = view.findViewById<TextView>(R.id.besin_uuid).text.toString().toIntOrNull()
        uuid?.let {
        val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(it)
        Navigation.findNavController(view).navigate(action)
        }
    }

}