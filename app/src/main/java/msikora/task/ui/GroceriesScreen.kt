package msikora.task.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import msikora.task.domain.Item
import msikora.task.utils.Colors

@Composable
fun GroceriesScreen(viewModel: GroceriesViewModel = hiltViewModel()) {
    Box(
        modifier =
        Modifier.fillMaxSize()
    ) {
        ItemsList(viewModel)
        ToggleButton(viewModel)
    }
}

@Composable
fun ItemsList(viewModel: GroceriesViewModel) {
    val items by viewModel.items.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items) { groceryItem ->
            GroceryItemCell(groceryItem)
        }
    }
}

@Composable
fun GroceryItemCell(item: Item) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(item.composeColor)
                .border(1.dp, Colors.circleBorder, CircleShape)
                .size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Colors.textsBorder, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
        ) {
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.nameBackground)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Divider(color = Colors.textsBorder)
            Text(
                text = item.weight,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun BoxScope.ToggleButton(viewModel: GroceriesViewModel) {
    val buttonState by viewModel.buttonState.collectAsState()

    Button(
        onClick = viewModel::toggle,
        modifier = Modifier.align(Alignment.BottomCenter),
    ) {
        Text(
            text = if (buttonState.enabled) {
                "STOP"
            } else {
                "START"
            }
        )
    }
}
