package com.justForPractice.JustForPractice

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var itemViewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val layoutManger=LinearLayoutManager(this)
        layoutManger.orientation= LinearLayoutManager.VERTICAL

        itemViewModel= ViewModelProvider(this).get(ItemViewModel::class.java)

        recyclerView.layoutManager=layoutManger
        val adapter=MyAdapter(this,itemViewModel)
        recyclerView.adapter=adapter



        itemViewModel.allitems.observe(this, Observer { items ->
            items?.let { adapter.setItems(it) }
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent= Intent(this ,AddEditItem::class.java)
            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete ->{
                itemViewModel.deleteAll()
            }
            else ->{}
        }

        return when (item.itemId) {
            R.id.action_delete ->true
            else -> super.onOptionsItemSelected(item)
        }
    }


}