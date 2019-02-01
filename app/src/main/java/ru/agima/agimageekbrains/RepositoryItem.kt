package ru.agima.agimageekbrains

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlin.random.Random
import kotlinx.android.synthetic.main.repo.*
import kotlinx.android.synthetic.main.repo.view.*

class RepositoryItem(val name: String, val description: String): Item() {

    override fun getId(): Long = Random.nextLong()

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView2.text = name
        viewHolder.containerView.textView3.text = description
    }

    override fun getLayout(): Int = R.layout.repo

}
