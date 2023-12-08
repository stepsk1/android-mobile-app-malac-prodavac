package com.triforce.malacprodavac.presentation.profile.profilePrivate.userScreens

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.profile.profilePrivate.ProfilePrivateViewModel
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink

@Composable
fun CustomerPrivateScreen(
    navController: NavController,
    viewModel: ProfilePrivateViewModel = hiltViewModel()
) {
    Icon(
        Icons.Outlined.Edit,
        contentDescription = "Edit",
        tint = MP_Green,
        modifier = Modifier.size(100.dp)
    )
}