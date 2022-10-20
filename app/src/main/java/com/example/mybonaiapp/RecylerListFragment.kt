package com.example.mybonaiapp

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonaiapp.adapter.RecyclerViewAdapter
import com.example.mybonaiapp.model.MyRecylerList
import com.example.mybonaiapp.viewmodel.MainActivityViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RecylerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecylerListFragment : Fragment() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view =inflater.inflate(R.layout.fragment_recyler_list, container, false)
        initiateViewModel(view)
        initViewModel()
          return view
    }

    private fun initiateViewModel(view:View){
        val recyclerView =view.findViewById<RecyclerView>(R.id.recyclerView)
        if(getActivity()?.getResources()?.getConfiguration()?.orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.layoutManager =GridLayoutManager(activity,2)
        }
        else{
            recyclerView.layoutManager =GridLayoutManager(activity,3)
        }
        val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerViewAdapter =RecyclerViewAdapter()
        recyclerView.adapter =recyclerViewAdapter
    }

    private fun initViewModel(){
        val viewModel =ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<MyRecylerList> {
            if(it != null){
                recyclerViewAdapter.setUpData(it._embedded.contents)
            }
        })
        viewModel.callApi()
    }


    companion object {
        @JvmStatic
        fun newInstance() = RecylerListFragment()
    }
}