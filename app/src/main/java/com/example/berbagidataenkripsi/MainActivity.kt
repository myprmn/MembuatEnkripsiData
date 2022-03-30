package com.example.berbagidataenkripsi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Membuat tempat simpanan
        val tempatData = getPreferences(Context.MODE_PRIVATE)
        //Membuat fungsi simpanan
        btn_simpan.setOnClickListener {
            //Menyimpan nilai dari XML ke Kotlin
            var nama = et_nama.text.toString()
            //Menguji nilai - nilai nama kosong
            if (nama.isEmpty()) {
                //Membuat pesan dan menampilkan
                Toast.makeText(
                    this,
                    "Masukan Nama Anda",
                    Toast.LENGTH_SHORT
                ).show()
                //kembali ke fungsi simpan
                return@setOnClickListener
            }
            //Membuat tempat simpanan untuk
            //Nilai variable yang dienkripsi
            val simpanNama = enkripsiData(nama)
            //Merubah nilai yang disimpan
            val simpanData = tempatData.edit()
            //Memindahkan nilai ke variable preference
            simpanData.putString("nama",simpanNama)
            simpanData.apply()
            Toast.makeText(
                this,
                "Nama anda tersimpan",
                Toast.LENGTH_SHORT
            ).show()
            //Membuat fungsi baru
            kosongkanText()
        }
        btn_panggil.setOnClickListener {
            var panggilNama = tempatData.getString("nama", null)
            var panggilHasil = deskripsiData(panggilNama.toString())
            tv_hasil.text = "$panggilHasil \n $panggilNama"
        }
    }

    private fun kosongkanText() {
        et_nama.setText("")
        tv_hasil.setText("")
    }

    private fun enkripsiData(namaEnkripsi: String): String {
        val enkripsi = encode(namaEnkripsi.toByteArray(), DEFAULT)
        return String(enkripsi)
    }

    private fun deskripsiData(namaEnkripsi: String): String {
        val enkripsi = decode(namaEnkripsi.toByteArray(), DEFAULT)
            return String(enkripsi)
    }
}