package com.triforce.malacprodavac.presentation.chat.components

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

        }
    }

    @Synchronized
    fun getSocket(): Socket{
        return mSocket;
    }

}