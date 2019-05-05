package serialization

import model.state.NormalState
import model.state.State
import model.state.board.CellContent
import model.state.player.NormalPlayer
import java.io.File
import java.util.regex.Pattern

class JSONState(val newStateString: String, val oldState: NormalState) {
    //state = this.gson.fromJson(read, NormalState::class.java) // TODO1 fare con GSON
    fun deserialize(): State{ // TODO1 migliorare
        var res = oldState
        val words = listOf("EMPTY", "WHITE", "BLACK", "KING")

        val a = newStateString.split("\"")
        val b = a.filter { s -> s == "EMPTY" || s == "WHITE" || s == "BLACK" || s == "KING" }

        val tmp = File("tmpNormalBoardContent.txt")
        var char = ""
        for(i in 0..b.size-2) {
            when (b[i]) {
                "EMPTY" -> char = "E"
                "BLACK" -> char = "B"
                "WHITE" -> char = "W"
                "KING" -> char = "K"
            }
            if(i==0 || (i+1)%9!=0){
                tmp.appendText(char+" ")
            }else{
                tmp.appendText(char+"\n")
            }
        }

        res.board.initializeContent(tmp)
        tmp.delete()
        res.player = if(b[b.size-1] == "WHITE") NormalPlayer.WHITE else NormalPlayer.BLACK

        return res
    }
}

/*
    {"board":[
        ["EMPTY","EMPTY","EMPTY","BLACK","BLACK","BLACK","EMPTY","EMPTY","EMPTY"],
        ["EMPTY","EMPTY","EMPTY","EMPTY","BLACK","EMPTY","EMPTY","EMPTY","EMPTY"],
        ["EMPTY","EMPTY","EMPTY","EMPTY","WHITE","EMPTY","EMPTY","EMPTY","EMPTY"],
        ["BLACK","EMPTY","EMPTY","EMPTY","WHITE","EMPTY","EMPTY","EMPTY","BLACK"],
        ["BLACK","BLACK","WHITE","WHITE","KING","WHITE","WHITE","BLACK","BLACK"],
        ["BLACK","EMPTY","EMPTY","EMPTY","WHITE","EMPTY","EMPTY","EMPTY","BLACK"],
        ["EMPTY","EMPTY","EMPTY","EMPTY","WHITE","EMPTY","EMPTY","EMPTY","EMPTY"],
        ["EMPTY","EMPTY","EMPTY","EMPTY","BLACK","EMPTY","EMPTY","EMPTY","EMPTY"],
        ["EMPTY","EMPTY","EMPTY","BLACK","BLACK","BLACK","EMPTY","EMPTY","EMPTY"]
        ],
    "turn":"WHITE"}
 */