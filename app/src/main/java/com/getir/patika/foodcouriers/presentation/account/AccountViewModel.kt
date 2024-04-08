package com.getir.patika.foodcouriers.presentation.account


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getir.patika.foodcouriers.common.domain.ViewState
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Login
import com.getir.patika.foodcouriers.domain.usecase.user.LoginUseCase
import com.getir.patika.foodcouriers.domain.usecase.user.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _uiStateLogin: MutableStateFlow<ViewState<BaseResponse<String>>> =
        MutableStateFlow(ViewState.Loading)
    val uiStateLogin = _uiStateLogin.asStateFlow()

    fun setLogin(login: Login) {
        loginUseCase.execute(login).map {
            when(val responseData: BaseResponse<String> = it) {
                is BaseResponse.Success -> {
                    ViewState.Success(responseData)
                }
                is BaseResponse.Error -> {
                    ViewState.Error(responseData.message)
                }
            }
        }.onEach { data ->
            _uiStateLogin.emit(data)
        }.catch {
            _uiStateLogin.emit(ViewState.Error(it.message.toString()))
        }.launchIn(viewModelScope)
    }


}