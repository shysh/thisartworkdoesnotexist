package com.shysh.thisartworkdoesnotexist.ui.common

typealias OnItemClickAction<T> = (T) -> Unit
typealias OnItemLongClickAction<T> = (T) -> Unit
typealias Action = () -> Unit
typealias ParametricAction<T> = (T) -> Unit

typealias OnItemClickForPositionAction = OnItemClickAction<Int>
typealias OnItemClickForPositionWithValueAction<V> = OnItemClickAction<Int>
typealias OnItemLongClickForPositionAction = OnItemLongClickAction<Int>