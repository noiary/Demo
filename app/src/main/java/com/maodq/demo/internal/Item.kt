package com.maodq.demo.internal

import java.io.Serializable
import kotlin.reflect.KClass

class Item(val name: String, val clazz: KClass<*>?) : Serializable