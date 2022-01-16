package com.bimabagaskhoro.asigmentkompas.utils

import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser
import java.util.ArrayList

object DataDummy {
    fun generateDummyUser(): ArrayList<ItemsUser> {
        val users = ArrayList<ItemsUser>()
        users.add(
            ItemsUser(
                "bimabagaskhoro",
                "https://avatars.githubusercontent.com/u/54490868?v=4"
            )
        )
        return users
    }

    fun generateDummyDetailUser(): ItemsDetail {
        return ItemsDetail(
            "bimabagaskhoro",
            "https://avatars.githubusercontent.com/u/54490868?v=4",
            "touché",
            "Bima Bagaskhoro"
        )
    }

    fun generateDummyRepos(): ArrayList<ItemRepos> {
        val users = ArrayList<ItemRepos>()
        users.add(
            ItemRepos(
                "asd",
                "asd",
                1,
                "asd",
                "bimabagaskhoro"
            )
        )
        return users
    }

}