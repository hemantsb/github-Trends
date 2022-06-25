package bit.hemant.git.trends.feature_repo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bit.hemant.git.trends.feature_repo.domain.model.Repo


@Composable
fun RepoItem(item: Repo, collapsedState: MutableState<Int>, onItemClick: (index: Int) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onItemClick.invoke(item.id ?: -1) }) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
        ) {
            RepoOwnerImage(
                url = item.repoImage,
                contentDescription = item.name
            )
        }

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = item.owner,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body2
            )

            if (collapsedState.value == item.id) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Yellow),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.language,
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = item.starCount.toString(),
                        style = MaterialTheme.typography.body2
                    )

                    Text(
                        text = item.forkCount.toString(),
                        style = MaterialTheme.typography.body2
                    )
                }
            }

        }

    }

}