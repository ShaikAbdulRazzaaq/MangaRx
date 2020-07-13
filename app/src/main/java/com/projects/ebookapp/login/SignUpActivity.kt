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
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.projects.ebookapp.R

class SignUpActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var signUp: Button? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()
        username = findViewById(R.id.et_howCanICallYou)
        password = findViewById(R.id.et_password)
        signUp = findViewById(R.id.btn_signUpToLogin)
        coordinatorLayout = findViewById(R.id.coordinator)
        progressBar = findViewById(R.id.signUpProgress)
        signUp?.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            validateCredentials(username?.text.toString(), password?.text.toString())
        }
    }

    private fun validateCredentials(userName: String, passWord: String) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
            if (TextUtils.isEmpty(userName)) username!!.error = getString(R.string.required_field)
            if (TextUtils.isEmpty(passWord)) password!!.error = getString(R.string.required_field)
        } else {
            firebaseAuth!!.createUserWithEmailAndPassword(userName, passWord).addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    progressBar!!.visibility = View.INVISIBLE
                    Toast.makeText(this, "Signed Up successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    finish()
                } else {
                    progressBar!!.visibility = View.INVISIBLE
                    Snackbar.make(coordinatorLayout!!, "" + task.exception?.message, BaseTransientBottomBar.LENGTH_LONG).show()
                    Log.e(TAG, "validateCredentials: ", task.exception)
                }
            }
        }
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}