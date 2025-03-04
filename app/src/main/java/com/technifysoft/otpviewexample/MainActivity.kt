/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.technifysoft.otpviewexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.technifysoft.otpview.OTPView

class MainActivity : AppCompatActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val otpView = findViewById<OTPView>(R.id.otpView)
        val submitBtn = findViewById<MaterialButton>(R.id.submitBtn)

        otpView.setOnOTPCompleteListener { otp ->
            Toast.makeText(this, "OTP Completed: $otp", Toast.LENGTH_SHORT).show()
        }

        submitBtn.setOnClickListener {
            // Get the OTP value
            val otp = otpView.getOTP()
            // Do something with the OTP value
            if (otp.isEmpty()) {
                Toast.makeText(this, "OTP is empty", Toast.LENGTH_SHORT).show()
            } else if (otp.length < 6) {
                Toast.makeText(this, "OTP length is less than 6", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "OTP is $otp", Toast.LENGTH_SHORT).show()
            }
        }

    }

}