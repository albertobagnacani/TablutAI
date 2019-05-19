package serialization

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class JSONName(val s: String) {
    private val gson : Gson

    init{
        //gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        gson = GsonBuilder().create()
    }

    fun serialize() : String{
        return gson.toJson(s)
    }
}

/*
    {
        "from":"e4",
        "to":"f4",
        "turn":"WHITE"
    }
*/