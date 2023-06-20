package com.example.cryptocompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocompose.model.CryptoListItem
import com.example.cryptocompose.repository.CryptoRepository
import com.example.cryptocompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val repository: CryptoRepository) :
    ViewModel() {
    // compose da live data ya gerek yok zaten mutable stateler var

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCryptoList= listOf<CryptoListItem>()
    private var isSearchStarting=true

    //arama algoritmamız apiden çekitiğimiz liste içinde ararma yapacak
    fun searchCryptoList(query:String){
        val listToSearch=if (isSearchStarting) cryptoList.value else initialCryptoList

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                cryptoList.value=initialCryptoList
                isSearchStarting=true
                return@launch
            }

            val result=listToSearch.filter { it.currency.contains(query.trim(), ignoreCase = true)}

            if (isSearchStarting){
                initialCryptoList=cryptoList.value
                isSearchStarting=false
            }
            cryptoList.value=result

        }
    }


    //bu sınıftan bir nesne oluşturulduğunda çağıralacak ilk blok
    init {
        loadCryptos()
    }

    fun loadCryptos() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCryptoList()
            when (result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, item ->
                        CryptoListItem(
                            item.currency,
                            item.price
                        )
                    }

                    cryptoList.value += cryptoItems
                    isLoading.value=false
                    error.value=""
                }

                is Resource.Error -> {
                    error.value=result.message!!
                    isLoading.value=false
                }

                else -> {}
            }
        }
    }

}