package com.example.s8107356assessment2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.s8107356assessment2.repository.AuthRepository
import com.example.s8107356assessment2.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.kotlin.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    // This rule makes LiveData run instantly
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var authRepository: AuthRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        // Use test dispatcher for coroutines
        Dispatchers.setMain(testDispatcher)

        // Create fake repository
        authRepository = mock()

        // Create ViewModel using the fake repository
        viewModel = LoginViewModel(authRepository)
    }

    @After
    fun tearDown() {
        // Reset dispatcher
        Dispatchers.resetMain()
    }

    @Test
    fun loginSuccess_updatesLoginResult() {
        runTest {
            val location = "sydney"
            val username = "Mio"
            val password = "9107356"
            val fakeKeypass = "music"

            // Return success result
            `when`(authRepository.login(location, username, password))
                .thenReturn(Result.success(fakeKeypass))

            // Watch loginResult
            val observer = mock<Observer<Result<String>>>()
            viewModel.loginResult.observeForever(observer)

            // Call login
            viewModel.login(location, username, password)

            // Check if keypass is correct
            verify(observer).onChanged(Result.success(fakeKeypass))

            // Stop watching
            viewModel.loginResult.removeObserver(observer)
        }
    }

    @Test
    fun loginFail404_updatesUserNotFoundMessage() {
        runTest {
            val location = "sydney"
            val username = "Mio"
            val password = "wrongpass"
            val exception = Exception("404 Not Found")

            // Return error result
            `when`(authRepository.login(location, username, password))
                .thenReturn(Result.failure(exception))

            val observer = mock<Observer<String>>()
            viewModel.errorMessage.observeForever(observer)

            viewModel.login(location, username, password)

            // Check message for 404 error
            verify(observer).onChanged("User not found. Please check your username or password.")

            viewModel.errorMessage.removeObserver(observer)
        }
    }

    @Test
    fun loginFail401_updatesUnauthorizedMessage() {
        runTest {
            val location = "sydney"
            val username = "Mio"
            val password = "wrongpass"
            val exception = Exception("401 Unauthorized")

            // Return error result
            `when`(authRepository.login(location, username, password))
                .thenReturn(Result.failure(exception))

            val observer = mock<Observer<String>>()
            viewModel.errorMessage.observeForever(observer)

            viewModel.login(location, username, password)

            // Check message for 401 error
            verify(observer).onChanged("Unauthorized. Please try again.")

            viewModel.errorMessage.removeObserver(observer)
        }
    }

    @Test
    fun loginOtherError_updatesGenericMessage() {
        runTest {
            val location = "sydney"
            val username = "Mio"
            val password = "wrongpass"
            val exception = Exception("Timeout")

            // Return error result
            `when`(authRepository.login(location, username, password))
                .thenReturn(Result.failure(exception))

            val observer = mock<Observer<String>>()
            viewModel.errorMessage.observeForever(observer)

            viewModel.login(location, username, password)

            // Check message for unknown error
            verify(observer).onChanged("Login failed. Please try again.")

            viewModel.errorMessage.removeObserver(observer)
        }
    }
}
