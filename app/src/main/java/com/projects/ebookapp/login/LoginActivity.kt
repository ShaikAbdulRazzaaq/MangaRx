package com.projects.ebookapp.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.projects.ebookapp.MainActivity
import com.projects.ebookapp.R

class LoginActivity : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null
    private var name: EditText? = null
    private var loginPassword: EditText? = null
    private var login: Button? = null
    private var signup: Button? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var progressBar: ProgressBar? = null
    private var facebookLogin: LoginButton? = null
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppEventsLogger.activateApp(application)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        loginPassword = findViewById(R.id.loginPassword)
        name = findViewById(R.id.et_yourName)
        login = findViewById(R.id.btn_login)
        signup = findViewById(R.id.btn_signUp)
        progressBar = findViewById(R.id.loginProgress)
        coordinatorLayout = findViewById(R.id.coordinatorLoginPage)
        facebookLogin = findViewById(R.id.facebookLoginButton)
        login?.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            validateCredentials(name?.text.toString(), loginPassword?.text.toString())
        }

        signup?.setOnClickListener { startActivity(Intent(this@LoginActivity, SignUpActivity::class.java)) }
        facebookLogin?.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d(TAG, "The login Result is $result  ")
                accessToken(result?.accessToken)
            }

            override fun onCancel() {
                Snackbar.make(coordinatorLayout!!, "Cancelled", BaseTransientBottomBar.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {
                Log.e(TAG, ": $error")
            }

        })
    }

    private fun accessToken(accessToken: AccessToken?) {
        val authCredential = FacebookAuthProvider.getCredential(accessToken?.token.toString())
        firebaseAuth?.signInWithCredential(authCredential)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                progressBar?.visibility = View.VISIBLE
                Log.d(TAG, "signInWithCredential:success")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(coordinatorLayout!!, "" + task.exception?.message, BaseTransientBottomBar.LENGTH_LONG).show()
            }
        }
    }

    private fun validateCredentials(username: String, password: String) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(username)) name!!.error = getString(R.string.required_field)
            if (TextUtils.isEmpty(password)) loginPassword!!.error = getString(R.string.required_field)
        } else {
            firebaseAuth!!.signInWithEmailAndPassword(username, password).addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    progressBar!!.visibility = View.INVISIBLE
                    Toast.makeText(this, "Login SuccessFull", Toast.LENGTH_SHORT).show()
                    firebaseUser = firebaseAuth!!.currentUser
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    progressBar!!.visibility = View.INVISIBLE
                    Snackbar.make(coordinatorLayout!!, "" + task.exception?.message, BaseTransientBottomBar.LENGTH_LONG).show()
                    Log.e(TAG, "validateCredentials: ", task.exception)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}