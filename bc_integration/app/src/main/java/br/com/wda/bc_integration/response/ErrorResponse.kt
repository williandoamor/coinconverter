package br.com.wda.bc_integration.response

data class ErrorResponse(
    val status: Long,
    val code: String,
    val message: String
)