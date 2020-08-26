package com.justForPractice.JustForPractice


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.add_edit_item.*
import java.text.SimpleDateFormat
import java.util.*


class AddEditItem : AppCompatActivity() {
    private lateinit var itemViewModel: ItemViewModel
    private var IdMode: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_item)
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        IdMode = intent.getLongExtra("PXtoEditModeID", -1)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)



        if (IdMode == (-1).toLong()) {
            title = "Add New"
            txvDate.text = SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date()).toString()
        } else {
            title = "Edit Item"
            txvEditTitle.setText(intent.getStringExtra("PXtoEditModeTI"))
            txvEditDescription.setText(intent.getStringExtra("PXtoEditModeDE"))
            txvDate.text =intent.getStringExtra("PXtoEditModeDA")
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save_item -> {
                if (txvEditTitle.text.isEmpty() || txvEditDescription.text.isEmpty()) {
                    Toast.makeText(this, "Title or Description is empty!!", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
                if (IdMode == (-1).toLong()) {
                    val itemT = Item(
                        Title = txvEditTitle.text.toString(),
                        Description = txvEditDescription.text.toString(),
                        Date = Date()
                    )
                    itemViewModel.insert(itemT)
                    finish()
                } else {
                    val itemT = Item(
                        IdMode,
                        Title = txvEditTitle.text.toString(),
                        Description = txvEditDescription.text.toString(),
                        Date = Date()
                    )
                    itemViewModel.update(itemT)
                    finish()
                }

            }
            android.R.id.home -> {
                finish()
            }
            else -> {
                Toast.makeText(this, "not saved!!", Toast.LENGTH_SHORT).show()
            }
        }
        return when (item.itemId) {
            R.id.save_item -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}