package client

import ai.action.Action
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.state.NormalState
import model.state.State
import model.state.board.ArrayMatrix
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer
import model.state.player.Player
import serialization.JSONAction
import serialization.JSONName
import serialization.JSONState
import utils.StreamUtils
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import java.util.*
import java.util.regex.Pattern

data class TablutClient(val player: NormalPlayer, val name: String, var state: NormalState) {
    private var port : Int
    private var socket : Socket
    private var dos : DataOutputStream
    private var dis : DataInputStream

    init {
        port = 0

        when (player) {
            NormalPlayer.WHITE -> {
                // turn = white
                port = 5800
            }
            NormalPlayer.BLACK -> {
                // turn = white
                port = 5801
            }
            //TODO1 else
        }

        socket = Socket("localhost", port)
        //socket = Socket("192.168.43.172", port)
        dos = DataOutputStream(socket.getOutputStream())
        dis = DataInputStream(socket.getInputStream())
    }

    fun write(action: Action){
        StreamUtils.writeString(dos, JSONAction(action).serialize())
    }

    fun read(){
        val read = StreamUtils.readString(dis)
        this.state = JSONState(read, state as NormalState).deserialize() as NormalState
    }

    fun declareName(){
        StreamUtils.writeString(dos, JSONName(name).serialize())
    }
}