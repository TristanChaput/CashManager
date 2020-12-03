package epitech.eu.mobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // get reference to all views
        val etUserName = findViewById<EditText>(R.id.et_user_name)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnSubmit = findViewById<Button>(R.id.submit)

        // set on-click listener
        btnSubmit.setOnClickListener {
            val userName = etUserName.text;
            val password = etPassword.text;

            val url = "http://192.168.1.80:8080/auth/local"

            val JSON = "application/json; charset=utf-8".toMediaType()
            val json = "{\"identifier\": \"$userName\", \"password\": \"$password\"}"
            val body = json.toRequestBody(JSON)
            val request = Request.Builder().method("POST", body).header(
                "Content-Type",
                "application/json"
            ).url(url).build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }

                override fun onResponse(call: Call, response: okhttp3.Response) {
                    val body = response.body?.string()

                    val gson = GsonBuilder().create()

                    val tmp = gson.fromJson(body, Response::class.java)
                    if (tmp.jwt == "") {
                        println("raté")
                    } else {
                        println("ouaiiii")
                    }
                }
            })

        }
    }
}

class Response(val jwt: String)