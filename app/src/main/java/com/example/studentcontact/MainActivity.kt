package com.example.studentcontact

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // Menggunakan 'lateinit' karena komponen baru diinisialisasi di onCreate
    private lateinit var etNamaLengkap: EditText
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var rbLakiLaki: RadioButton
    private lateinit var rbPerempuan: RadioButton
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbCoding: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var btnTampilkan: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi komponen UI
        etNamaLengkap = findViewById(R.id.etNamaLengkap)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        rbLakiLaki = findViewById(R.id.rbLakiLaki)
        rbPerempuan = findViewById(R.id.rbPerempuan)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbCoding = findViewById(R.id.cbCoding)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        btnTampilkan = findViewById(R.id.btnTampilkan)
        tvHasil = findViewById(R.id.tvHasil)

        // Event Handler menggunakan Lambda Expression khas Kotlin
        btnTampilkan.setOnClickListener {
            prosesValidasiDanTampilkan()
        }
    }

    private fun prosesValidasiDanTampilkan() {
        val nama = etNamaLengkap.text.toString().trim()

        // 1. Validasi Gagal: Jika Nama Kosong (Gambar 3)
        if (nama.isEmpty()) {
            etNamaLengkap.error = "Nama tidak boleh kosong"
            Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            return // Keluar dari fungsi
        }

        // 2. Mengambil data Jenis Kelamin
        val jenisKelamin = when (rgJenisKelamin.checkedRadioButtonId) {
            R.id.rbLakiLaki -> "Laki-laki"
            R.id.rbPerempuan -> "Perempuan"
            else -> "-"
        }

        // 3. Mengambil data Hobi menggunakan List & Filtering khas Kotlin yang ringkas
        val listHobi = mutableListOf<String>()
        if (cbMembaca.isChecked) listHobi.add("Membaca")
        if (cbCoding.isChecked) listHobi.add("Coding")
        if (cbOlahraga.isChecked) listHobi.add("Olahraga")

        // joinToString otomatis menggabungkan list dengan koma (misal: "Membaca, Coding")
        val stringHobi = if (listHobi.isNotEmpty()) listHobi.joinToString(", ") else "-"

        // 4. Sukses: Tampilkan Hasil ke TextView dengan String Template ($nama, dll)
        val hasilOutput = """
            Nama  : $nama
            Kelamin : $jenisKelamin
            Hobi  : $stringHobi
        """.trimIndent()

        tvHasil.text = hasilOutput

        // Mengubah warna teks hasil menjadi biru gelap bawaan android sistem ketika sukses
        tvHasil.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
    }
}