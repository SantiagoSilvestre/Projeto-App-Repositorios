package br.com.me.san.apprepositorios.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import br.com.me.san.apprepositorios.R
import br.com.me.san.apprepositorios.core.createDialog
import br.com.me.san.apprepositorios.core.createProgressDialog
import br.com.me.san.apprepositorios.core.hideSoftKeyboard
import br.com.me.san.apprepositorios.databinding.ActivityMainBinding
import br.com.me.san.apprepositorios.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { RepoListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        viewModel.getRepoList("SantiagoSilvestre")

        binding.recyclerView.adapter = adapter

        observe()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        p0?.let { viewModel.getRepoList(it)  }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        Log.d(TAG, "onQueryTextSubmit: $p0")
        return false
    }

    companion object {
        const val TAG = "TAG"
    }

    private fun observe() {

        viewModel.repos.observe(this) {
            when(it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

}