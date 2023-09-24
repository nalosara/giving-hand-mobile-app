package com.example.givinghand.datasource

import com.example.givinghand.R
import com.example.givinghand.data.Action
import com.example.givinghand.data.Category
import com.example.givinghand.data.User

object DataSource {

    val Categories = listOf(
        Category(
            id = 1,
            name = "Donate",
            picture = R.drawable.donate
        ),
        Category(
            id = 2,
            name = "Animal Care",
            picture = R.drawable.animal
        ),
        Category(
            id = 3,
            name = "Environment",
            picture = R.drawable.env
        ),
        Category(
            id = 4,
            name = "Social",
            picture = R.drawable.social
        ),
    )

    var ActionItems = mutableListOf(
        Action(
            id = 1,
            name = "Red Cross",
            address = "Kolodvorska 12, Sarajevo 71000",
            description = "Collecting donations for building a women's shelter.",
            current_volunteers = 0,
            category_id = 1,
            max_volunteers = 2147483647,
            date = "Unknown"
        ),
        Action(
            id = 2,
            name = "Heart for Children",
            address = "Zmaja od Bosne bb, 71000 Sarajevo",
            description = "Donate to help with medical bills for children in need.",
            current_volunteers = 0,
            category_id = 1,
            max_volunteers = 2147483647,
            date = "Unknown"
        ),
        Action(
            id = 3,
            name = "Save Sarajevo",
            address = "Hamdije Čemerlića 39a, 71000 Sarajevo",
            description = "Donate to  to provide relief from the conditions of extreme poverty beset on the city of Sarajevo.",
            current_volunteers = 0,
            category_id = 1,
            max_volunteers = 2147483647,
            date = "Unknown"
        ),
        Action(
            id = 4,
            name = "SOS Children's Villages",
            address = "Ahmeda Muradbegovića 1c, Sarajevo",
            description = "Donate to help children and youth who are in risk and without parental guidance.",
            current_volunteers = 0,
            category_id = 1,
            max_volunteers = 2147483647,
            date = "Unknown"
        ),
        Action(
            id = 5,
            name = "Noah's Ark",
            address = "Franjčevićeva ul., 10361, Dumovec, Croatia",
            description = "Accompany us to walk and play with animals living in our shelter",
            current_volunteers = 0,
            max_volunteers = 50,
            category_id = 2,
            date = "25.05.2023 12:00-16:00"

        ),
        Action(
            id = 6,
            name = "ZOO Zagreb Volunteering",
            address = "Ulica Fakultetsko dobro 1, 10000 Zagreb",
            description = "Volunteer in our ZOO and help feed and care for our animals",
            current_volunteers = 0,
            max_volunteers = 100,
            category_id = 2,
            date = "17.07.2023 10:00-18:00"

        ),
        Action(
            id = 7,
            name = "Animal Friend",
            address = "Jurišićeva 25, 10000 Zagreb",
            description = "Join us in caring for animals and making the world cruelty-free, safe space for them",
            current_volunteers = 0,
            max_volunteers = 20,
            category_id = 2,
            date = "20.06.2023 16:00-19:30"
        ),
        Action(
            id = 8,
            name = "Volunteering in Earth Network",
            address = "Maršala Tita 21, Sarajevo",
            description = "Join a project of cleaning Sarajevo from trash and help the community.",
            current_volunteers = 0,
            max_volunteers = 15,
            category_id = 3,
            date = "14.08.2023 10:00-16:30"
        ),
        Action(
            id = 9,
            name = "Living Healthy",
            address = "Rockefellerova 7, 10000 Zagreb",
            description = "Become a part of a group of volunteers with the aim of planting healthy, young trees in the parks of Zagreb.",
            current_volunteers = 0,
            max_volunteers = 20,
            category_id = 3,
            date = "17.06.2023 14:00-16:30"
        ),
        Action(
            id = 10,
            name = "Prevention Action",
            address = "Josipa Vancaša 21, 71000 Sarajevo",
            description = "Join us in clearing our mountains and rivers from trash that has been accumulating over the years.",
            current_volunteers = 0,
            max_volunteers = 27,
            category_id = 3,
            date = "14.08.2023 10:00-18:00"
        ),
        Action(
            id = 11,
            name = "Let's Do It",
            address = "Tepebašina 7, 71 000 Sarajevo",
            description = "Help us clean our neighborhood and make it a good place for people to live in.",
            current_volunteers = 0,
            max_volunteers = 35,
            category_id = 4,
            date = "22.07.2023 11:00-15:00"
        ),
        Action(
            id = 12,
            name = "Oasis Association Action",
            address = "Sutjeska 18, Sarajevo",
            description = "Join us and help elderly people by doing their shopping and everyday chores.",
            current_volunteers = 0,
            max_volunteers = 30,
            category_id = 4,
            date = "04.07.2023 10:00-12:00"
        ),
        Action(
            id = 13,
            name = "Strebaj.ba Instructor Positions",
            address = "Trg Djece Sarajeva 18, Sarajevo",
            description = "Become a tutor and help children with understand and learn.",
            current_volunteers = 0,
            max_volunteers = 10,
            category_id = 4,
            date = "08.06.2023 08:00-14:00"
        ),
        Action(
            id = 14,
            name = "Sarajevo Film Festival Volunteers",
            address = "CineStar Sarajevo",
            description = "Become a volunteer for SFF and help in festival realization.",
            current_volunteers = 0,
            max_volunteers = 45,
            category_id = 4,
            date = "12.08.2023 09:00-20:30"
        ),

    )

    var AuthorizedUsers = mutableListOf(
        User(
            id = 1,
            username = "saranalo",
            password = "test123",
            email = "saranalo@gmail.com",
            name = "Sara Nalo",
            address = "Alije Nametka 22, Sarajevo",
            authorized = 1
        ),
        User(
            id = 2,
            username = "aminatpkvc",
            password = "testbest",
            email = "aminatpkvs@gmail.com",
            name = "Amina Tupković",
            address = "Dobrinjska 45",
            authorized = 1
        ),
        User(
            id = 3,
            username = "tijanab",
            password = "test123",
            email = "burtijana@gmail.com",
            name = "Tijana Burazorović",
            address = "Džemala Bijedića 62",
            authorized = 1
        ),
    )




}