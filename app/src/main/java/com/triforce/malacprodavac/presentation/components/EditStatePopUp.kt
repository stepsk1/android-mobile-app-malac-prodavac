package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.presentation.profile.ProfileViewModel

@Composable
fun EditStatePopup(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    var state = viewModel.state

    if (isOpen) {
        // Create a local state to hold the edited text
        var editedText by remember { mutableStateOf(state.currentUser?.email.toString()) }

        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnClickOutside = false
            )
        ) {
            // Dialog content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
            ) {

                DataChange( onDismiss = onDismiss, onSave = onSave)

                Spacer(modifier = Modifier.height(16.dp))

                // Save and Cancel buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            onSave(editedText)
                            state = state.copy(email = editedText)
                            onDismiss()
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Sačuvaj")
                    }

                    Button(
                        onClick = onDismiss
                    ) {
                        Text("Odbaci")
                    }
                }
            }
        }
    }
}
