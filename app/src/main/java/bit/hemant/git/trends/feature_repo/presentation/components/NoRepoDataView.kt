package bit.hemant.git.trends.feature_repo.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import bit.hemant.git.trends.R
import bit.hemant.git.trends.ui.theme.mintGreen


const val Button_Height = 48

@Composable
fun NoRepoDataView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Center),
            contentAlignment = Center,
        ) {
            Column {

                Box(
                    modifier = Modifier
                        .padding(start = 32.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.nointernet_connection),
                        contentDescription = "UFO"
                    )
                }

                Text(
                    text = "Something went wrong..",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = "An alien is probably blocking your signal",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

        }


        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(Button_Height.dp)
                .align(BottomCenter),
            onClick = { },
            border = BorderStroke(1.dp, mintGreen),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = mintGreen)
        ) {
            Text(text = "Retry", style = MaterialTheme.typography.button)
        }


    }

}