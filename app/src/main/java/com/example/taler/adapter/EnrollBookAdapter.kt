package com.example.taler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taler.EnrollBookActivity
import com.example.taler.R
import com.example.taler.databinding.ActivityEnrollBookBinding
import com.example.taler.databinding.BookItemBinding
import com.example.taler.model.Book

// 리스너 공부 바람!!

// 개별 데이터에 대응
// ViewHolder 클래스에서 리스트가 아닌 객체 하나에 접근하는게 더 편하네 EnrollBookAdapter 도 수정 바람

// 모든 레이아웃이 코드로 변환되면 View가 된다고 생각하자!
class EnrollBookAdapter(val bookList: MutableList<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class EnrollViewHolder(val binding: BookItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book:Book){
            setContent(book)
            binding.bookIv.setOnClickListener{
                setChecked(book,it)
            }
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    bookClickListener?.onBookClick(pos,book)
                }
            }

        }

    }
    interface OnBookClickListener{
        fun onBookClick(position:Int, book:Book)
    }

    private lateinit var binding: BookItemBinding
    private lateinit var enrollBinding : ActivityEnrollBookBinding
    private lateinit var bookClickListener: OnBookClickListener
    //lateinit var bookList:MutableList<Book>
    var enrollBookActivity: EnrollBookActivity = EnrollBookActivity()


    fun setOnBookClickListener(listener: OnBookClickListener){
        this.bookClickListener = listener
    }


    // 뷰 생성 시

    // 어댑터 액티비티간 데이터 intent에서 막힘

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return EnrollViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        // LayoutInflater 는 xml -> 클래스로 변환해주는 역할
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding = (holder as EnrollViewHolder).binding

        holder.bind(bookList[position])
        binding.root.setOnClickListener{
            bookClickListener.onBookClick(position,bookList[position])
        }
        /*
        binding.bookIv.setOnClickListener{
            val intent = Intent(enrollBookActivity,TalerMain::class.java)
            enrollBookActivity.startActivity(intent)
        }
*/


    }
    // 항목 개수 반환
    override fun getItemCount(): Int{
        return bookList.size
    }

    // 선택되면 체크(빨간색) 아님 검은색
    // 선택되면 체크(빨간색) 아님 검은색
    private fun setChecked(book: Book,view: View){
        if(!book.checked){
            book.checked = true
            view.setBackgroundResource(R.drawable.shape_square_border_red)

        }else{
            book.checked = false
            view.setBackgroundResource(R.drawable.shape_square_border_black)

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