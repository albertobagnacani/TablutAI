package serialization

import aima.core.agent.Action
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class JSONAction(val action: Action) {
    private val gson : Gson

    init{
        //gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        gson = GsonBuilder().create()
    }

    fun serialize() : String{
        return gson.toJson(action)
    }
}

/*
    {
        "from":"e4",
        "to":"f4",
        "turn":"WHITE"
    }
*/