package com.example.productivtypro.Task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskEntryViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddNewTask(date:String, viewModel: TaskEntryViewModel = viewModel(factory = AppViewModelProvider.Factory), addPad:Boolean = true){
    var startPad = 0.dp
    if (addPad){
        startPad = 5.dp
    }
    val name by viewModel.name
    viewModel.setDateTime(date)
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(modifier = Modifier.padding(start = startPad,end = 10.dp,top= 10.dp, bottom = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column {
            Box {
                OutlinedTextField(value = name,
                    onValueChange = {viewModel.setInputName(it)},
                    label = { Text(stringResource(id = R.string.addnewTask)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    singleLine = true,
                    trailingIcon = {IconButton(onClick = {
                        viewModel.insertData()
                        viewModel.setInputName("")
                    })
                    { Icon(imageVector = Icons.Default.Done ,contentDescription = "Add") }
                    }
                )
            }
        }
    }
}
