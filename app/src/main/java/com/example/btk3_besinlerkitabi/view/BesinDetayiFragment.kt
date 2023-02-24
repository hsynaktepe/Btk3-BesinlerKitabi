package com.example.btk3_besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.btk3_besinlerkitabi.R
import com.example.btk3_besinlerkitabi.databinding.FragmentBesinDetayiBinding
import com.example.btk3_besinlerkitabi.util.gorselIndir
import com.example.btk3_besinlerkitabi.util.placeholderYap
import com.example.btk3_besinlerkitabi.viewmodel.BesinDetayiViewModel

class BesinDetayiFragment : Fragment() {
    private lateinit var viewModel: BesinDetayiViewModel
    private var besinId = 0
    private lateinit var dataBinding: FragmentBesinDetayiBinding

    val besinIsim: TextView
        get() = view?.findViewById<TextView>(R.id.besinIsim) as TextView
    val besinKalori: TextView
        get() = view?.findViewById<TextView>(R.id.besinKalori) as TextView
    val besinKarbonhidrat: TextView
        get() = view?.findViewById<TextView>(R.id.besinKarbonhidrat) as TextView
    val besinProtein: TextView
        get() = view?.findViewById<TextView>(R.id.besinProtein) as TextView
    val besinYag: TextView
        get() = view?.findViewById<TextView>(R.id.besinYag) as TextView
    val besinImage: ImageView
        get() = view?.findViewById<ImageView>(R.id.besinImage) as ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_besin_detayi,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            besinId = BesinDetayiFragmentArgs.fromBundle(it).besinId
        }

        viewModel = ViewModelProviders.of(this).get(BesinDetayiViewModel::class.java)
        viewModel.roomVerisiniAl(besinId)


        observeLiveData()

        /* val besinDetayiButton = view.findViewById<Button>(R.id.besin_detayi_button)
         besinDetayiButton.setOnClickListener {
             val action = BesinDetayiFragmentDirections.actionBesinDetayiFragmentToBesinListesiFragment()
             Navigation.findNavController(it).navigate(action)
         }*/

    }

    fun observeLiveData() {
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin ->
            besin?.let {

                dataBinding.secilenBesin = it

                /*besinIsim.text = it.besinIsim
                besinKalori.text = it.besinKalori
                besinKarbonhidrat.text = it.besinKarbonhidrat
                besinProtein.text = it.besinProtein
                besinYag.text = it.besinYag
                context?.let {
                    besinImage.gorselIndir(besin.besinGorsel, placeholderYap(it))
                }*/
            }
        })
    }

}