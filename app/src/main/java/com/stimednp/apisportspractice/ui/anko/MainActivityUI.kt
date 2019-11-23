package com.stimednp.apisportspractice.ui.anko

import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stimednp.apisportspractice.api.MainPresenter
import com.stimednp.apisportspractice.R.color.*
import com.stimednp.apisportspractice.data.Teams
import com.stimednp.apisportspractice.ui.activity.MainActivity
import com.stimednp.apisportspractice.ui.adapter.MainAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by rivaldy on 11/6/2019.
 */

class MainActivityUI : AnkoComponent<MainActivity> {
    companion object {

        val teams: MutableList<Teams> = mutableListOf()
        lateinit var presenter: MainPresenter
        lateinit var adapter: MainAdapter
        lateinit var leagueName: String

        lateinit var listTeam: RecyclerView
        lateinit var progressBar: ProgressBar
        lateinit var swipeRefreshLayout: SwipeRefreshLayout
        lateinit var spinner: Spinner
    }

    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui) {
            linearLayout() {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL
                padding = dip(16)

                spinner = spinner(){

                }
                swipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(
                        colorAccent,
                        colorPrimary,
                        colorPrimaryDark,
                        colorAccent
                    )
                    relativeLayout() {
                        lparams(matchParent, wrapContent)

                        listTeam = recyclerView() {
                            lparams(matchParent, wrapContent)
                            layoutManager = LinearLayoutManager(context)
                        }
                        progressBar = progressBar() {}.lparams { centerHorizontally() }
                    }
                }
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        leagueName = spinner.selectedItem.toString()
                        presenter.getTeamList(
                            leagueName
                        )
                        presenter.getTeamList(
                            leagueName
                        )
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }
                swipeRefreshLayout.onRefresh {
                    presenter.getTeamList(
                        leagueName
                    )
                }
            }
        }
    }
}