package com.molyavin.mvvm.utils

class IncorrectUserInfoException : Throwable(message = "Incorrect email or password!")
class MissingUserInfoException : Throwable(message = "User mapping has just failed!")