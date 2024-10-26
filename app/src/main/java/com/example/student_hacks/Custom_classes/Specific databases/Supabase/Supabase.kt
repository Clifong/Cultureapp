package com.example.student_hacks.Custom_classes.Specific
import com.example.student_hacks.Custom_classes.Database.Database
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://ptkfyzmnzfezfookyczt.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InB0a2Z5em1uemZlemZvb2t5Y3p0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjk5MTQyOTQsImV4cCI6MjA0NTQ5MDI5NH0.8kJgO-TWjIUWc33Myw7R5FU5_dXOaXckQKmj3dhLbm8"
) {
    install(Postgrest)
}

class Supabase : Database(supabase) {

}