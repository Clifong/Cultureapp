package com.example.student_hacks.Custom_classes.Exceptions

open class DatabaseException : Exception() {

}

class SignInFailException : DatabaseException() {

}

class SignUpFailException : DatabaseException() {

}