package com.huyhieu.libs

import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class BiometricPromptManager(
    private val activity: AppCompatActivity,
) {
    private val resultChannel = Channel<BiometricResult?>()
    val promptResults = resultChannel.receiveAsFlow()

    fun resetPromptResult(){
        resultChannel.trySend(null)
    }
    fun showBiometricPrompt(
        title: String,
        description: String,
        negativeButtonText: String = "Cancel"
    ) {

        val manager = BiometricManager.from(activity)
        val promptInfo = PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setAllowedAuthenticators(authenticators)
        if (Build.VERSION.SDK_INT < 30) {
            promptInfo.setNegativeButtonText(negativeButtonText)
        }
        when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.d(TAG, "canAuthenticate: BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE")
                resultChannel.trySend(BiometricResult.HardwareUnavailable)
                return
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.d(TAG, "canAuthenticate: BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE")
                resultChannel.trySend(BiometricResult.FeatureUnavailable)
                return
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.d(TAG, "canAuthenticate: BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED")
                resultChannel.trySend(BiometricResult.AuthenticationNotSet)
                return
            }

            else -> Unit
        }
        val prompt = BiometricPrompt(activity, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Log.d(TAG, "onAuthenticationError: $errorCode - $errString")
                resultChannel.trySend(BiometricResult.AuthenticationError(errorCode, errString))
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d(TAG, "onAuthenticationFailed")
                resultChannel.trySend(BiometricResult.AuthenticationFailed)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.d(TAG, "onAuthenticationSucceeded: $result")
                resultChannel.trySend(BiometricResult.AuthenticationSuccess(result))
            }
        })
        prompt.authenticate(promptInfo.build())
    }

    fun checkBiometricSupport(manager: BiometricManager = BiometricManager.from(activity)): Boolean {
        val canAuthenticate = manager.canAuthenticate(authenticators)
        Log.d(TAG, "checkBiometricSupport: $canAuthenticate")
        return when (canAuthenticate) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> false
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> false
            else -> true
        }
    }

    companion object {
        private const val TAG = "BiometricPromptManager"
        val authenticators = if (Build.VERSION.SDK_INT >= 30) {
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        } else {
            BIOMETRIC_STRONG
        }
    }
}

sealed interface BiometricResult {
    data object HardwareUnavailable : BiometricResult
    data object FeatureUnavailable : BiometricResult
    data object AuthenticationNotSet : BiometricResult

    data class AuthenticationError(val errorCode: Int, val errString: CharSequence) :
        BiometricResult

    data object AuthenticationFailed : BiometricResult
    data class AuthenticationSuccess(val result: BiometricPrompt.AuthenticationResult) :
        BiometricResult
}