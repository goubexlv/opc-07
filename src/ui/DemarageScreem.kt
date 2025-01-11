package ui

import service.QcmService
import ui.composants.domaineUi

val blue = "\u001B[34m"
val red = "\u001B[31m"
val green = "\u001B[32m"
val reset = "\u001B[0m"
var currentName = ""

val qcmService = QcmService()
val listDomaine =  qcmService.getAllDomaine()


fun BienvenuComposent(){

    val welc = """
            ${blue}
                          _         
                         (_)        
               __ _ _   _ _ ________
              / _` | | | | |_  /_  /
             | (_| | |_| | |/ / / / 
              \__, |\__,_|_/___/___|
                 | |                
                 |_|                
                                                                      
${reset}
        """

    println(welc)
}

fun startApp() {

    var continuer = true

    while (continuer) {
        println(
                """
                ##################################################
                #               ${blue}Menus${reset}                            #
                #                                                #
                #    1️. Faire un quizz                          #
                #    2️. Voir les scores 💬                      #
                #    3️. Sortir du programme 🚫                  #
                #                                                #
                ##################################################
                """
        )

        print("\t \t \t Choisissez une option : ")
        val choix = readLine()?.toIntOrNull()
        when (choix) {
            1 -> {
                quizz()
                continuer()
            }
            2 -> {
                afficheResult()
            }
            3 -> {
                println(
                    "\t \t \t \n" +
                            "                                    _             \n" +
                            "     /\\                            (_)            \n" +
                            "    /  \\  _   _ _ __ _____   _____  _ _ __        \n" +
                            "   / /\\ \\| | | | '__/ _ \\ \\ / / _ \\| | '__|       \n" +
                            "  / ____ \\ |_| | | |  __/\\ V / (_) | | |_ _ _ _ _ \n" +
                            " /_/    \\_\\__,_|_|  \\___| \\_/ \\___/|_|_(_|_|_|_|_)\n" +
                            "                                                  \n" +
                            "                                                  \n"
                )
                continuer = false
            }

            else -> println("\t \t \t ${red}Choix invalide. Veuillez entrer un nombre valide.${reset}")
        }


    }
}

fun domaine(){

    println()
    println()
    var i = 1
    print("""
           ##################################################
           #               ${blue}Domaines${reset}                         #
           #                                                # 
                """)
    for(dom in listDomaine){
        println("\t \t \t ${i.toString()}\uFE0F. ${dom} ")
        i++
    }
    print("""
           ##################################################
            """)
    println()
    println()
}


fun quizz(){
    println("")
    println("")
    println(
        """
            ##################################################
            #                                                #
            #    Vielle vous enregistrez 💾                  #
            #                                                #
            ##################################################
        """
    )


    print("\t \t \t Entrez votre nom : ")
    currentName = readLine().toString()
    println("")
    domaine()

    print("\t \t \t Veillez choisir le domaine des QCM : ")
    val choix = readLine()?.toIntOrNull()
    if (choix != null) {
        if (choix in 1..2) {

            domaineUi(listDomaine[choix - 1])
        } else {
            println("\t \t \t ${red}Choix invalide.${reset}")

        }
    } else {
        println("\t \t \t ${red}Choix ne dois pas etre null.${reset}")
    }

}

fun afficheResult(){
    val listeScore = qcmService.getScore()
    println("")
    println("")
    if (listeScore.isEmpty()) {
        println("\t \t \t Aucun score disponible. \uD83D\uDE22")

        println("")
        println("\t \t \t ---------------------------------------${green}by 45$reset---------------------------------------")
        println("")
        return
    }

    println("\t \t \t${green}--- Résultat final \uD83D\uDE08 ---$reset")

    for(scores in listeScore){
        println("\t \t \t${blue}Nom$reset: ${scores.nom}")
        println("\t \t \t${blue}Domaine$reset: ${scores.domaine}")
        if(scores.score.toInt() in 0..9){
            println("\t \t \t${blue}Score$reset: ${red}${scores.score}$reset / 20")
        }else{
            println("\t \t \t${blue}Score$reset: ${red}${scores.score}$reset / 20")
        }
        println("")
    }
    println("")
    println("\t \t \t ---------------------------------------${green}by 45$reset---------------------------------------")
    println("")
}

fun continuer(){
    println("")
    print("\t \t \tVoulez vous recommencez 1-Oui, 2-Non : ")
    val choix = readLine()?.toIntOrNull()
    when(choix){
        1 -> {
            quizz()
        }
        2 -> {
            startApp()
        }
        else -> {
            println("\t \t \t ${red}Choix invalide. Veuillez entrer un nombre valide.${reset}")
        }

    }
}

