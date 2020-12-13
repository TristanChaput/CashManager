package epitech.eu.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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

class PaymentActivity : AppCompatActivity() {
    lateinit var network: String
    lateinit var token: String
    lateinit var amount: String
    lateinit var paymentUpdate: TextView
    lateinit var total: TextView
    lateinit var backMenuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        token = intent.getStringExtra("token").toString()
        network = intent.getStringExtra("network").toString()
        amount = intent.getStringExtra("amount").toString().dropLast(1)
        paymentUpdate = findViewById(R.id.payment_update)
        backMenuButton = findViewById(R.id.back_menu_button)
        //total = findViewById(R.id.total)

        backMenuButton.setOnClickListener {
            val intent = Intent(this@PaymentActivity, ArticleActivity::class.java)
            intent.putExtra("token",token)
            intent.putExtra("network", network)
            startActivity(intent)
        }

        val btnQrCode = findViewById<ImageButton>(R.id.qrcode)
        btnQrCode.setOnClickListener {
            scanQRCode()
        }
    }

    /**
     * QR Code scanner function
     */
    private fun scanQRCode(){
        val integrator = IntentIntegrator(this).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("")
        }
        integrator.initiateScan()
    }

    /**
     * Catch QR Code scanner result
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            else makePayment(result.contents)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    /**
     * Payment function, calling api to check is payment is possible and displaying the payment update (pending, accepted, refused)
     */
    private fun makePayment(account: String) {
        val url = "$network/accounts/pay"

        val JSON = "application/json; charset=utf-8".toMediaType()
        val json = "{\"number\": \"$account\", \"amount\": \"$amount\"}"
        val body = json.toRequestBody(JSON)
        val request = Request.Builder().method("POST", body).addHeader(
            "Content-Type",
            "application/json"
        ).addHeader(
            "Authorization",
            "Bearer $token"
        ).url(url).build()

        val client = OkHttpClient()

        //total.toggleVisibility()
        paymentUpdate.visibility = View.VISIBLE
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request : $e")
                this@PaymentActivity.runOnUiThread(Runnable {
                    Toast.makeText(
                        this@PaymentActivity,
                        "Payment failed",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()

                val tmp = gson.fromJson(body, PaymentResponse::class.java)
                if (tmp.message == "Congratulation. Have good holidays !") {
                    successfulPayment()
                } else {
                    refusedPayment()
                }
            }
        })
    }

    private fun successfulPayment() {
        this@PaymentActivity.runOnUiThread(Runnable {
            paymentUpdate.text = getString(R.string.paymentAccepted)
            backMenuButton.visibility = View.VISIBLE
        })
    }

    private fun refusedPayment() {
        this@PaymentActivity.runOnUiThread(Runnable {
            paymentUpdate.text = getString(R.string.paymentRefused)
            backMenuButton.visibility = View.VISIBLE
        })
    }
}

class PaymentResponse(val message: String)