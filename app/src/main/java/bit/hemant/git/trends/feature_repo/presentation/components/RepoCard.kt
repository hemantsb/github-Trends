package bit.hemant.git.trends.feature_repo.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bit.hemant.git.trends.feature_repo.domain.model.Repo


@Composable
fun RepoItem(item: Repo) {

    Row(modifier = Modifier.padding(16.dp)) {

        RepoOwnerImage(
            url = item.repoImage,
            contentDescription = item.name
        )

        Column() {
            Text(
                text = item.owner,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h3
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                style = MaterialTheme.typography.h5
            )
        }

    }

}