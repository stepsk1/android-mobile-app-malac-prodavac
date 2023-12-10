package com.triforce.malacprodavac.presentation.chat.components

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(){
        try {
            mSocket = IO.socket("")
        }catch (e: URISyntaxException){
            Log.d("SocketFilip", "Errooooooor Socketttt")
        }
    }

    @Synchronized
    fun getSocket(): Socket{
        return mSocket;
    }

}