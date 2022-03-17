package com.example.session2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "First App"
        val array = ArrayList<ItemModel>()
        array.add(ItemModel(R.drawable.ic_launcher_background, "Ahmed"))
        array.add(ItemModel(R.drawable.ic_launcher_background, "Abdo"))
        array.add(ItemModel(R.drawable.ic_launcher_background, "Eman"))
        val adapter = Adapter(array)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter




    }
}