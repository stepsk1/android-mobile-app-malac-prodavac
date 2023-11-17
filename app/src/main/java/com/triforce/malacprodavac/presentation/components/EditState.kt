package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.presentation.profile.ProfileViewModel

@Composable
fun EditState(viewModel: ProfileViewModel) {

    var state = viewModel.state

    var isPopupOpen by remember { mutableStateOf(false) }
    var stateToEdit by remember { mutableStateOf(state.currentUser?.email.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Button to open the edit state popup
        Button(onClick = { isPopupOpen = true }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Promenite podatke")
        }

        // EditStatePopup composable
        EditStatePopup(
            isOpen = isPopupOpen,
            onDismiss = { isPopupOpen = false },
            onSave = { editedState ->
                stateToEdit = editedState
            }
        )
    }
}