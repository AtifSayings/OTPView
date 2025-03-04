/*
 * Atif Pervaiz
 * Github: https://github.com/AtifSayings
 * Website: https://www.technifysoft.com/
 * Playstore: https://play.google.com/store/apps/dev?id=9049012648188611488
 */

package com.technifysoft.otpview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

/**
 * OTPView is a custom view for entering One-Time Passwords (OTPs).
 * It displays a series of input fields where the user can enter each digit of the OTP.
 *
 * @constructor Creates an OTPView with the specified context, attributes, and default style.
 *
 * @param context The application environment.
 * @param attrs A collection of attributes specified in an XML tag.
 * @param defStyleAttr An attribute in the current theme that contains a reference to a style resource that supplies default values for the view.
 */
class OTPView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var numFields: Int = 6
    private var inputType: Int = InputType.TYPE_CLASS_NUMBER
    private var textSize: Float = 16f
    private var cornerRadius: Float = 8f
    private var backgroundColor: Int = Color.WHITE
    private var strokeColor: Int = Color.GRAY
    private var strokeWidth: Float = 2f
    private var spacing: Int = 8 // Spacing between fields
    private var padding: Int = 8 // Padding inside each field
    private var font: Typeface? = null

    private var onOTPCompleteListener: ((String) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        parseAttributes(context, attrs)
        setupOTPFields()
    }

    private fun parseAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.OTPView)
            numFields = typedArray.getInt(R.styleable.OTPView_ts_otp_otpLength, 6)
            inputType = typedArray.getInt(R.styleable.OTPView_ts_otp_inputType, InputType.TYPE_CLASS_NUMBER)
            textSize = typedArray.getDimension(R.styleable.OTPView_ts_otp_textSize, 16f)
            cornerRadius = typedArray.getDimension(R.styleable.OTPView_ts_otp_cornerRadius, 8f)
            backgroundColor = typedArray.getColor(R.styleable.OTPView_ts_otp_backgroundColor, Color.WHITE)
            strokeColor = typedArray.getColor(R.styleable.OTPView_ts_otp_strokeColor, Color.GRAY)
            strokeWidth = typedArray.getDimension(R.styleable.OTPView_ts_otp_strokeWidth, 2f)
            spacing = typedArray.getDimensionPixelSize(R.styleable.OTPView_ts_otp_spacingBetweenFields, 8)
            padding = typedArray.getDimensionPixelSize(R.styleable.OTPView_ts_otp_padding, 8)

            val fontId = typedArray.getResourceId(R.styleable.OTPView_ts_otp_fontFamily, -1)
            if (fontId != -1) {
                font = ResourcesCompat.getFont(context, fontId)
            }

            typedArray.recycle()
        }
    }

    private fun setupOTPFields() {
        removeAllViews()

        val fieldSize = (textSize * 2 + padding * 2).toInt() // Ensure square shape, include padding
        val params = LayoutParams(fieldSize, fieldSize)
        params.setMargins(spacing / 2, 0, spacing / 2, 0)

        for (i in 0 until numFields) {
            val editText = EditText(context).apply {
                layoutParams = params
                gravity = Gravity.CENTER
                setPadding(padding, padding, padding, padding)
                inputType = this@OTPView.inputType
                setTextSize(TypedValue.COMPLEX_UNIT_PX, this@OTPView.textSize)
                background = null
                background = createRoundedBackground()
                typeface = font
                filters = arrayOf(InputFilter.LengthFilter(1)) // Limit input to 1 character
                imeOptions = if (i == numFields - 1) EditorInfo.IME_ACTION_DONE else EditorInfo.IME_ACTION_NEXT
            }

            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        focusNextField(i)
                        checkOTPComplete()
                    } else if (s?.isEmpty() == true) {
                        focusPreviousField(i)
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            addView(editText)
        }
    }

    private fun createRoundedBackground(): Drawable {
        return MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(cornerRadius)
                .build()
        ).apply {
            fillColor = ColorStateList.valueOf(backgroundColor)
            strokeColor = ColorStateList.valueOf(this@OTPView.strokeColor)
            strokeWidth = this@OTPView.strokeWidth
        }
    }

    private fun focusNextField(index: Int) {
        if (index < childCount - 1) {
            val nextField = getChildAt(index + 1) as? EditText
            nextField?.requestFocus()
        }
    }

    private fun focusPreviousField(index: Int) {
        if (index > 0) {
            val prevField = getChildAt(index - 1) as? EditText
            prevField?.requestFocus()
        }
    }

    private fun checkOTPComplete() {
        val otp = getOTP()
        if (otp.length == numFields) {
            onOTPCompleteListener?.invoke(otp)
        }
    }

    fun getOTP(): String {
        val otp = StringBuilder()
        for (i in 0 until numFields) {
            val editText = getChildAt(i) as? EditText
            otp.append(editText?.text.toString())
        }
        return otp.toString()
    }

    fun setOnOTPCompleteListener(listener: (String) -> Unit) {
        onOTPCompleteListener = listener
    }
}