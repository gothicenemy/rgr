package com.example.rgr

class Group(val students: List<Student>) {
    fun calculateAverageGrade(studentName: String): Double? {
        val student = students.find { it.firstName == studentName || it.lastName == studentName }
        return student?.grades?.average()
    }

    fun calculateGroupAverage(): Double {
        val allGrades = students.flatMap { it.grades }
        return if (allGrades.isNotEmpty()) {
            allGrades.average()
        } else {
            0.0
        }
    }
}
