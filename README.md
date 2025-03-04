# OTPView

CustomOTPView is an easy-to-use, customizable OTP input view for Android. It provides flexibility to change the background color, text size, spacing, stroke, and more.

## Features
- Customizable OTP length
- Adjustable spacing between OTP fields
- Custom fonts support
- Supports different input types (numeric, alphanumeric, etc.)
- Customizable text color, background color, and stroke
- Rounded corners with adjustable radius

## Installation

### Gradle
Add the following dependency in your project's `build.gradle`:

```gradle
dependencies {
    //Will be availanle soon as following
    implementation 'com.technifysoft:otpview:1.0.0'
}
```

## Usage
Add `CustomOTPView` to your XML layout:

```xml
<com.technifysoft.otpview.OTPView
        android:id="@+id/otpView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:ts_otp_backgroundColor="#E9E9E9"
        app:ts_otp_cornerRadius="10dp"
        app:ts_otp_fontFamily="@font/poppins_bold"
        app:ts_otp_inputType="2"
        app:ts_otp_otpLength="6"
        app:ts_otp_padding="10dp"
        app:ts_otp_spacingBetweenFields="10dp"
        app:ts_otp_strokeColor="#908B8B"
        app:ts_otp_strokeWidth="1dp"
        app:ts_otp_textColor="#6F6F6F"
        app:ts_otp_textSize="16sp" />
```

### Handling OTP Input in Kotlin

```kotlin
val otpView = findViewById<OTPView>(R.id.otpView)

//Manually get e.g. by clicking on a button
val otp = otpView.getOTP()

//OR

//Otp completion listener
otpView.setOnOTPCompleteListener { otp ->
    Toast.makeText(this, "OTP Completed: $otp", Toast.LENGTH_SHORT).show()
}
```

## Customization
You can customize the OTP view using the following attributes:

| Attribute | Description                                                                                                             |
|-----------|-------------------------------------------------------------------------------------------------------------------------|
| `ts_otp_otpLength` | Sets the OTP length (default: 6)                                                                                        |
| `ts_otp_textSize` | Sets the text size of OTP fields                                                                                        |
| `ts_otp_textColor` | Changes the text color of OTP fields                                                                                    |
| `ts_otp_backgroundColor` | Sets the background color of OTP fields                                                                                 |
| `ts_otp_spacingBetweenFields` | Defines the spacing between OTP fields                                                                                  |
| `ts_otp_strokeColor` | Sets the border color                                                                                                   |
| `ts_otp_strokeWidth` | Adjusts the stroke width                                                                                                |
| `ts_otp_cornerRadius` | Defines the corner radius for OTP fields                                                                                |
| `ts_otp_fontFamily` | Allows setting a custom font                                                                                            |
| `ts_otp_inputType` | Defines input type (e.g., 1(text input), 2(number 0-9), 3(phone), 4(date-time), 129(text password) 18(number password)) |

## License
MIT License. See [LICENSE](LICENSE) for details.

## Author
Developed by **TechnifySoft**.

## Contributing
Pull requests are welcome! If you have any issues or feature requests, feel free to open an issue.

