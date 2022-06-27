package bit.hemant.git.trends.feature_repo.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import bit.hemant.git.trends.R
import bit.hemant.git.trends.core.util.TestTag
import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.ui.theme.Typography
import bit.hemant.git.trends.ui.theme.lightBody


@Composable
fun RepoItem(item: Repo, collapsedState: MutableState<Int>, onItemClick: (index: Int) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onItemClick.invoke(item.id ?: -1) }
        .testTag(TestTag.REPO_ITEM)) {

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
                style = Typography.bodySmall,
                color = lightBody
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )

            if (collapsedState.value == item.id) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Circle,
                            contentDescription = "Language",
                            Modifier
                                .size(16.dp)
                        )
                        Text(
                            text = item.language,
                            Modifier.padding(start = 8.dp),
                            style = Typography.bodySmall,
                            color = lightBody
                        )
                    }


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.star_yellow), "star")
                        Text(
                            text = item.starCount.toString(),
                            Modifier.padding(start = 8.dp),
                            style = Typography.bodySmall,
                            color = lightBody
                        )
                    }


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.fork_black), "Fork")
                        Text(
                            text = item.forkCount.toString(),
                            Modifier.padding(start = 8.dp),
                            style = Typography.bodySmall,
                            color = lightBody
                        )
                    }

                }
            }

        }

    }

}