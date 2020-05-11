//POST A PLAYER

function crear() {
    document.getElementById("fullInfo").innerHTML = "";//para setear a empty la info en html
    document.getElementById("data").innerHTML = "";//para setear a empty la info en html
    document.getElementById("sameRoles").innerHTML = "";//para setear a empty la info en html

    let playerName = document.getElementById("newPlayerName").value;
    let newPlayer = {
        name: playerName,
    };

    let createdPlayer = JSON.stringify(newPlayer);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/dices/players",
        contentType: "application/json",
        dataType: "json",
        data: createdPlayer,
        success: function (data) {

            //createPlayer;
            if (playerName.length != 0) {
                console.log("New player created with name: " + playerName);
                alert("New player created with name: " + playerName + "!");
            } else {
                console.log("New player created with an ANONYMOUS name!");
                alert("New player created with an ANONYMOUS name!");
            }
        },
        error: function () {
            alert("Something went wrong! Maybe that player already exists in our database!")
        }
    });
}


//PUT-EDIT A PLAYER

function update() {
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    let playerID = +document.getElementById("playerIdToModify").value;

    //MODIFICAR PARA QUE PRIMERO ANALICE SI ESE ID EXISTE DENTRO DE DB! Y LUEGO QUE PIDA NEW NAME!

    if (playerID != 0) {

        let playerToModify = {
            id: playerID,
            name: prompt("Please enter the modified name of this player"),
        }

        let modifiedPlayer = JSON.stringify(playerToModify);

        $.ajax({
            type: "PUT",
            url: "http://localhost:8081/dices/players/id",
            contentType: "application/json",
            dataType: "text",
            data: modifiedPlayer,
            success: function (data) {
                if (playerToModify.name.length != 0) {
                    alert("Player name changed to: " + playerToModify.name);
                    console.log("Player name changed to: " + playerToModify.name);
                } else {
                    alert("Player name changed to: ANONYMOUS");
                    console.log("Player name changed to: ANONYMOUS");
                }
            },
            error: function () {
                alert("Something went wrong! Maybe this ID doen'st exists in our database!");
            }
        });

    } else {
        alert("You should enter an ID!");
    }
}


//DELETE PLAYER BY ID

function delete_() {
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    let playerID = +document.getElementById("playerIdToModify").value;

    if (playerID != 0) {

        let playerToDelete = {
            id: playerID,
        }

        let deletedPlayer = JSON.stringify(playerToDelete);

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8081/dices/players/id",
            contentType: "application/json",
            data: deletedPlayer,
            success: function () {
                alert("Player with id: " + playerID + " has been deleted!");
            },
            error: function () {
                alert("Something went wrong! Maybe this ID doen'st exists in our database!");
            }
        });

    } else {
        alert("You should enter an ID!");
    }
}


//DELETE ALL PLAYERS

function delete_All() {
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    $.ajax({
        type: "DELETE",
        url: "http://localhost:8081/dices/players",
        contentType: "application/json",
        success: function () {
            alert("All players have been deleted!");
        },
        error: function () {
            alert("Something went wrong! Maybe this ID doen'st exists in our database!");
        }
    });
}


//GET PLAYER BY ID (we are using a request mapping due to problems caused by GetMapping, so the id is passed through URL)

function getById() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    let playerID = document.getElementById("playerID").value;

    if (playerID != 0) {

        let playerToShow = {
            id: playerID,
        }

        let shownPlayer = $.param(playerToShow);//JSON.stringify(playerToShow);

        $.ajax({
            type: "GET",
            url: "http://localhost:8081/dices/players/" + playerID,
            contentType: "application/json",
            data: shownPlayer,
            success: function (data) {

                document.getElementById("fullInfo").innerHTML += "Complete info: " + "<br />" + "<br />" +
                    "The ID of this player is: " + data.id + "<br />" +
                    "The name of this player is: " + data.name + "<br />" +
                    "The register date of this player is: " + data.registerDate + "<br />" +
                    "The total amount of dice rolls of this player is: " + data.totalDiceRolls + "<br />" +
                    "The total amount of games won of this player is: " + data.gamesWon + "<br />" +
                    "The success rate of this player is: " + data.successRate + "<br />";

                // document.getElementById("data").innerHTML = JSON.stringify(data);
                console.log(data);//data
            },
            error: function () {

                alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
            }
        });
    } else {
        alert("You should enter an ID!");
    }
}


