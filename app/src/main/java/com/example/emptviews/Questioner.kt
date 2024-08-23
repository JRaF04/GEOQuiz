package com.example.emptviews

import kotlin.random.Random

class Questioner {

    private var askArray = arrayOf(
        "The Amazon River is the longest river in the world.",
        "Africa is the second largest continent by land area.",
        "Australia is both a country and a continent.",
        "The capital of Canada is Toronto.",
        "The Sahara Desert is the largest desert in the world.",
        "Mount Everest is located in the Himalayas.",
        "Russia is the largest country in the world by land area.",
        "Greenland is the world's largest island.",
        "Japan is an island nation in East Asia.",
        "The Dead Sea is the lowest point on Earth's surface.",
        "The equator passes through the continent of Africa.",
        "Antarctica is the coldest continent on Earth.",
        "The United States has 50 states.",
        "China has the largest population in the world.",
        "The capital of Australia is Sydney.",
        "The Amazon rainforest produces 20% of the world's oxygen.",
        "Europe is the smallest continent by land area.",
        "The Nile River flows into the Mediterranean Sea.",
        "Iceland is known for its hot springs and geysers.",
        "The Andes Mountains are the longest mountain range in the world.",
        "Africa is bordered by both the Atlantic and Indian Oceans.",
        "The capital of Brazil is Rio de Janeiro.",
        "The Great Wall of China is visible from space.",
        "New Zealand is part of the continent of Australia.",
        "The official language of Brazil is Portuguese.",
        "The Mediterranean Sea is surrounded by three continents.",
        "There are 7 continents in the world.",
        "The capital of Egypt is Cairo.",
        "Mount Kilimanjaro is the highest mountain in Africa.",
        "The Great Barrier Reef is located off the coast of Australia.",
        "The Arctic Ocean is the smallest ocean in the world.",
        "The Yangtze River is the longest river in Asia.",
        "The Eiffel Tower is located in Berlin.",
        "Madagascar is an island nation off the coast of Africa.",
        "Lake Baikal is the deepest lake in the world.",
        "The Sahara Desert is larger than the United States.",
        "The capital of Italy is Rome.",
        "The Ganges River is considered sacred in Hinduism.",
        "Canada is the second largest country in the world by land area.",
        "Mount Fuji is the highest mountain in Japan.",
        "The capital of India is New Delhi.",
        "The Amazon rainforest is located in Africa.",
        "The Great Victoria Desert is located in Australia.",
        "The Rocky Mountains are located in North America.",
        "Venice is a city known for its canals in Italy.",
        "The Himalayas are located in South America.",
        "The Baltic Sea is located in Northern Europe.",
        "Africa has the most countries of any continent.",
        "The capital of Japan is Tokyo.",
        "Greenland is a territory of Denmark."
    )

    private val answers = arrayOf(
    false,  // The Amazon River is the second longest, after the Nile.
    true,   // Africa is the second largest continent.
    true,   // Australia is both a country and a continent.
    false,  // The capital of Canada is Ottawa.
    false,  // The largest desert is Antarctica by area, Sahara is the largest hot desert.
    true,   // Mount Everest is located in the Himalayas.
    true,   // Russia is the largest country by land area.
    true,   // Greenland is the world's largest island.
    true,   // Japan is an island nation in East Asia.
    true,   // The Dead Sea is the lowest point on Earth's surface.
    true,   // The equator passes through Africa.
    true,   // Antarctica is the coldest continent.
    true,   // The United States has 50 states.
    true,   // China has the largest population.
    false,  // The capital of Australia is Canberra.
    false,  // The Amazon rainforest produces roughly 6-9% of the world's oxygen.
    false,  // Australia is the smallest continent by land area.
    true,   // The Nile River flows into the Mediterranean Sea.
    true,   // Iceland is known for its hot springs and geysers.
    true,   // The Andes Mountains are the longest mountain range.
    true,   // Africa is bordered by both the Atlantic and Indian Oceans.
    false,  // The capital of Brazil is Brasília.
    false,  // The Great Wall of China is not visible from space with the naked eye.
    false,  // New Zealand is part of Oceania, not Australia.
    true,   // The official language of Brazil is Portuguese.
    true,   // The Mediterranean Sea is surrounded by Europe, Asia, and Africa.
    true,   // There are 7 continents.
    true,   // The capital of Egypt is Cairo.
    true,   // Mount Kilimanjaro is the highest mountain in Africa.
    true,   // The Great Barrier Reef is located off the coast of Australia.
    true,   // The Arctic Ocean is the smallest ocean.
    true,   // The Yangtze River is the longest river in Asia.
    false,  // The Eiffel Tower is located in Paris.
    true,   // Madagascar is an island nation off the coast of Africa.
    true,   // Lake Baikal is the deepest lake.
    false,  // The Sahara Desert is smaller than the United States.
    true,   // The capital of Italy is Rome.
    true,   // The Ganges River is sacred in Hinduism.
    true,   // Canada is the second largest country by land area.
    true,   // Mount Fuji is the highest mountain in Japan.
    true,   // The capital of India is New Delhi.
    false,  // The Amazon rainforest is located in South America.
    true,   // The Great Victoria Desert is in Australia.
    true,   // The Rocky Mountains are in North America.
    true,   // Venice is known for its canals in Italy.
    false,  // The Himalayas are in Asia.
    true,   // The Baltic Sea is in Northern Europe.
    true,   // Africa has the most countries.
    true,   // The capital of Japan is Tokyo.
    true    // Greenland is a territory of Denmark.
    )

    private val questions = Array(askArray.size){Question("", false)}

    private fun generateQuestions() {
        for(i in askArray.indices) {
            questions[i] = Question(askArray[i], answers[i])
        }
    }

    init{generateQuestions()}

    private fun shuffleOrder() { //O(n)
        var holder: Question
        var chosen: Int
        for(i in questions.indices) {
            chosen = Random.nextInt(0, questions.size)
            holder = questions[i]
            questions[i] = questions[chosen]
            questions[chosen] = holder
        }
    }

    private var questionCounter = -1

    init {
    shuffleOrder()
    }


    fun getQuestion() : Question {
        return if (questionCounter == questions.size - 1)  {
            questions[questionCounter]
        } else {
            questions[++questionCounter]
        }
    }

    fun getPrevQuestion() : Question {
        if (questionCounter > 0) {
            return questions[--questionCounter]
        }
        else {
            questionCounter = 0
            return questions[questionCounter]
        }
    }


    fun getQuestionsCount() : Int {
        return questions.size - questionCounter
    }
}