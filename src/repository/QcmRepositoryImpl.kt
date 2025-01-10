package repository

import model.Questions
import model.Score
import java.io.File

val directoryPath = "./data"
val filenameqcm = "$directoryPath/qcm.txt"
val filenamescore = "$directoryPath/score.txt"

class QcmRepositoryImpl : QcmRepository {
    override fun getAllQcm(dom : String): List<Questions> {
        val file = File(filenameqcm)

        if (!file.exists()) {
            return emptyList()
        }

        return file.readLines().mapNotNull { line ->
            val parts = line.split(",")
            if (parts.isNotEmpty() && parts[0] == dom) {
                if (parts.size == 7) {
                    try {
                        val domaine = parts[0]
                        val questions = parts[1]
                        val opt1 = parts[2]
                        val opt2 = parts[3]
                        val opt3 = parts[4]
                        val opt4 = parts[5]
                        val correct = parts[6]
                        Questions(questions, opt1, opt2, opt3, opt4,correct)
                    } catch (e: Exception) {
                        null
                    }
                } else {
                    null
                }
            }else{
                null
            }
        }

    }

    override fun getAllDomaine(): List<String> {
        val file = File(filenameqcm)

        if (!file.exists()) {
            return emptyList()
        }

        var listDomaine : MutableList<String> = emptyList<String>().toMutableList()

        val files = file.readLines()

        for(line in files){
            val parts = line.split(",")
            val dom = parts[0]
            if(dom !in listDomaine){
                listDomaine.add(dom)
            }
        }

        return listDomaine
    }

    override fun addScore(score: Score) {
        val file = File(filenamescore)

        if (!file.exists()) {
            file.createNewFile()
        }

        val newScore = "${score.nom},${score.domaine},${score.score}"
        file.appendText("$newScore\n")

    }

    override fun getScore(): List<Score> {
        val file = File(filenamescore)

        if (!file.exists()) {
            return emptyList()
        }

        return file.readLines().mapNotNull { line ->
            val parts = line.split(",")
                if (parts.size == 3) {
                    try {
                        val nom = parts[0]
                        val dom = parts[1]
                        val score = parts[2]
                        Score(nom, dom, score)
                    } catch (e: Exception) {
                        null
                    }
                } else {
                    null
                }

        }

    }

}