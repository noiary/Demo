package com.maodq.demo.internal

import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.reflect.KClass

class Item(val name: String, val clazz: KClass<*>?) : Serializable
class ItemF(val name:String,val fragment:Fragment)