package repository

import model.Questions
import model.Score


interface QcmRepository {
    fun getAllQcm(dom : String) : List<Questions>
    fun getAllDomaine() : List<String>
    fun addScore(score : Score)
    fun getScore() : List<Score>

}



