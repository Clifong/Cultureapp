package com.example.student_hacks.Custom_classes.Database

import io.github.jan.supabase.SupabaseClient

abstract class Database(supabase: SupabaseClient) {

    fun getCredential(id: Int) {}

    fun getFriendList(id: Int) {}
}