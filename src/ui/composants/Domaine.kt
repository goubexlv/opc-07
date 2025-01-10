package ui.composants

import ui.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun domaineUi(domChoix : String){
    val listQuestion = qcmService.getAllQuestion(domChoix)
    println("\t \t \tVeuillez repondre au question du domaine ${blue}${domChoix}$reset ")
    println("")
    val selectedQuestions = listQuestion.shuffled().take(10)
    val executor = Executors.newSingleThreadScheduledExecutor()
    var score = 0
    var i = 1

    for (question in selectedQuestions) {
        println("\t \t \tQuestion $i : ${question.question}")
        println("\t \t \ta) ${question.opt1}")
        println("\t \t \tb) ${question.opt2}")
        println("\t \t \tc) ${question.opt3}")
        println("\t \t \td) ${question.opt4}")
        print("\t \t \tVotre choix : ")

        val task = executor.submit<String?> {
            readLine()
        }

        val response = try {
            task.get(10, TimeUnit.SECONDS)
        } catch (e: Exception) {
            null
        }

        if (response != null) {
            when (response){
                "a" -> {
                    if (question.opt1 == question.correct){
                        score +=2
                    }
                }
                "b" -> {
                    if (question.opt2 == question.correct){
                        score +=2
                    }
                }
                "c" -> {
                    if (question.opt3 == question.correct){
                        score +=2
                    }
                }
                "d" -> {
                    if (question.opt4 == question.correct){
                        score +=2
                    }
                }
                else -> {
                    println("\n\t \t \tD'accord bravo pour le choix .")
                    println(" ")
                }
            }

        } else {
            println("\n\t \t \tTemps écoulé !")
            println("")
        }

        i++

    }

    executor.shutdown()
    println("")

    println("\t \t \t--- ${blue}Résultat final$reset ---")
    println("\t \t \t${blue}Nom$reset: $currentName")
    println("\t \t \t${blue}Domaine$reset: $domChoix")
    if(score in 0..9){
        println("\t \t \tVous avez obtenu un score de ${red}$score$reset sur 20")
    }else{
        println("\t \t \tVous avez obtenu un score de ${green}$score$reset sur 20")
    }

    qcmService.addScrore(currentName,domChoix,score.toString())

    println("")
    println("\t \t \t ---------------------------------------by 45---------------------------------------")
    println("")


}

