package ru.agima.agimageekbrains

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject

class MainViewModel(gitHubServ: GitHubService = gitHubService) {
    val disposable = CompositeDisposable()
    val state:BehaviorSubject<MainViewState> = BehaviorSubject.createDefault(MainViewState.Loading)

    fun onClicked() {
        disposable += gitHubService.listRepos()
            .doOnSubscribe { state.onNext(MainViewState.Loading) }
            .doOnSuccess { state.onNext(MainViewState.Content(it)) }
            .subscribeBy ()
    }


    fun onCleared() {

    }
}

operator fun CompositeDisposable.plusAssign(disp: Disposable) {
    this.add(disp)
}

sealed class MainViewState() {
    data class Content(val repoList:List<Repository>):MainViewState()
    object Loading:MainViewState()
    data class Error(val error:Throwable):MainViewState()
}