package org.expodev.testkotlinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.expodev.testkotlinapp.data.SearchRepository
import org.expodev.testkotlinapp.data.SearchRepositoryProvider
import org.expodev.testkotlinapp.data.SourceOfQuotes


const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity(), ChangeSourceListener {
    override fun sourceChanged(position: Int) {
        Log.d(TAG, "From main: ${adapter[position]}")
        val intent = Intent(applicationContext, QuotesActivity::class.java)
        intent.putExtra(INTENT_NAME_NAME, adapter[position].name)
        intent.putExtra(INTENT_SITE_NAME, adapter[position].site)
        startActivity(intent)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val repository: SearchRepository = SearchRepositoryProvider.provideSearchRepository()
    private val list: MutableList<SourceOfQuotes> = mutableListOf()

    @BindView(R.id.list)
    lateinit var listView: RecyclerView

    lateinit var adapter: SourceOfQuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        listView.layoutManager = llm

        compositeDisposable.add(
                repository.searchSourcesOfQuotes()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe ({
                            result ->
                        result.forEach {list.addAll(it)}
                        adapter = SourceOfQuotesAdapter(list)
                        adapter.addListener(this)
                        listView.adapter = adapter
                        Log.i(TAG, list.toString())
                    }, {
                            throwable ->
                        Log.e(TAG, throwable.message)
                    })
        )

    }
}
