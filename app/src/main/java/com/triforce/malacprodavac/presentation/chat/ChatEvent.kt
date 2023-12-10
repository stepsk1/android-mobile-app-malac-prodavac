package com.triforce.malacprodavac.presentation.chat

sealed class ChatEvent {
    object Refresh : ChatEvent()
    data class SendMessage(val msg: String) : ChatEvent()
    object RecieveMessage : ChatEvent()
}