package nz.co.warehouseandroidtest.main.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.warehouseandroidtest.common.Event
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import nz.co.warehouseandroidtest.main.domain.MainUseCase
import javax.inject.Inject
import nz.co.warehouseandroidtest.common.Result


class MainViewModel @Inject constructor(
    private val mMainUseCase: MainUseCase
) : ViewModel() {

    val userResult = MediatorLiveData<Event<Result<UserResponse>>>()

    fun getUserId() {
        viewModelScope.launch {
            userResult.addSource(mMainUseCase.getUserId()) {
                userResult.value = Event(it)
            }
        }
    }



}