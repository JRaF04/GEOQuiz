package com.example.emptviews

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*


data class QuizUiState (
    val questionString: String = ""
)



class MainActivityController () : ViewModel() {

    private val questions = Questioner()

    private val _uiState = MutableStateFlow(QuizUiState())

    val uiState : StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var question : Question = questions.getQuestion()
    fun getQuestion() : String{
        return question.getQuestion()
    }

    fun check(input : Boolean) : String{
        return correctIncorrect(question.check(input))
    }

    fun skip() {
        question = questions.getQuestion()
        updateQuestion()
    }

    fun prev() {
        question = questions.getPrevQuestion()
        updateQuestion()
    }

    private fun correctIncorrect(correct : Boolean) : String{
        return if (correct) {
            "Correct"
        } else {
            "Incorrect"
        }
    }

    fun getAnswer() : Boolean{
        return question.check(true)
    }

    fun setCheat() {
        question.setCheated()
    }

    fun getCheat() : Boolean {
        return question.cheated
    }

    fun updateQuestion() {
        _uiState.update { currentState -> currentState.copy(questionString = question.getQuestion()) }
    }


}