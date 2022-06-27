package bit.hemant.git.trends.feature_repo.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import bit.hemant.git.trends.MainActivity
import bit.hemant.git.trends.core.util.TestTag
import bit.hemant.git.trends.di.ApplicationModule
import bit.hemant.git.trends.ui.theme.OctaTrendTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(ApplicationModule::class)
class GitReposScreenTest {


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            OctaTrendTheme() {
                GitReposScreen()
            }
        }
    }

    @Test
    fun threeDotItemClickableTest() {
        composeRule.onNodeWithContentDescription("three Dot Menu").assertExists()
        composeRule.onNodeWithText("Sort by stars").assertDoesNotExist()
        composeRule.onNodeWithText("Sort by title").assertDoesNotExist()
        composeRule.onNodeWithContentDescription("three Dot Menu").performClick()
        composeRule.onNodeWithText("Sort by stars").assertExists()
        composeRule.onNodeWithText("Sort by title").assertExists()

    }

    @Test
    fun expandedRepoTest() {
        Thread.sleep(2000)
        composeRule.onAllNodesWithContentDescription("star")[0].assertDoesNotExist()
        composeRule.onAllNodesWithTag(TestTag.REPO_ITEM)[0].performClick()
        composeRule.onAllNodesWithContentDescription("star")[0].assertExists()
    }

}