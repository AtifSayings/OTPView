# CustomOTPView

[![Maven Central](https://img.shields.io/maven-central/v/com.yourname/customotpview)](https://repo1.maven.org/maven2/com/yourname/customotpview/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

CustomOTPView is a customizable OTP input field for Android, allowing users to input and verify OTP codes easily.

## Features
- Customizable OTP length
- Supports numbers & text input types
- Adjustable spacing, padding, and text size
- Custom fonts & colors
- Auto focus on next field
- Backspace support to move to the previous field

## Installation

Add the following dependency in your **build.gradle.kts**:

```kotlin
dependencies {
    implementation("com.yourname:customotpview:1.0.0")
}
```

## Usage

Add the **CustomOTPView** to your XML layout:

```xml
<com.yourname.customotpview.CustomOTPView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:otp_otpLength="6"
    app:otp_inputType="number"
    app:otp_textSize="18sp"
    app:otp_cornerRadius="10dp"
    app:otp_spacingBetweenFields="12dp"
    app:otp_backgroundColor="#FFFFFF"
    app:otp_strokeColor="#000000"
    app:otp_strokeWidth="2dp" />
```

### Get OTP Input

```kotlin
val otpCode = customOtpView.getOTP()
```

## Customization

| Attribute                | Description                              | Example Value |
|--------------------------|------------------------------------------|--------------|
| `otp_otpLength`         | Number of OTP fields                    | `6`          |
| `otp_inputType`         | Input type (number, text)               | `number`     |
| `otp_textSize`          | Text size of OTP fields                 | `18sp`       |
| `otp_cornerRadius`      | Corner radius for OTP fields            | `10dp`       |
| `otp_spacingBetweenFields` | Spacing between OTP fields            | `12dp`       |
| `otp_backgroundColor`   | Background color of fields               | `#FFFFFF`    |
| `otp_strokeColor`       | Border color of OTP fields              | `#000000`    |
| `otp_strokeWidth`       | Border width                             | `2dp`        |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Feel free to open issues or submit pull requests to improve the library.

## Author
**Your Name**  
GitHub: [@yourusername](https://github.com/yourusername)

