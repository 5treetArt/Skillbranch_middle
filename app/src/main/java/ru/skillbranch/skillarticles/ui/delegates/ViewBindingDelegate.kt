package ru.skillbranch.skillarticles.ui.delegates

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Умеет обрабатывать сгенерированный уже viewBinding класс.
 * Надуть из него вьюху и подставить в необходимом жизненном цикле активити.
 */
class ViewBindingDelegate<T : ViewBinding>(
    private val activity: AppCompatActivity,
    private val initializer: (LayoutInflater)->T
) : ReadOnlyProperty<AppCompatActivity, T>, LifecycleObserver {
    private var _value: T? = null

    init {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (_value == null) {
            _value = initializer(activity.layoutInflater)
        }

        activity.setContentView(_value!!.root)
        activity.lifecycle.removeObserver(this)
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (_value == null) {
            _value = initializer(thisRef.layoutInflater)
        }

        return _value!!
    }
}

inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding(noinline initializer: (LayoutInflater) -> T) =
    ViewBindingDelegate(this, initializer)