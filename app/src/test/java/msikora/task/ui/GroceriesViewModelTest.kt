@file:OptIn(ExperimentalCoroutinesApi::class)

package msikora.task.ui

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import msikora.task.data.TestGroceriesRepository
import msikora.task.domain.Item
import msikora.task.testutils.MainDispatcherRule
import org.junit.Assert

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GroceriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var testRepo: TestGroceriesRepository
    lateinit var viewModel: GroceriesViewModel

    @Before
    fun setUp() {
        testRepo = TestGroceriesRepository()
        viewModel = GroceriesViewModel(testRepo)
    }

    @Test
    fun testToggling() = runTest {
        val expectedButtonStates = mutableListOf(ButtonState.disabled())
        val expectedItemsList = mutableListOf(emptyList<Item>())
        val buttonStates = mutableListOf<ButtonState>()
        val items = mutableListOf<List<Item>>()
        val collectButtonStatesJob = launch(UnconfinedTestDispatcher()) {
            viewModel.buttonState.toList(buttonStates)
        }
        val collectItemsJob = launch(UnconfinedTestDispatcher()) {
            viewModel.items.toList(items)
        }
        Assert.assertEquals(expectedButtonStates, buttonStates)

        viewModel.toggle()

        testRepo.exposedGroceries.value = listOf(itemA)
        expectedButtonStates += ButtonState.enabled()
        expectedItemsList += listOf(itemA)
        Assert.assertEquals(expectedButtonStates, buttonStates)
        Assert.assertEquals(expectedItemsList, items)

        viewModel.toggle()

        testRepo.exposedGroceries.value = listOf(itemA, itemB)
        expectedButtonStates += ButtonState.disabled()
        Assert.assertEquals(expectedButtonStates, buttonStates)
        Assert.assertEquals(expectedItemsList, items)

        viewModel.toggle()

        expectedButtonStates += ButtonState.enabled()
        expectedItemsList += listOf(itemA, itemB)
        Assert.assertEquals(expectedButtonStates, buttonStates)
        Assert.assertEquals(expectedItemsList, items)

        collectButtonStatesJob.cancel()
        collectItemsJob.cancel()
    }

    companion object {

        val itemA = Item("#000000", "Item A", "some weight")
        val itemB = Item("#000000", "Item B", "some weight")
    }
}
