package com.justForPractice.JustForPractice

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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
            val itemT=Item(Title =  "Title",Description =  "Description",Data =  SimpleDateFormat("yyyy/MM/dd HH:mm").format(
                Date()
            ).toString())
            itemViewModel.insert(itemT)
            itemViewModel.insert(itemT)
            itemViewModel.insert(itemT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

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