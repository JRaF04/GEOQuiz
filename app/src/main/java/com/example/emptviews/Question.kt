package com.example.emptviews

class Question(private var asked: String, private var correct : Boolean) {
    var cheated : Boolean = false
    fun check(answer : Boolean) : Boolean{
        return answer == correct
    }
    fun getQuestion() : String {
        return asked
    }

    fun setCheated() {
        cheated = true
    }

}