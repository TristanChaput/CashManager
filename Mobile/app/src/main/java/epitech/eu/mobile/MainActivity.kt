package epitech.eu.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import android.widget.*

class MainActivity : AppCompatActivity() {
    val JSON = "application/json; charset=utf-8".toMediaType()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        val etUserName = findViewById<EditText>(R.id.et_user_name)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        // set on-click listener
        btnSubmit.setOnClickListener {
            val userName = etUserName.text;
            val password = etPassword.text;
            Toast.makeText(this@MainActivity, userName, Toast.LENGTH_LONG).show()


            val url = "http://api.openweathermap.org/data/2.5/forecast?id=524901&appid={API key}"

            val request = Request.Builder().url(url).build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                    println(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    println("GOOD REQUEST to execute request")
                    println(response)
                }
            })
            // your code to validate the user_name and password combination
            // and verify the same

        }
    }
}