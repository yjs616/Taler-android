package com.example.taler

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taler.adapter.BookAdapter
import com.example.taler.api.BookService
import com.example.taler.api.ReadBookService
import com.example.taler.dao.BestSellerDto
import com.example.taler.dao.ReadBookDto
import com.example.taler.dao.SearchBookDto
import com.example.taler.databinding.ActivitySelectBookBinding
import com.example.taler.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SelectBookActivity : AppCompatActivity() {
    // 인터파크 api 주소
    // http://book.interpark.com/api/
    // 인증키 : BC4B833441C648690AFBC811F686190E79323052B2F7149F8661B2E03164B85F

    private lateinit var binding: ActivitySelectBookBinding
    private lateinit var bookService: BookService
    lateinit var bookList:MutableList<Book>
    private lateinit var readBookService: ReadBookService
    //var bookList: MutableList<Book>  mutableListOf<Book>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookList = mutableListOf<Book>()

        binding = ActivitySelectBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookService()
        //bookServiceLoadBestSellers()
        bookServiceSearchBook("동화")
        //Log.d("셀렉트북", bookList.size.toString())
        val testBook = Book(1,"test","test","test","test",true)

        //다음 버트 누르면 -> Tutorial1Actity로 이동
        binding.nextBtn.setOnClickListener{
            startActivity(Intent(this,Tutorial1Activity::class.java))
            initReadBookService()
            readBookServicePostBook(333,bookList[0].id,bookList[2].id,bookList[3].id,bookList[4].id,bookList[5].id)
            Log.d("버튼안북리스트","${bookList[0].id},${bookList[2].id},${bookList[3].id},${bookList[4].id},${bookList[5].id}")
        }
    }
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
                        Log.d("함수안안북리스트",bookList.size.toString())
                        // 새 리스트로 갱신;
                        //bookRecyclerViewAdapter.submitList(it.books)
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

    private fun initReadBookService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.37.173.13:8080/") //
            .addConverterFactory(GsonConverterFactory.create()) // Gson 변환기 사용;
            .build()

        readBookService = retrofit.create(ReadBookService::class.java)
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
        bookService.getBooksByName(getString(R.string.interparkAPIKey), keyword,2,10,"salesPoint")
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
                            Log.d(M_TAG, "${book.title} ${book.coverLargeUrl}")
                            bookList.add(book)
                        }

                        // 새 리스트로 갱신
                        Log.d("함수안안북리스트",bookList.size.toString())
                        setRecyclerView(it.books as MutableList<Book>)
                        //setBookList(it.books)

                    }
                }

                // 실패.
                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {

                    Log.e(M_TAG, t.toString())
                }
            })
    }

    fun readBookServicePostBook(userId: Long,bookId1:Long,bookId2:Long,bookId3:Long,bookId4:Long,bookId5:Long) {
        readBookService.postBookById(userId,bookId1,bookId2,bookId3,bookId4,bookId5)
            .enqueue(object : Callback<ReadBookDto> {
                // 성공.

                override fun onResponse(
                    call: Call<ReadBookDto>,
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
                override fun onFailure(call: Call<ReadBookDto>, t: Throwable) {

                    Log.d("readBookServicePostBook", t.toString())
                }
            })
    }

    fun setRecyclerView(bookList: MutableList<Book>){
        binding.bookRv.layoutManager = GridLayoutManager(this,2)
        binding.bookRv.adapter = BookAdapter(bookList)
        binding.bookRv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
/*
    fun setBookList(bookList:MutableList<Book>){
        this.bookList = bookList
    }
    fun getBookList():MutableList<Book>{
        return bookList
    }*/

    companion object {
        private const val M_TAG = "SelectBookActivity"
    }
}
