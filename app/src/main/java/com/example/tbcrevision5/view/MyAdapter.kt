package com.example.tbcrevision5.view

import android.annotation.SuppressLint
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcrevision5.databinding.*
import com.example.tbcrevision5.model.Data
import com.example.tbcrevision5.setImage

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val INPUT = 1
        const val GENDER = 2
        const val DATE = 3
    }

    var fieldsList = listOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            INPUT -> InputViewHolder(
                InputLayoutBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            GENDER -> GenderViewHolder(
                GenderLayoutBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> DateViewHolder(
                BirthdayLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InputViewHolder -> holder.bind()
            is GenderViewHolder -> holder.bind()
            is DateViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = fieldsList.size

    override fun getItemViewType(position: Int): Int {
        when (fieldsList[position][0][0].fieldType?.lowercase()) {
            "input" -> {
                return INPUT
            }
            "chooser" -> {
                when (fieldsList[position][0][0].hint?.lowercase()) {
                    "birthday" -> {
                        return DATE
                    }
                    "gender" -> {
                        return GENDER
                    }
                }
            }
        }
        return -1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: Data) {
        fieldsList = listOf(newList)
        notifyDataSetChanged()
    }

    inner class InputViewHolder(private val binding: InputLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = fieldsList[adapterPosition]
            binding.apply {
                etInput.apply {
                    when (fieldsList[adapterPosition][0][0].keyboard) {
                        "text" -> {
                            inputType = InputType.TYPE_CLASS_TEXT
                        }
                        "number" -> {
                            inputType = InputType.TYPE_CLASS_NUMBER
                        }
                    }
                    hint = currentItem[0][0].hint
                }
                ivIcon.setImage("https://icon-library.com/images/edit-icon-png/edit-icon-png-0.jpg")
                etInput.isEnabled = currentItem[0][0].isActive == true
            }
        }
    }

    inner class DateViewHolder(private val binding: BirthdayLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = fieldsList[adapterPosition]
            binding.apply {
                dpBirthday.updateDate(2002, 4, 28)
                ivIcon.setImage("https://icon-library.com/images/edit-icon-png/edit-icon-png-0.jpg")
                dpBirthday.isEnabled = currentItem[0][0].isActive == true
                tvHint.text = currentItem[0][0].hint
            }
        }
    }

    inner class GenderViewHolder(private val binding: GenderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = fieldsList[adapterPosition]
            binding.apply {
                ivIcon.setImage("https://icon-library.com/images/edit-icon-png/edit-icon-png-0.jpg")
                spGender.isEnabled = currentItem[0][0].isActive == true
                tvHint.text = currentItem[0][0].hint
            }
        }
    }
}