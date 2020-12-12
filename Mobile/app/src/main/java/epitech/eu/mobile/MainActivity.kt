package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
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
        val etNetworkLocation = findViewById<EditText>(R.id.et_network_location)
        val btnSubmit = findViewById<Button>(R.id.submit)

        val btnQrCode = findViewById<Button>(R.id.qrcode)
        btnQrCode.setOnClickListener {
            scanQRCode()
        }

        // set on-click listener
        btnSubmit.setOnClickListener {
            val userName = etUserName.text;
            val password = etPassword.text;
            val networkLocation = etNetworkLocation.text;

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
                    println("Failed to execute request : $e")
                    this@MainActivity.runOnUiThread(Runnable {
                        Toast.makeText(
                                this@MainActivity,
                                "Authentification failed",
                                Toast.LENGTH_SHORT
                        ).show()
                    })
                }

                override fun onResponse(call: Call, response: okhttp3.Response) {
                    val body = response.body?.string()

                    val gson = GsonBuilder().create()

                    val tmp = gson.fromJson(body, Response::class.java)
                    if (tmp.jwt == null) {
                        this@MainActivity.runOnUiThread(Runnable {
                            Toast.makeText(
                                this@MainActivity,
                                "Authentification failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        })

                    } else {
                        val intent = Intent(this@MainActivity, ArticleActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
            //val intent = Intent(this@MainActivity, ArticleActivity::class.java)
            //startActivity(intent)
        }
    }

    private fun scanQRCode(){
        val integrator = IntentIntegrator(this).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("")
        }
        integrator.initiateScan()
    }

    // Get the results:
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            else Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

class Response(val jwt: String)