package edu.shch.hsr.relicanalyzer.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchBox(
    search: String,
    changeSearch: (String) -> Unit,
    openFilter: () -> Unit,
    modifier: Modifier = Modifier
) {
    val source = remember { MutableInteractionSource() }
    val textFieldColors = OutlinedTextFieldDefaults.colors()
    BasicTextField(
        value = search,
        onValueChange = changeSearch,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 4.em,
            color = MaterialTheme.colorScheme.primary
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        decorationBox = @Composable { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = search,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                isError = false,
                interactionSource = source,
                colors = textFieldColors,
                contentPadding = PaddingValues(start = 8.dp),
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    IconImageButton(
                        image = R.drawable.filter,
                        onClick = openFilter
                    )
                },
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = false,
                        interactionSource = source,
                        colors = textFieldColors,
                        shape = CircleShape
                    )
                }
            )
        }
    )
}