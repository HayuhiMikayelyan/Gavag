package com.example.gavag.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gavag.R
import com.example.gavag.databinding.FragmentLogBinding
import com.example.gavag.screens.recycler.Model
import com.example.gavag.screens.recycler.RecAdapter

class LogFragment : Fragment() {

    lateinit var binding: FragmentLogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modelList = listOf(
            Model(R.drawable.img1,"000000000"),
            Model(R.drawable.img2,"000000001")
        )
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecAdapter(modelList)
        recycler.adapter = adapter
    }




}