//GET ALL PLAYERS

function getAllPlayers() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dices/players",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {

            for (var j = 0; j < data.PLAYERS.length; j++) {

                document.getElementById("fullInfo").innerHTML += "<h3>Complete info of player " + (j + 1) + ": </h3>" +
                    "The ID of this player is: " + data.PLAYERS[j].idPlayer + "<br />" +
                    "The name of this player is: " + data.PLAYERS[j].name + "<br />" +
                    "The register date of this player is: " + data.PLAYERS[j].registerDate + "<br />" +
                    "The total amount of dice rolls of this player is: " + data.PLAYERS[j].totalDiceRolls + "<br />" +
                    "The total amount of games won of this player is: " + data.PLAYERS[j].gamesWon + "<br />" +
                    "The success rate of this player is: " + data.PLAYERS[j].successRate + "<br />";

                console.log(data.PLAYERS[j]);
            }
        },
        error: function () {

            alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
        }
    });
}


//GET PLAYERS IN RANKING

function getPlayersByRanking() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dices/players/ranking",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {

            for (var j = 0; j < data.RANKING.length; j++) {

                document.getElementById("fullInfo").innerHTML += "<h3>Player in ranking " + (j + 1) + ": </h3>" +
                    "The ID of this player is: " + data.RANKING[j].idPlayer + "<br />" +
                    "The name of this player is: " + data.RANKING[j].name + "<br />" +
                    "The total amount of dice rolls of this player is: " + data.RANKING[j].totalDiceRolls + "<br />" +
                    "The total amount of games won of this player is: " + data.RANKING[j].gamesWon + "<br />" +
                    "The success rate of this player is: " + data.RANKING[j].successRate + "<br />";

                console.log(data.RANKING[j]);
            }
        },
        error: function () {

            alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
        }
    });
}


//GET BEST PLAYER

function getBestPlayer() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dices/players/best",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {

            document.getElementById("fullInfo").innerHTML += "<h3>Best Player: </h3>" +
                "The ID of this player is: " + data.id + "<br />" +
                "The name of this player is: " + data.name + "<br />" +
                "The total amount of dice rolls of this player is: " + data.totalDiceRolls + "<br />" +
                "The total amount of games won of this player is: " + data.gamesWon + "<br />" +
                "The success rate of this player is: " + data.successRate + "<br />";

            console.log(data);

        },
        error: function () {

            alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
        }
    });
}


//GET WORST PLAYER

function getWorstPlayer() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dices/players/worst",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {

            document.getElementById("fullInfo").innerHTML += "<h3>Worst Player: </h3>" +
                "The ID of this player is: " + data.id + "<br />" +
                "The name of this player is: " + data.name + "<br />" +
                "The total amount of dice rolls of this player is: " + data.totalDiceRolls + "<br />" +
                "The total amount of games won of this player is: " + data.gamesWon + "<br />" +
                "The success rate of this player is: " + data.successRate + "<br />";

            console.log(data);

        },
        error: function () {

            alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
        }
    });
}


//GET TOTAL AVERAGE

function getAverage() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dices/players/average",
        contentType: "application/json",
        dataType: "json",
        success: function (data, xhr) {

            document.getElementById("fullInfo").innerHTML += "<h3>The average success of all player is: " + "<h3 style='color: Tomato;'>" + data.averageSuccess + "</h3>" + "</h3>";

            console.log(data, xhr);

        },
        error: function (data, xhr) {

            alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
        }
    });
}


/////////////////////////////////////--GAMES--///////////////////////////////////////////////////


//POST A GAME

