package com.example.paypay.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypay.db.CurrencyRate
import com.example.paypay.DataManager
import com.example.paypay.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyPageViewModel @Inject constructor(
    val repository: RemoteRepository
) : ViewModel() {

//    val currencyList : StateFlow<List<CurrencyRate>>
//        get() {
//            repository.getCurrencyList()
//        }

    init {
        viewModelScope.launch {
            repository.getCurrencyList()
        }
    }


}