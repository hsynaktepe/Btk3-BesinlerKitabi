package com.example.btk3_besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.btk3_besinlerkitabi.R
import com.example.btk3_besinlerkitabi.adapter.BesinRecyclerAdapter
import com.example.btk3_besinlerkitabi.viewmodel.BesinListesiViewModel

class BesinListesiFragment : Fragment() {

    private lateinit var viewModel : BesinListesiViewModel

    private val recyclerBesinAdapter = BesinRecyclerAdapter(arrayListOf())

    val besinListRecycler: RecyclerView
        get() = view?.findViewById<RecyclerView>(R.id.besinListRecycler) as RecyclerView
    val besinHataMesaji: TextView
            get() = view?.findViewById<TextView>(R.id.besinHataMesaji) as TextView
 val besinYukleniyor: ProgressBar
            get() = view?.findViewById<ProgressBar>(R.id.besinYukleniyor) as ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        viewModel = ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refreshData()

        //var besinListRecycler = view.findViewById<RecyclerView>(R.id.besinListRecycler)
        besinListRecycler.layoutManager = LinearLayoutManager(context)
        besinListRecycler.adapter = recyclerBesinAdapter

        swipeRefreshLayout.setOnRefreshListener {
            besinYukleniyor.visibility = View.VISIBLE
            besinHataMesaji.visibility = View.GONE
            besinListRecycler.visibility = View.GONE

            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing = false

            /*var benimStringim = "Atıl"
            benimStringim.benimEklentim("denemeoğlu")*/
        }

        observeLiveData()

        /*val besinListesiButton = view.findViewById<Button>(R.id.besin_listesi_button)

        besinListesiButton.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(3)
            Navigation.findNavController(it).navigate(action)
        }*/

    }

    fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner, Observer { besinler->
            besinler?.let{
                besinListRecycler.visibility = View.VISIBLE
                recyclerBesinAdapter.besinListesiGuncelle(besinler)
            }
        })

        viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if(it){
                    besinHataMesaji.visibility = View.VISIBLE
                    besinListRecycler.visibility = View.GONE
                }else{
                    besinHataMesaji.visibility = View.GONE
                }
            }
        })

        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if(it){
                    besinListRecycler.visibility = View.GONE
                    besinHataMesaji.visibility = View.GONE
                    besinYukleniyor.visibility = View.VISIBLE
                }else{
                    besinYukleniyor.visibility = View.GONE
                }
            }

        })
    }
}