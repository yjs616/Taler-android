package com.example.taler

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

/*
* 1. 하이준 -> xml파일에 책 사이즈가 작으면 중간에 위치하도록 해주세요, xml파일에 중간 중간 줄이 있던데 없애주세요! ㅇ
* 2. 하이준 -> 책 10권 데이터 그립에서 bookdetails에 insert해주시고 5권 선택시 자동으로 favorite user id는 1로, 나머지 선택한 5권 들어갈 수 있도록 해주세요 해당 api는 노션에서 확인 부탁드리겠습니다!
* */
class SelectBookActivity : AppCompatActivity() {
    // 인터파크 api 주소
    // http://book.interpark.com/api/
    // 인증키 : BC4B833441C648690AFBC811F686190E79323052B2F7149F8661B2E03164B85F


    private lateinit var binding: ActivitySelectBookBinding
    private lateinit var bookService: BookService

    // "동화"라고 검색한 결과 중 판매량 순으로 2번부터 10개의 책을 담은 리스트
    lateinit var bookList:MutableList<Book>
    private lateinit var readBookService: ReadBookService

    // 선택된 책 5권이 담긴 리스트
    lateinit var checkedBookList:MutableList<Book>
    lateinit var bookAdapter:BookAdapter



    //var bookList: MutableList<Book>  mutableListOf<Book>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookList = mutableListOf<Book>()
        checkedBookList = mutableListOf<Book>()

        binding = ActivitySelectBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookService()
        //bookServiceLoadBestSellers()
        bookServiceSearchBook("피터팬")
        bookServiceSearchBook("백설공주")
        bookServiceSearchBook("신데렐라")
        bookServiceSearchBook("흥부와 놀부")
        bookServiceSearchBook("심청전")
        bookServiceSearchBook("아기돼지 삼형제")
        bookServiceSearchBook("강아지똥")
        bookServiceSearchBook("구름빵")
        bookServiceSearchBook("무지개 물고기와 이야기꾼")
        bookServiceSearchBook("토끼와 거북이")
        // 약간의 텀이 필요한데?
        setRecyclerView(bookList)


        /*for(book in bookList){
            if(book.checked){
                checkedBookList.add(book)
                Log.d("북리스트",book.id.toString())
            }
        }*/
        Log.d("체크된북리스트",checkedBookList.size.toString())

        //다음 버튼 누르면 -> Tutorial1Actity로 이동
        binding.nextBtn.setOnClickListener{
            //Log.d("온크리에이트북리스트", bookList.size.toString())
            initReadBookService()

            // 선택된 책 checkedBookList에 리스트 형식으로 담음...
            addCheckedBookList()
            setRecyclerView(bookList)


            //Log.d("버튼안체크된북리스트개수","${checkedBookList.size}")
            if(checkedBookList.size != 5){
                Toast.makeText(this,"책 5권을 선택해주세요!",Toast.LENGTH_SHORT).show()
                checkedBookList.removeAll(checkedBookList)
            }else{

                readBookServicePostBook(999,checkedBookList[0].id,checkedBookList[1].id,checkedBookList[2].id,checkedBookList[3].id,checkedBookList[4].id)
                startActivity(Intent(this,Tutorial1Activity::class.java))
            }
            Log.d("북리스트작가",bookList[0].author)
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
        bookService.getBooksByName(getString(R.string.interparkAPIKey), keyword,1,1,"accuracy")
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
        binding.bookRv.removeItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    fun addCheckedBookList(){
        for(book in bookList){
            if(book.checked){
                checkedBookList.add(book)
            }
        }
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
