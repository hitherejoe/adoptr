/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
