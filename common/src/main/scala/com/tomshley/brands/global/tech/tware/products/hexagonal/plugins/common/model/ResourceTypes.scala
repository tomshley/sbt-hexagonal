package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.model


object ResourceTypes extends Enumeration {
  type EnumType = Value

  val Untyped = Value("")
  val JavaScript = Value(".js")
  val CSS = Value(".css")
  val LESS = Value(".less")
  val Template = Value(".tpl")
  val Proto = Value(".proto")
  val Feature = Value(".feature")
}
