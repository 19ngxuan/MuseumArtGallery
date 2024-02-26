package com.example.improvedmuseumartgallery.presentation.screens.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
@Composable
fun MyCheckbox(
    isChecked: Boolean,
    onCheckClick: () -> Unit
) {


    var checkState by remember {
        mutableStateOf(isChecked)
    }

    IconButton(onClick = {
        onCheckClick.invoke()
        checkState = !checkState
    }) {
        Icon(
            imageVector = if (checkState) Icons.Filled.CheckBox else Icons.Filled.CheckBoxOutlineBlank,
            contentDescription = if (checkState) "Remove Check" else "Add Check"
        )
    }





}

