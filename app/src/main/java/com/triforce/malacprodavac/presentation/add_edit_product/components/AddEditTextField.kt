package com.triforce.malacprodavac.presentation.add_edit_product.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.presentation.add_edit_product.AddEditProductEvent
import com.triforce.malacprodavac.presentation.registration.RegistrationFormEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTextField(
    text: String,
    onTextValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text

) {
    TextField(
        value = text,
        onValueChange = { value -> onTextValueChange(value) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}