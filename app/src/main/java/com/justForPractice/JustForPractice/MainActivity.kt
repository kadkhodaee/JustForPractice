package com.justForPractice.JustForPractice

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MyActivity() {
    private lateinit var itemViewModel: ItemViewModel
    private var allitem = emptyList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val layoutManger=LinearLayoutManager(this)
        layoutManger.orientation= LinearLayoutManager.VERTICAL

        itemViewModel= ViewModelProvider(this).get(ItemViewModel::class.java)

        recyclerView.layoutManager=layoutManger
        val adapter=MyAdapter(this)
        recyclerView.adapter=adapter

        itemViewModel.allitems.observe(this, Observer { items ->
            items?.let {
                adapter.setItems(it)
                allitem=it
            }
        })

        val myTHP= object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val sourceposition=viewHolder.adapterPosition
                    itemViewModel.delete(allitem[sourceposition])
                }

            }

        val itemTouchHelper=ItemTouchHelper(myTHP)
        itemTouchHelper.attachToRecyclerView(recyclerView)



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
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
            R.id.action_theme->{
                changeTheme()
            }
            else ->{}
        }

        return when (item.itemId) {
            R.id.action_delete ->true
            else -> super.onOptionsItemSelected(item)
        }
    }

}