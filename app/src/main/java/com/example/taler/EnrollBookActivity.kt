package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taler.adapter.EnrollBookAdapter
import com.example.taler.api.BookService
import com.example.taler.api.EnrollBookService
import com.example.taler.api.ReadBookService
import com.example.taler.dao.BestSellerDto
import com.example.taler.dao.EnrollBookDto
import com.example.taler.dao.ReadBookDto
import com.example.taler.dao.SearchBookDto
import com.example.taler.databinding.ActivityEnrollBookBinding
import com.example.taler.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnrollBookActivity : AppCompatActivity() {
    // 추천 목록을 베스트 셀러에서 가져옴

    private lateinit var binding: ActivityEnrollBookBinding
    private lateinit var bookService: BookService
    lateinit var bookList:MutableList<Book>
    lateinit var checkedBookList:MutableList<Book>
    lateinit var enrollBookService: EnrollBookService

    //private lateinit var enrollBookAdapter: EnrollBookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookList = mutableListOf<Book>()
        checkedBookList = mutableListOf<Book>()

        binding = ActivityEnrollBookBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initBookService()
        bookServiceLoadBestSellers()
        //bookServiceSearchBook("동화")
        Log.d("온크리에이트안북리스트",bookList.size.toString())

        initEnrollBookService()
        binding.selfBtn.setOnClickListener{
            addCheckedBookList()
            if(checkedBookList.size != 1){
                Toast.makeText(this,"책 1권을 선택해주세요!", Toast.LENGTH_SHORT).show()
                checkedBookList.removeAll(checkedBookList)
            }else{
                enrollBookServicePostBook("1",checkedBookList[0].title,checkedBookList[0].author)
                Log.d("인롤액티비티체크된북리스트",checkedBookList[0].author)
                startActivity(Intent(this,SelfWriteActivity::class.java))
            }
        }

        /*enrollBookAdapter.setOnBookClickListener(object:EnrollBookAdapter.OnBookClickListener{
            override fun onBookClick(position: Int, book: Book) {
                val intent = Intent(this@EnrollBookActivity,TalerMain::class.java)
                intent.putExtra("title",enrollBookAdapter.bookList[position].title)
                intent.putExtra("url",enrollBookAdapter.bookList[position].title)
                Log.d("인롤액티비티",enrollBookAdapter.bookList[position].title)
                startActivity(intent)
            }
        })*/
    }
    /*
    private fun initBookRecyclerView(bookList: MutableList<Book>) {
        enrollBookAdapter = EnrollBookAdapter(bookList)
        enrollBookAdapter.setOnBookClickListener(object:EnrollBookAdapter.OnBookClickListener{
            override fun onBookClick(position: Int, book: Book) {
                val intent = Intent(this@EnrollBookActivity,TalerMain::class.java)
                intent.putExtra("title",enrollBookAdapter.bookList[position].title)
                intent.putExtra("url",enrollBookAdapter.bookList[position].title)
                Log.d("인롤액티비티",enrollBookAdapter.bookList[position].title)
                startActivity(intent)
            }
        })
        binding.bookRv.layoutManager = GridLayoutManager(this,2)
        binding.bookRv.adapter = EnrollBookAdapter(bookList)
        binding.bookRv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }*/
    // 베스트 셀러 가져오기

    private fun bookServiceLoadBestSellers() {
        bookService.getBestSellerBooks(getString(R.string.interparkAPIKey))
            .enqueue(object : Callback<BestSellerDto> {
                // 응답이 온 경우;
                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    // 받은 응답이 성공한 응답일 때;
                    if (response.isSuccessful.not()) {
                        Log.e(M_TAG, "NOT!! SUCCESS")
                        return
                    }

                    // 받은 응답의 바디가 채워져 있는 경우만 진행;
                    response.body()?.let {
                        Log.d(M_TAG, it.toString())

                        it.books.forEach { book ->
                            Log.d(M_TAG, book.toString())
                            bookList.add(book)
                        }
                        //enrollBookAdapter = EnrollBookAdapter(it.books as MutableList<Book>)
                        //initBookRecyclerView(it.books as MutableList<Book>)
                        setRecyclerView(it.books as MutableList<Book>)
                        Log.d("함수안북리스트",bookList.size.toString())
                    }
                }

                // 응답에 실패한 경우
                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    Log.e(M_TAG, t.toString())
                }
            })
    }

    private fun initBookService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com/") //
            .addConverterFactory(GsonConverterFactory.create()) // Gson 변환기 사용;
            .build()

        bookService = retrofit.create(BookService::class.java)
    }
    private fun initEnrollBookService(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.37.173.13:8080/") //
            .addConverterFactory(GsonConverterFactory.create()) // Gson 변환기 사용;
            .build()

        enrollBookService = retrofit.create(EnrollBookService::class.java)
    }
    /*
    accuracy(기본값) : 정확도순
    publishTime : 출간일
    title : 제목
    salesPoint : 판매량
    customerRating : 고객평점
    reviewCount : 리뷰갯수
    price : 가격오름순
    priceDesc : 가격내림순*/
    // http://book.interpark.com/bookPark/html/bookpinion/api_booksearch.html
    fun bookServiceSearchBook(keyword: String) {
        bookService.getBooksByName(getString(R.string.interparkAPIKey), keyword,10,10,"customerRating")
            .enqueue(object : Callback<SearchBookDto> {
                // 성공.

                override fun onResponse(
                    call: Call<SearchBookDto>,
                    response: Response<SearchBookDto>
                ) {

                    if (response.isSuccessful.not()) {
                        Log.e(M_TAG, "NOT!! SUCCESS")
                        return
                    }

                    // 받은 응답의 바디가 채워져 있는 경우만 진행;
                    response.body()?.let {
                        Log.d(M_TAG, it.toString())

                        it.books.forEach { book ->

                        }
                        // 새 리스트로 갱신
                        setRecyclerView(it.books as MutableList<Book>)

                    }
                }

                // 실패.
                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {

                    Log.e(M_TAG, t.toString())
                }
            })
    }

    fun enrollBookServicePostBook(userId: String,bookTitle:String, bookAuthor:String) {
        enrollBookService.postEnrolledBook(userId,bookTitle,bookAuthor)
            .enqueue(object : Callback<EnrollBookDto> {
                // 성공.

                override fun onResponse(
                    call: Call<EnrollBookDto>,
                    response: Response<EnrollBookDto>
                ) {

                    if (response.isSuccessful.not()) {
                        Log.d("서버전송실패", "NOT!! SUCCESS")
                        return
                    }

                    // 받은 응답의 바디가 채워져 있는 경우만 진행;
                    response.body()?.let {
                        Log.d("서버전송성공", it.toString())
                    }
                }

                // 실패.
                override fun onFailure(call: Call<EnrollBookDto>, t: Throwable) {

                    Log.d("서버전송실패", t.toString())
                }
            })
    }
    /*
    fun enrollBookServicePostBook(userId:String,bookTitle:String, bookAuthor:String)  {
        enrollBookService.postEnrolledBook(userId,bookTitle, bookAuthor)
            .enqueue(object : Callback<EnrollBookDto> {
                // 성공.

                override fun onResponse(
                    call: Call<EnrollBookDto>,
                    response: Response<ReadBookDto>
                ) {

                    if (response.isSuccessful.not()) {
                        Log.d("readBookServicePostBook", "NOT!! SUCCESS")
                        return
                    }

                    // 받은 응답의 바디가 채워져 있는 경우만 진행;
                    response.body()?.let {
                        Log.d("readBookServicePostBook", it.toString())
                    }
                }

                // 실패.
                override fun onFailure(call: Call<EnrollBookDto>, t: Throwable) {

                    Log.d("readBookServicePostBook", t.toString())
                }
            })
    }*/

    fun setRecyclerView(bookList: MutableList<Book>){
        binding.bookRv.layoutManager = GridLayoutManager(this,2)
        binding.bookRv.adapter = EnrollBookAdapter(bookList)
        binding.bookRv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    fun addCheckedBookList(){
        for(book in bookList){
            if(book.checked){
                checkedBookList.add(book)
            }
        }
    }

    companion object {
        private const val M_TAG = "EnrollBookActivity"
    }

}