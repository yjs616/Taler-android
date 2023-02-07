package com.example.taler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taler.R
import com.example.taler.databinding.BookItemBinding
import com.example.taler.model.Book

// 책 선택 페이지 리사이클러뷰의 어댑터

// 개별 데이터에 대응
// ViewHolder 클래스에서 리스트가 아닌 객체 하나에 접근하는게 더 편하네 EnrollBookAdapter 도 수정 바람
class BookViewHolder(val binding: BookItemBinding): RecyclerView.ViewHolder(binding.root){
    var count = 0
    fun bind(book: Book){
        setContent(book)
        binding.bookIv.setOnClickListener{
            setChecked(book,it)
        }
    }

    // 선택되면 체크(빨간색) 아님 검은색
    private fun setChecked(book: Book,view: View){
        if(!book.checked){
            book.checked = true
            view.setBackgroundResource(R.drawable.shape_square_border_red)
            count++

        }else{
            book.checked = false
            view.setBackgroundResource(R.drawable.shape_square_border_black)
            count--
        }
    }
    // 이미지, 텍스트 item에 적용
    private fun setContent(book:Book){
        //binding.titleTv.text = book.title
        Glide.with(binding.bookIv.context)
            .load(book.coverLargeUrl)
            .into(binding.bookIv)
    }

}

// 모든 레이아웃이 코드로 변환되면 View가 된다고 생각하자!
class BookAdapter(val bookList: MutableList<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: BookItemBinding
    // 뷰 생성 시
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return BookViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        // LayoutInflater 는 xml -> 클래스로 변환해주는 역할
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding = (holder as BookViewHolder).binding
        holder.bind(bookList[position])
        /*binding.bookIv.setOnClickListener{
            setChecked(position,it)
        }
        setContent(position)*/

    }
    // 항목 개수 반환
    override fun getItemCount(): Int{
        return bookList.size
    }

}