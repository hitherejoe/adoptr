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

class DogListState(
    val dogs: List<Dog> = DogFactory.dogs(),
    val mode: DogListMode = DogListMode.LIST,
    val showingModeMenu: Boolean = false,
    val selectedDog: String? = null,
    val includeAttributes: List<DogAttributes> = emptyList(),
    val favorites: List<String> = emptyList()
) {
    fun build(block: Builder.() -> Unit) = Builder(this).apply(block).build()

    class Builder(state: DogListState) {
        var mode = state.mode
        var showingModeMenu = state.showingModeMenu
        var selectedDog = state.selectedDog
        var includeAttributes = state.includeAttributes
        var favorites = state.favorites

        fun build(): DogListState {
            val filtersDogs =
                if (includeAttributes.isEmpty()) {
                    DogFactory.dogs()
                } else {
                    DogFactory.dogs().filterNot { dog ->
                        // any of includeAttributes are not in dog attributes
                        includeAttributes.any {
                            !dog.attributes.contains(it)
                        }
                    }
                }
            return DogListState(
                filtersDogs,
                mode,
                showingModeMenu,
                selectedDog,
                includeAttributes,
                favorites
            )
        }
    }
}
