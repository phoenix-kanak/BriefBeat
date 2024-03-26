package com.example.briefbeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),CategoryRVAdapter.CategoryClickInterface {
    private lateinit var newsRV: RecyclerView
    private lateinit var categoryRV: RecyclerView
    private lateinit var progressbar:ProgressBar
    private lateinit var articlesArrayList:ArrayList<Articles>
    private lateinit var categoryModalArrayList:ArrayList<CategoryModal>
    private lateinit var categoryRVAdapter: CategoryRVAdapter
    private lateinit var newsRVAdapter: NewsRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //db223ddb2c07443686bfdc750ab4846a
        setContentView(R.layout.activity_main)
        newsRV=findViewById(R.id.vRV)
        categoryRV=findViewById(R.id.hRV)
        progressbar=findViewById(R.id.Pbar)
        articlesArrayList = ArrayList<Articles>()
        categoryModalArrayList=ArrayList<CategoryModal>()
        categoryRVAdapter = CategoryRVAdapter(categoryModalArrayList, this, this::onCategoryClick)
        newsRVAdapter= NewsRVAdapter(articlesArrayList,this)
        newsRV.layoutManager = LinearLayoutManager(this)
        newsRV.adapter=newsRVAdapter
        categoryRV.adapter=categoryRVAdapter
        getCategories()
        getNews("All")
        newsRVAdapter.notifyDataSetChanged()
    }

    fun getCategories(){
        categoryModalArrayList.add(CategoryModal("All","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5_jbTu9KqPs28Pp-IYY1uskfhHTLbnqW0Ug&usqp=CAU"))
        categoryModalArrayList.add(CategoryModal("Science","https://assets.technologynetworks.com/production/dynamic/images/content/368887/science-is-becoming-less-disruptive-368887-960x540.jpg?cb=12105518"))
        categoryModalArrayList.add(CategoryModal("Technology","https://www.tofler.in/blog/wp-content/uploads/2023/08/technology.jpg"))
        categoryModalArrayList.add(CategoryModal("General","https://assets.weforum.org/global_future_council/image/FAdBujGNsB9kx8aa04DoOi3g5mnmf-OkZPy_0idrKMI.jpg"))
        categoryModalArrayList.add(CategoryModal("Sports","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKAkSRStDdnOJFFQBbhszGokRz5ADw4Btt4eNsXm-H393llFKh56mkpq9DHOp8vKrNqKY&usqp=CAU"))
        categoryModalArrayList.add(CategoryModal("Business","https://cloudinary.hbs.edu/hbsit/image/upload/s--EmT0lNtW--/f_auto,c_fill,h_375,w_750,/v20200101/6978C1C20B650473DD135E5352D37D55.jpg"))
        categoryModalArrayList.add(CategoryModal("Health","https://imageio.forbes.com/specials-images/imageserve/638ee3f77ab30981d50d997c/The-Top-5-Healthcare-Trends-In-2023/960x0.jpg?height=399&width=711&fit=bounds"))
        categoryModalArrayList.add(CategoryModal("Entertainment","https://etimg.etb2bimg.com/photo/75690061.cms"))
        categoryRVAdapter.notifyDataSetChanged()
    }
    private fun getNews(category: String) {
        progressbar.visibility= View.VISIBLE
        articlesArrayList.clear()
        val categoryURL =
            "https://newsapi.org/v2/top-headlines?country=in&category=$category&apikey=db223ddb2c07443686bfdc750ab4846a"
        val url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=db223ddb2c07443686bfdc750ab4846a"
        val Base_URL="https://newsapi.org/"
        val retrofit = Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<NewsModal?>? = if (category == "All") {
            retrofitAPI.getAllNews(url)
        } else {
            retrofitAPI.getNewsByCategory(categoryURL)
        }

        call?.enqueue(object : Callback<NewsModal?> {
            override fun onResponse(call: Call<NewsModal?>, response: Response<NewsModal?>) {
                    val newsModal = response.body()
                    progressbar.visibility= View.GONE
                    val articles: ArrayList<Articles> = newsModal!!.articles
                for (i in 0 until articles.size) {
                    val article = articles[i]
                    articlesArrayList.add(Articles(article.title, article.description,
                        article.urlToImage,article.getUrlToImage,article.content))
                }
                newsRVAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsModal?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Oops something went wrong!", Toast.LENGTH_SHORT).show()            }
        })
    }


    override fun onCategoryClick(position: Int) {
        val category = categoryModalArrayList[position].category
        getNews(category)
    }
}