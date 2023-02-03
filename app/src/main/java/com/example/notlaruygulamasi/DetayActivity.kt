package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detay.*
import kotlinx.android.synthetic.main.activity_detay.editTextDers
import kotlinx.android.synthetic.main.activity_detay.editTextNot1
import kotlinx.android.synthetic.main.activity_detay.editTextNot2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_not_kayit.*

@Suppress("DEPRECATION")
class DetayActivity : AppCompatActivity() {

    private lateinit var not:Notlar
    private lateinit var vt: VeritabaniYardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)



        toolbarNotDetay.title = "Not Detay"
        setSupportActionBar(toolbarNotDetay)

        vt = VeritabaniYardimcisi(this)

        not = intent.getSerializableExtra("nesne") as Notlar

        editTextDers.setText(not.ders_adi)
        editTextNot1.setText((not.not1).toString())
        editTextNot2.setText((not.not2).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_sil ->{
                Snackbar.make(toolbarNotDetay,"Silinsin mi?", Snackbar.LENGTH_SHORT)
                    .setAction("EVET"){

                        Notlardao().notSil(vt,not.not_id)
                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                    }.show()

                return true
            }
            R.id.action_duzenle ->{

                val ders_adi = editTextDers.text.toString().trim()
                val not1 = editTextNot1.text.toString().trim()
                val not2 = editTextNot2.text.toString().trim()

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(toolbarNotDetay,"Ders adı giriniz.",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbarNotDetay,"1.Notu Giriniz.",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbarNotDetay,"2.Notu Giriniz.",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                Notlardao().notGuncelle(vt,not.not_id,ders_adi,not1.toInt(),not2.toInt())

                startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                finish()
                return true
            }
            else->{
                return false
            }
        }
    }
}