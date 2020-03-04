package com.example.moviename.movies.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

/**
 * ViewModel class for the movies.
 */
class MoviesModel : ViewModel() {

    private val client = OkHttpClient()
    private var mutableLiveData = MutableLiveData<ArrayList<MoviesResults>>()
    val itemsList = ArrayList<MoviesResults>()
    val filteredItemsList = ArrayList<MoviesResults>()
    private val mCrossIcon = MutableLiveData<Boolean>()
    /**
     * This method is used for to call the movies api after get the response from the server we are sending the data to the
     * activity via mutable live data as a movies arrayList
     */
    fun callGetMoviesApi(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("exe", "error" + e.localizedMessage)
                mutableLiveData.postValue(null)

            }

            override fun onResponse(call: Call, response: Response) {

                val gson = Gson()
                val response = response.body?.string()
                val movieResponse = gson.fromJson(response, MoviesPojo::class.java!!)
                itemsList.addAll(movieResponse.results)
                filteredItemsList.addAll(itemsList)
                mutableLiveData.postValue(itemsList)
            }
        })
    }


    /**
     * handle text changes of search list
     */
    fun onSearchList(s: CharSequence, start: Int, before: Int, count: Int) {
        val searchList = s.toString().toLowerCase()
        if (searchList.isEmpty()) {
            filteredItemsList.addAll(itemsList)
            mutableLiveData.postValue(filteredItemsList)
            return
        }
        filteredItemsList.clear()
        for (item in itemsList) {

            if (item.title.toLowerCase().contains(searchList)) {
                filteredItemsList.add(item)
            }
        }
        mutableLiveData.postValue(filteredItemsList)


    }

    /**
     * notify when cross icon clicked.
     */
    fun onCrossClicked() {

        mCrossIcon.postValue(true)
    }

    /**
     * notify when movies list will come.
     */
    fun getMoviesData(): MutableLiveData<ArrayList<MoviesResults>> {
        return mutableLiveData
    }

    /**
     * notify when cross icon clicked.
     */
    fun crossClicked(): MutableLiveData<Boolean> {
        return mCrossIcon
    }

}