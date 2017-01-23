package com.summer.keyboardapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 * describe
 * Created by hui on 2017/1/23.
 */
public class MainActivity extends AppCompatActivity {

    private Context ctx;
    private Activity act;
    private EditText editUsernameRandom;
    private EditText editPswRandom;
    private EditText editUsername;
    private EditText editPsw;
    private KeyboardUtil keyboardUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        act = this;
        editUsernameRandom = (EditText) this.findViewById(R.id.edit_username_random);
        editPswRandom = (EditText) this.findViewById(R.id.edit_psw_random);
        editUsername = (EditText) this.findViewById(R.id.edit_username);
        editPsw = (EditText) this.findViewById(R.id.edit_psw);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Method setShowSoftInputOnFocus = null;
        try {
            setShowSoftInputOnFocus = editUsernameRandom.getClass().getMethod(
                    "setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(editUsernameRandom, false);
            setShowSoftInputOnFocus = editPswRandom.getClass().getMethod(
                    "setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(editPswRandom, false);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        keyboardUtil = new KeyboardUtil(act, ctx);
        keyboardUtil.setEd(editUsernameRandom);
        editUsernameRandom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSysInput();
                keyboardUtil.setEd(editUsernameRandom);
                keyboardUtil.showKeyboard();
                return false;
            }
        });

        editPswRandom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSysInput();
                keyboardUtil.setEd(editPswRandom);
                keyboardUtil.showKeyboard();
                return false;
            }
        });
        editUsername.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyboardUtil.hideKeyboard();
                return false;
            }
        });
        editPsw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyboardUtil.hideKeyboard();
                return false;
            }
        });
    }

    private void hideSysInput() {
        if (this.getWindow().getDecorView().getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) this
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
