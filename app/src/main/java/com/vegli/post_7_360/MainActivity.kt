package com.vegli.post_7_360
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vegli.post_7_360.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Harry Potter Books"
        setContentView(binding.root)
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        fetchBooks()
    }

    private fun fetchBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://potterapi-fedeperin.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.getBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(
                call: Call<List<Book>>,
                response: Response<List<Book>>
            ) {
                if (response.isSuccessful) {
                    val books = response.body() ?: emptyList()
                    binding.rvBooks.adapter = BookAdapter(books)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
