package com.example.student_hacks.Custom_classes.Exceptions

open class DatabaseException(message : String) : Exception(message) {
}

class SignInFailException : DatabaseException("Email or password wrong!") {

}

class SignUpFailException : DatabaseException("Email already in use!") {

}

class FailToUpdateProfileException : DatabaseException("Fail tp update profile") {

}

class FailToAddDiaryException : DatabaseException("Fail to add diary") {

}