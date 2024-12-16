package com.example.rgr

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val students = mutableListOf<Student>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextGrades = findViewById<EditText>(R.id.editTextGrades)
        val buttonAddStudent = findViewById<Button>(R.id.buttonAddStudent)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)


        buttonCalculate.setOnClickListener {
            val group = Group(students)
            val groupAverage = group.calculateGroupAverage()
            textViewResult.text = "Середній бал групи: $groupAverage"
        }

        recyclerView = findViewById(R.id.recyclerViewStudents)
        adapter = StudentAdapter(students)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonAddStudent.setOnClickListener {
            val firstName = editTextName.text.toString()
            val lastName = editTextLastName.text.toString()
            val grades = editTextGrades.text.toString().split(",").map { it.toDouble() }
            val student = Student(firstName, lastName, grades)
            students.add(student)

            editTextName.text.clear()
            editTextLastName.text.clear()
            editTextGrades.text.clear()

            // Сортування та оновлення RecyclerView після додавання нового студента
            students.sortBy { it.lastName }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        adapter.notifyDataSetChanged()
    }
}
