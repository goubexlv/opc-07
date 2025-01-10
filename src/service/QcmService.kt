package service

import model.Questions
import model.Score
import repository.QcmRepository
import repository.QcmRepositoryImpl

class QcmService {

    val qcmRepository : QcmRepository = QcmRepositoryImpl()

    fun getAllDomaine() : List<String> {
        return qcmRepository.getAllDomaine()
    }

    fun getAllQuestion(dom : String) : List<Questions> {
        return qcmRepository.getAllQcm(dom)
    }

    fun addScrore(nom: String,dom : String, score : String){
        qcmRepository.addScore(Score(nom,dom,score))

    }

    fun getScore() : List<Score> {
        return qcmRepository.getScore()
    }


}