package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins
package common.model

sealed trait HexagonalPart {}

sealed trait HexagonalOuter extends HexagonalPart {}

sealed trait HexagonalInfra extends HexagonalPart {}

sealed trait HexagonalLayer extends HexagonalPart {}

case object LoggingAdapter extends HexagonalOuter

case object StreamingAdapter extends HexagonalInfra

case object TransactionalAdapter extends HexagonalInfra

case object ObjectPersistenceAdapter extends HexagonalInfra

case object Edge extends HexagonalLayer

case object ValueAdd extends HexagonalLayer

case object Core extends HexagonalLayer
