package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.presentation.profile.ProfileViewModel
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun DataChange(
    viewModel: ProfileViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    ) {
    var state = viewModel.state

    var editedEmail by remember { mutableStateOf(state.currentUser?.email.toString()) }
    var editedAdress by remember { mutableStateOf(state.currentUser?.address.toString()) }
    var editedPhone by remember { mutableStateOf(state.currentUser?.phoneNumber.toString()) }
    var editedNameOfCompany by remember { mutableStateOf(state.currentUser?.shop?.businessName?.toString()) }

    Text("Promenite email:",
        modifier = Modifier.height(20.dp),
        color = MP_White,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))

    // Text field for editing the state
    BasicTextField(
        value = editedEmail,
        onValueChange = { editedEmail = it },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSave(editedEmail)
                onDismiss()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(MP_Gray)
            .height(22.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text("Promenite adresu:",
        modifier = Modifier.height(20.dp),
        color = MP_White,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))

    // Text field for editing the state
    BasicTextField(
        value = editedAdress,
        onValueChange = { editedAdress = it },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSave(editedAdress)
                onDismiss()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(MP_Gray)
            .height(22.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text("Promenite kontakt telefon:",
        modifier = Modifier.height(20.dp),
        color = MP_White,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))

    // Text field for editing the state
    BasicTextField(
        value = editedPhone,
        onValueChange = { editedPhone = it },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSave(editedPhone)
                onDismiss()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(MP_Gray)
            .height(22.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    if(editedNameOfCompany != null) {
        Text("Promenite naziv preduzeća:",
            modifier = Modifier.height(20.dp),
            color = MP_White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Text field for editing the state
        BasicTextField(
            value = editedNameOfCompany!!,
            onValueChange = { editedNameOfCompany = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSave(editedPhone)
                    onDismiss()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(MP_Gray)
                .height(22.dp)
        )
    }
}