package com.example.bai3tuan7

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bai3tuan7.model.Student.Student

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var studentAdapter: StudentAdapter

    private val students = listOf(
        Student("Ly Quang Vu", "20215517"),
        Student("Le Thanh Thuong", "20215555"),
        Student("Nguyen Duy Tan", "20211122"),
        Student("Nguyen Vu Hung", "20215321"),
        Student("Nguyen Van Truong", "20215565"),
        Student("Nguyen Phuc Hiep", "20215944"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        studentAdapter = StudentAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 2) {
                    val filteredList = students.filter {
                        it.name.contains(newText, ignoreCase = true) ||
                                it.mssv.contains(newText, ignoreCase = true)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(students)
                }
                return true
            }
        })
    }
}
