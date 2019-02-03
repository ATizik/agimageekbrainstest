package ru.agima.agimageekbrains

import android.view.View
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlin.random.Random
import kotlinx.android.synthetic.main.repo.*
import kotlinx.android.synthetic.main.repo.view.*

class RepositoryItem(val name: String, val description: String): Item() {

    override fun getId(): Long = Random.nextLong()

    override fun createViewHolder(itemView: View): ViewHolder {
        itemView.textView2.text = name
        itemView.textView3.text = description
        return super.createViewHolder(itemView)
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int = R.layout.repo

}
