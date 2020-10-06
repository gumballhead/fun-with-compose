package com.example.cleankt

interface View<Model> {
    fun render(model: Model)
}

interface ResultView<Model> : View<Model> {
    fun render(error: Throwable)

    fun render(result: Result<Model>) {
        when (result) {
            is Success -> render(result.data)
            is Failure -> render(result.error)
        }
    }
}

interface AsyncView<Model> : ResultView<Model> {
    fun renderLoading()

    fun render(state: AsyncState<Model>) {
        when (state) {
            is Loading -> renderLoading()
            is Complete -> render(state.result)
        }
    }
}
