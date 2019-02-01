package ru.agima.agimageekbrains

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val compDisp = CompositeDisposable()
    lateinit var mainViewModel: MainViewModel

    val section = Section()
    val groupAdapter = GroupAdapter<ViewHolder>()
        .apply { add(section) }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = lastCustomNonConfigurationInstance as MainViewModel? ?: MainViewModel()

        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = groupAdapter

        button.clicks().forEach {
            textView.text = "Button Clicked"
            mainViewModel.onClicked()
        }

        compDisp += mainViewModel.state
            .throttleLast(0, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = ::mainStateBinder)
    }

    private fun mainStateBinder(mainViewState: MainViewState) {
        when (mainViewState) {
            is MainViewState.Content -> onContent(mainViewState)
            is MainViewState.Error -> onError(mainViewState)
        }
    }

    private fun onContent(mainViewState: MainViewState.Content) {
        section.update(mainViewState.repoList.map(Repository::toItem))
    }

    private fun onError(mainViewState: MainViewState.Error) {
        section.update(listOf())
        Toast.makeText(this,mainViewState.error.localizedMessage,Toast.LENGTH_SHORT).show()
    }

    private fun onLoading(mainViewState: MainViewState.Loading) {
        Toast.makeText(this,"Загрузка",Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        compDisp.clear()
    }

    override fun onRetainCustomNonConfigurationInstance() = mainViewModel


}

private fun Repository.toItem() = RepositoryItem(name ?: "No name" ,description ?: "No description")
