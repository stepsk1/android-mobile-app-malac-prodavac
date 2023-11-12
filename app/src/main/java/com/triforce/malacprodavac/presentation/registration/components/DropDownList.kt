package com.triforce.malacprodavac.presentation.registration.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.triforce.malacprodavac.presentation.registration.RegistrationFormEvent
import com.triforce.malacprodavac.presentation.registration.RegistrationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(viewModel: RegistrationViewModel) : String {
    val rolesList = listOf(
        "KUPAC",
        "DOSTAVLJAČ",
        "PRODAVAC"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(rolesList[0]) }

    // menu box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
            readOnly = true,
            value = selectedRole,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.RoleChanged(it))
            },
            label = { Text("Uloga") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            rolesList.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedRole = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
    return selectedRole
}