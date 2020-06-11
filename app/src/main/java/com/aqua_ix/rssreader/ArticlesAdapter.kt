package com.aqua_ix.rssreader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticlesAdapter(
    private val context: Context,
    private val articles: List<Article>,
    private val onArticleClicked: (Article) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    // ビューホルダー
    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val pubDate: TextView = view.findViewById(R.id.pubDate)
    }

    override fun getItemCount(): Int = articles.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        // Viewを生成する
        val view = inflater.inflate(R.layout.grid_article_cell, parent, false)
        // ViewHolderを生成する
        val viewHolder = ArticleViewHolder(view)

        // Viewタップ時の処理
        view.setOnClickListener {
            // タップされた記事の位置
            val position = viewHolder.adapterPosition
            // タップされた位置に応じた記事
            val article = articles[position]
            // コールバックを呼ぶ
            onArticleClicked(article)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // アダプター中の位置に応じた記事を得る
        val article = articles[position]
        // 記事のタイトルを設定する
        holder.title.text = article.title
        // 記事の発行日付を設定する
        holder.pubDate.text = context.getString(R.string.pubDate, article.pubDate)
    }
}
