package co.joebirch.adoptr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogViewModel : ViewModel() {

    private val _liveData = MutableLiveData<DogListState>()
    val liveData = _liveData as LiveData<DogListState>

    init {
        _liveData.value = DogListState()
    }

    fun filterDogsBy(dogAttributes: DogAttributes) {
        val list = _liveData.value?.includeAttributes?.toMutableList()

        if (list!!.contains(dogAttributes)) {
            list.remove(dogAttributes)
        } else {
            list.add(dogAttributes)
        }
        _liveData.value = _liveData.value!!.build {
            includeAttributes = list.toList()
        }
    }

    fun isDogFavorite(id: String) = _liveData.value!!.favorites.contains(id)

    fun toggleFavorite(id: String) {
        val list = _liveData.value?.favorites?.toMutableList()
        if (list!!.contains(id)) {
            list.remove(id)
        } else {
            list.add(id)
        }
        _liveData.value = _liveData.value!!.build {
            favorites = list.toList()
        }
    }

    fun dogs() = _liveData.value!!.dogs
}