package com.example.s8107356assessment2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.s8107356assessment2.model.DashboardEntity
import com.example.s8107356assessment2.repository.DashboardRepository
import com.example.s8107356assessment2.viewmodel.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.kotlin.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    // Makes LiveData work instantly for testing
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var repository: DashboardRepository
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchDashboardData_success_updatesEntities() {
        runTest {
            val keypass = "testKey"
            val fakeList = listOf(
                DashboardEntity("Artist", "Album", 2022, "Pop", 10, "Nice album", "Best Track")
            )

            // Return success
            `when`(repository.getDashboardData(keypass))
                .thenReturn(Result.success(fakeList))

            val observer = mock<Observer<List<DashboardEntity>>>()
            viewModel.entities.observeForever(observer)

            // Call function
            viewModel.fetchDashboardData(keypass)

            // Check LiveData is updated
            verify(observer).onChanged(fakeList)

            viewModel.entities.removeObserver(observer)
        }
    }

    @Test
    fun fetchDashboardData_failure_updatesError() {
        runTest {
            val keypass = "testKey"
            val exception = Exception("Network error")

            // Return failure
            `when`(repository.getDashboardData(keypass))
                .thenReturn(Result.failure(exception))

            val observer = mock<Observer<Throwable?>>()
            viewModel.error.observeForever(observer)

            // Call function
            viewModel.fetchDashboardData(keypass)

            // Check error LiveData is updated
            verify(observer).onChanged(exception)

            viewModel.error.removeObserver(observer)
        }
    }
}
