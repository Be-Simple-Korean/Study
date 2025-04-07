package com.example.studytesttodo.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("확인") },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onConfirm) { Text("확인") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("취소") }
        }
    )
}