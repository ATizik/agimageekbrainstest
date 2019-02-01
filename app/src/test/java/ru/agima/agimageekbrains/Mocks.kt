package ru.agima.agimageekbrains

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

fun clickMock() =
    Single.just(0)
        .subscribeOn(Schedulers.io())
        .ignoreElement()