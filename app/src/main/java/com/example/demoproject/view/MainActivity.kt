package com.example.demoproject.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.adapter.RecyclerViewAdapter
import com.example.demoproject.databinding.ActivityMainBinding
import com.example.demoproject.model.ModelData
import com.example.demoproject.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnDataClickListener {

    private lateinit var viewModel: MainViewModel
    private val recylerViewAdapter = RecyclerViewAdapter(arrayListOf(), this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

    }

    private fun initialize() {

        binding.myToolbar.setTitle("Home")
        binding.recyclerView.setHasFixedSize(true)
        val llm = LinearLayoutManager(this)
        binding.recyclerView.setLayoutManager(llm)

        binding.btnGet.setOnClickListener {

            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.refreshData(binding.edtName.text.toString())

            binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.recyclerView.adapter = recylerViewAdapter

            observeLiveData()

        }
    }

    private fun observeLiveData() {

        viewModel.data.observe(this, Observer { data ->
            data?.let {
                binding.recyclerView.visibility = View.VISIBLE
                recylerViewAdapter.updateDataList(data)
            }
        })

        viewModel.dataError.observe(this, Observer { error ->
            error?.let {
                if (it) {
                    binding.dataError.visibility = View.VISIBLE
                } else {
                    binding.dataError.visibility = View.GONE
                }
            }

        })
        viewModel.dataLoading.observe(this, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.dataLoading.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.dataError.visibility = View.GONE
                } else {
                    binding.dataLoading.visibility = View.GONE
                }
            }

        })
    }

    override fun onItemClick(item: ModelData, posistion: Int) {
        val intent = Intent(this, Details::class.java)
        intent.putExtra("Name", item.name)
        intent.putExtra("Url", item.owner.avatar_url)
        intent.putExtra("Owner", item.owner.login)
        intent.putExtra("Stars", item.stargazers_count)
        intent.putExtra("OpenIssue", item.open_issues)
        startActivity(intent)
    }

}