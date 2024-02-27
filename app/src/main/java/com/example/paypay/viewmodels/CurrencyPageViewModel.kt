package com.example.paypay.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypay.IDataManager
import com.example.paypay.data.IDataRefresher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyPageViewModel @Inject constructor(
    private val dataManager: IDataManager,
    private val dataRefresher: IDataRefresher
) : ViewModel() {

//    val currencyList : StateFlow<List<CurrencyRate>>
//        get() {
//            repository.getCurrencyList()
//        }

    init {
        viewModelScope.launch {
            dataManager.getData()
        }
    }


}