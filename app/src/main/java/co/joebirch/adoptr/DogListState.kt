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