function roll() {
    document.getElementById("fullInfo").innerHTML = "";//para setear a empty la info en html
    document.getElementById("data").innerHTML = "";//para setear a empty la info en html
    document.getElementById("sameRoles").innerHTML = "";//para setear a empty la info en html

    let playerID = document.getElementById("playerID2").value;

    if (playerID != 0) {

        $.ajax({
            type: "POST",
            url: "http://localhost:8081/dices/players/" + playerID + "/games",
            contentType: "application/json",
            dataType: "json",
            success: function (data) {

                document.getElementById("fullInfo").innerHTML += "<h3>Game with id " + data.id + ": </h3>" +
                    "The result of each dice in this roll: " + "<h4 style='color: Tomato;'>" +
                    "Dice 1 [" + data.dice1 + "] , Dice 2 [" + data.dice2 +
                    "] , Dice 3[" + data.dice3 + "] , Dice 4[" + data.dice4 +
                    "] , Dice 5[" + data.dice5 + "] , Dice 6[" + data.dice6 + "]" +
                    "<h4 style='color: blue;'> <br /> ¿Game won? " + data.won + "</h4>" +
                    "The ID of this player is: " + data.player.id + "<br />" +
                    "The name of this player is: " + data.player.name + "<br />" +
                    "The total amount of dice rolls of this player is: " + data.player.totalDiceRolls + "<br />" +
                    "The total amount of games won of this player is: " + data.player.gamesWon + "<br />" +
                    "The success rate of this player is: " + data.player.successRate + "<br />";
            },
            error: function () {
                alert("Something went wrong! Maybe that player already exists in our database!")
            }
        });

    } else {
        alert("You should enter an ID!");
    }
}

//GET GAMES BY PLAYER ID (we are using a request mapping due to problems caused by GetMapping, so the id is passed through URL)

function getGamesById() {

    document.getElementById("erase_repeated_results").innerHTML = ""; //to erase info I don't want to store
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    let playerID = document.getElementById("playerID3").value;

    if (playerID != 0) {

        $.ajax({
            type: "GET",
            url: "http://localhost:8081/dices/players/" + playerID + "/games",
            contentType: "application/json",
            success: function (data, xhr) {

                document.getElementById("fullInfo").innerHTML += "<h2>Games of player [" + (data.GAMES[0].name).toUpperCase() + "] with id " + playerID + ": </h2>";

                for (var j = 0; j < data.GAMES.length; j++) {
                    document.getElementById("fullInfo").innerHTML += "<h3>Game with id " + data.GAMES[j].idGame + ": </h3>" +
                        "The result of each dice in this roll: " + "<h4 style='color: Tomato;'>" +
                        "Dice 1 [" + data.GAMES[j].valueDice1 + "] , Dice 2 [" + data.GAMES[j].valueDice2 +
                        "] , Dice 3[" + data.GAMES[j].valueDice3 + "] , Dice 4[" + data.GAMES[j].valueDice4 +
                        "] , Dice 5[" + data.GAMES[j].valueDice5 + "] , Dice 6[" + data.GAMES[j].valueDice6 + "]" +
                        "<h4 style='color: blue;'> <br /> ¿Game won? " + data.GAMES[j].won + "</h4>";
                }
                document.getElementById("fullInfo").innerHTML += "<h3>The total amount of dice rolls of this player is: " + data.GAMES[0].totalDiceRolls + "<br />" +
                    "The total amount of games won of this player is: " + Math.round((data.GAMES[0].totalDiceRolls) * (data.GAMES[0].successRate) / 100) + "<br />" +
                    "The success rate of this player is: " + data.GAMES[0].successRate + "</h3> <br />";

                console.log(data, xhr);
            },
            error: function () {

                alert("Something went wrong! Maybe you are entering parameters not related to our Database!");
            }
        });
    } else {
        alert("You should enter an ID!");
    }
}


//DELETE GAMES OF A PLAYER BY ID

function deleteGames() {
    document.getElementById("fullInfo").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("sameRoles").innerHTML = "";

    let playerID = +document.getElementById("playerIdToDeleteGames").value;

    if (playerID != 0) {

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8081/dices/players/" + playerID + "/games",
            contentType: "application/json",
            success: function () {
                alert("All games of player with id: " + playerID + " has been deleted!");
            },
            error: function () {
                alert("Something went wrong! Maybe this ID doen'st exists in our database!");
            }
        });
    } else {
        alert("You should enter an ID!");
    }
}

/////////////////////////////////////////////////////////////////////////////////////////


