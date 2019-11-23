package com.stimednp.apisportspractice.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.stimednp.apisportspractice.*
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.adapter
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.listTeam
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.presenter
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.progressBar
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.spinner
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.swipeRefreshLayout
import com.stimednp.apisportspractice.ui.anko.MainActivityUI.Companion.teams
import com.stimednp.apisportspractice.api.ApiRepository
import com.stimednp.apisportspractice.api.MainPresenter
import com.stimednp.apisportspractice.data.Teams
import com.stimednp.apisportspractice.ui.adapter.MainAdapter
import com.stimednp.apisportspractice.ui.MainView
import com.stimednp.apisportspractice.ui.anko.MainActivityUI
import com.stimednp.apisportspractice.ui.invisible
import com.stimednp.apisportspractice.ui.visible
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        adapter = MainAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(applicationContext, R.layout.spinner_item, spinnerItems)
        spinner.adapter = spinnerAdapter
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Teams>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
