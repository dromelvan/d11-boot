function user(user1, user2) {
  expect(user1.id).to.eq(user2.id);
  expect(user1.name).to.eq(user2.name);
  expect(user1.administrator).to.eq(user2.administrator);
}

function country(country1, country2) {
  expect(country1.id).to.eq(country2.id);
  expect(country1.name).to.eq(country2.name);
  expect(country1.iso).to.eq(country2.iso);
}

function stadium(stadium1, stadium2) {
  expect(stadium1.id).to.eq(stadium2.id);
  expect(stadium1.name).to.eq(stadium2.name);
  expect(stadium1.city).to.eq(stadium2.city);
  expect(stadium1.capacity).to.eq(stadium2.capacity);
  expect(stadium1.opened).to.eq(stadium2.opened);
  expect(stadium1.photoFileName).to.eq(stadium2.photoFileName);
}

function team(team1, team2) {
  expect(team1.id).to.eq(team2.id);
  expect(team1.whoscoredId).to.eq(team2.whoscoredId);
  expect(team1.premierLeagueId).to.eq(team2.premierLeagueId);
  expect(team1.name).to.eq(team2.name);
  expect(team1.shortName).to.eq(team2.shortName);
  expect(team1.code).to.eq(team2.code);
  expect(team1.established).to.eq(team2.established);
  expect(team1.color).to.eq(team2.color);
  expect(team1.dummy).to.eq(team2.dummy);
  expect(team1.photoFileName).to.eq(team2.photoFileName);
  expect(team1.url).to.eq(team2.url);
}

function d11Team(d11Team1, d11Team2) {
    expect(d11Team1.id).to.eq(d11Team2.id);
    expect(d11Team1.name).to.eq(d11Team2.name);
    expect(d11Team1.shortName).to.eq(d11Team2.shortName);
    expect(d11Team1.code).to.eq(d11Team2.code);
    expect(d11Team1.dummy).to.eq(d11Team2.dummy);
    expect(d11Team1.photoFileName).to.eq(d11Team2.photoFileName);
}

function position(position1, position2) {
    expect(position1.id).to.eq(position2.id);
    expect(position1.name).to.eq(position2.name);
    expect(position1.code).to.eq(position2.code);
    expect(position1.maxCount).to.eq(position2.maxCount);
    expect(position1.defender).to.eq(position2.defender);
    expect(position1.sortOrder).to.eq(position2.sortOrder);
}

function player(player1, player2) {
    expect(player1.id).to.eq(player2.id);
    expect(player1.whoscoredId).to.eq(player2.whoscoredId);
    expect(player1.premierLeagueId).to.eq(player2.premierLeagueId);
    expect(player1.firstName).to.eq(player2.firstName);
    expect(player1.lastName).to.eq(player2.lastName);
    expect(player1.name).to.eq(player2.name);
    expect(player1.shortName).to.eq(player2.shortName);
    expect(player1.fullName).to.eq(player2.fullName);
    expect(player1.parameterizedName).to.eq(player2.parameterizedName);
    expect(player1.dateOfBirth).to.eq(player2.dateOfBirth);
    expect(player1.height).to.eq(player2.height);
    expect(player1.photoFileName).to.eq(player2.photoFileName);
    expect(player1.verified).to.eq(player2.verified);
}

function playerSearchResult(player1, player2) {
    expect(player1.id).to.eq(player2.id);
    expect(player1.name).to.eq(player2.name);
    expect(player1.teamId).to.eq(player2.teamId);
    expect(player1.teamName).to.eq(player2.teamName);

}

function season(season1, season2) {
  expect(season1.id).to.eq(season2.id);
  expect(season1.name).to.eq(season2.name);
  expect(season1.shortName).to.eq(season2.shortName);
  expect(season1.d11TeamBudget).to.eq(season2.d11TeamBudget);
  expect(season1.d11TeamMaxTransfers).to.eq(season2.d11TeamMaxTransfers);
  expect(season1.status).to.eq(season2.status);
  expect(season1.date).to.eq(season2.date);
  expect(season1.legacy).to.eq(season2.legacy);
}

function matchWeek(matchWeek1, matchWeek2) {
    expect(matchWeek1.id).to.eq(matchWeek2.id);
    expect(matchWeek1.matchWeekNumber).to.eq(matchWeek2.matchWeekNumber);
    //expect(matchWeek1.date).to.eq(matchWeek2.date);
    expect(matchWeek1.elapsed).to.eq(matchWeek2.elapsed);
    expect(matchWeek1.status).to.eq(matchWeek2.status);

    expect(matchWeek1.mostValuablePlayer.points).to.eq(matchWeek2.mostValuablePlayer.points);
    expect(matchWeek1.mostValuablePlayer.goals).to.eq(matchWeek2.mostValuablePlayer.goals);

    player(matchWeek1.mostValuablePlayer.player, matchWeek2.mostValuablePlayer.player);
    team(matchWeek1.mostValuablePlayer.team, matchWeek2.mostValuablePlayer.team);
    d11Team(matchWeek1.mostValuablePlayer.d11Team, matchWeek2.mostValuablePlayer.d11Team);
}

function matchWeekWithoutMVP(matchWeek1, matchWeek2) {
    expect(matchWeek1.id).to.eq(matchWeek2.id);
    expect(matchWeek1.matchWeekNumber).to.eq(matchWeek2.matchWeekNumber);
    //expect(matchWeek1.date).to.eq(matchWeek2.date);
    expect(matchWeek1.elapsed).to.eq(matchWeek2.elapsed);
    expect(matchWeek1.status).to.eq(matchWeek2.status);

    expect(matchWeek1.mostValuablePlayer).be.undefined
}

function match(match1, match2) {
    expect(match1.id).to.eq(match2.id);
    expect(match1.whoscoredId).to.eq(match2.whoscoredId);
    expect(match1.datetime).to.eq(match2.datetime);
    expect(match1.homeTeamGoalsScored).to.eq(match2.homeTeamGoalsScored);
    expect(match1.awayTeamGoalsScored).to.eq(match2.awayTeamGoalsScored);
    expect(match1.previousHomeTeamGoalsScored).to.eq(match2.previousHomeTeamGoalsScored);
    expect(match1.previousAwayTeamGoalsScored).to.eq(match2.previousAwayTeamGoalsScored);
    expect(match1.elapsed).to.eq(match2.elapsed);
    expect(match1.status).to.eq(match2.status);
}

function d11Match(d11Match1, d11Match2) {
    expect(d11Match1.id).to.eq(d11Match2.id);
    expect(d11Match1.datetime).to.eq(d11Match2.datetime);
    expect(d11Match1.homeTeamGoalsScored).to.eq(d11Match2.homeTeamGoalsScored);
    expect(d11Match1.awayTeamGoalsScored).to.eq(d11Match2.awayTeamGoalsScored);
    expect(d11Match1.homeTeamPoints).to.eq(d11Match2.homeTeamPoints);
    expect(d11Match1.awayTeamPoints).to.eq(d11Match2.awayTeamPoints);
    expect(d11Match1.previousHomeTeamGoalsScored).to.eq(d11Match2.previousHomeTeamGoalsScored);
    expect(d11Match1.previousAwayTeamGoalsScored).to.eq(d11Match2.previousAwayTeamGoalsScored);
    expect(d11Match1.previousHomeTeamPoints).to.eq(d11Match2.previousHomeTeamPoints);
    expect(d11Match1.previousAwayTeamPoints).to.eq(d11Match2.previousAwayTeamPoints);

    expect(d11Match1.elapsed).to.eq(d11Match2.elapsed);
    expect(d11Match1.status).to.eq(d11Match2.status);
}

function playerMatchStat(playerMatchStat1, playerMatchStat2) {
    expect(playerMatchStat1.id).to.eq(playerMatchStat2.id);

    expect(playerMatchStat1.playedPosition).to.eq(playerMatchStat2.playedPosition);
    expect(playerMatchStat1.lineup).to.eq(playerMatchStat2.lineup);
    expect(playerMatchStat1.substitutionOnTime).to.eq(playerMatchStat2.substitutionOnTime);
    expect(playerMatchStat1.substitutionOffTime).to.eq(playerMatchStat2.substitutionOffTime);
    expect(playerMatchStat1.goals).to.eq(playerMatchStat2.goals);
    expect(playerMatchStat1.goalAssists).to.eq(playerMatchStat2.goalAssists);
    expect(playerMatchStat1.ownGoals).to.eq(playerMatchStat2.ownGoals);
    expect(playerMatchStat1.goalsConceded).to.eq(playerMatchStat2.goalsConceded);
    expect(playerMatchStat1.yellowCardTime).to.eq(playerMatchStat2.yellowCardTime);
    expect(playerMatchStat1.redCardTime).to.eq(playerMatchStat2.redCardTime);
    expect(playerMatchStat1.manOfTheMatch).to.eq(playerMatchStat2.manOfTheMatch);
    expect(playerMatchStat1.sharedManOfTheMatch).to.eq(playerMatchStat2.sharedManOfTheMatch);
    expect(playerMatchStat1.rating).to.eq(playerMatchStat2.rating);
    expect(playerMatchStat1.points).to.eq(playerMatchStat2.points);

    player(playerMatchStat1.player, playerMatchStat2.player);
    team(playerMatchStat1.team, playerMatchStat2.team);
    d11Team(playerMatchStat1.d11Team, playerMatchStat2.d11Team);
    match(playerMatchStat1.match, playerMatchStat2.match);
    position(playerMatchStat1.position, playerMatchStat2.position);
}

function playerSeasonStat(playerSeasonStat1, playerSeasonStat2) {
    expect(playerSeasonStat1.id).to.eq(playerSeasonStat2.id);

    expect(playerSeasonStat1.shirtNumber).to.eq(playerSeasonStat2.shirtNumber);
    expect(playerSeasonStat1.fee).to.eq(playerSeasonStat2.fee);
    expect(playerSeasonStat1.winCount).to.eq(playerSeasonStat2.winCount);
    expect(playerSeasonStat1.ranking).to.eq(playerSeasonStat2.ranking);
    expect(playerSeasonStat1.points).to.eq(playerSeasonStat2.points);
    expect(playerSeasonStat1.formPoints).to.eq(playerSeasonStat2.formPoints);
    expect(playerSeasonStat1.pointsPerAppearance).to.eq(playerSeasonStat2.pointsPerAppearance);
    expect(playerSeasonStat1.goals).to.eq(playerSeasonStat2.goals);
    expect(playerSeasonStat1.goalAssists).to.eq(playerSeasonStat2.goalAssists);
    expect(playerSeasonStat1.ownGoals).to.eq(playerSeasonStat2.ownGoals);
    expect(playerSeasonStat1.goalsConceded).to.eq(playerSeasonStat2.goalsConceded);
    expect(playerSeasonStat1.cleanSheets).to.eq(playerSeasonStat2.cleanSheets);
    expect(playerSeasonStat1.yellowCards).to.eq(playerSeasonStat2.yellowCards);
    expect(playerSeasonStat1.redCards).to.eq(playerSeasonStat2.redCards);
    expect(playerSeasonStat1.substitutionsOn).to.eq(playerSeasonStat2.substitutionsOn);
    expect(playerSeasonStat1.substitutionsOff).to.eq(playerSeasonStat2.substitutionsOff);
    expect(playerSeasonStat1.manOfTheMatch).to.eq(playerSeasonStat2.manOfTheMatch);
    expect(playerSeasonStat1.sharedManOfTheMatch).to.eq(playerSeasonStat2.sharedManOfTheMatch);
    expect(playerSeasonStat1.rating).to.eq(playerSeasonStat2.rating);
    expect(playerSeasonStat1.gamesStarted).to.eq(playerSeasonStat2.gamesStarted);
    expect(playerSeasonStat1.gamesSubstitute).to.eq(playerSeasonStat2.gamesSubstitute);
    expect(playerSeasonStat1.gamesDidNotParticipate).to.eq(playerSeasonStat2.gamesDidNotParticipate);
    expect(playerSeasonStat1.minutesPlayed).to.eq(playerSeasonStat2.minutesPlayed);

    expect(playerSeasonStat1.formMatchPoints.length).to.eq(playerSeasonStat2.formMatchPoints.length);
    for (let i = 0; i < playerSeasonStat1.formMatchPoints.length; ++i) {
        expect(playerSeasonStat1.formMatchPoints[i]).to.eq(playerSeasonStat2.formMatchPoints[i]);
    }

    player(playerSeasonStat1.player, playerSeasonStat2.player);
    team(playerSeasonStat1.team, playerSeasonStat2.team);
    d11Team(playerSeasonStat1.d11Team, playerSeasonStat2.d11Team);
    season(playerSeasonStat1.season, playerSeasonStat2.season);
    position(playerSeasonStat1.position, playerSeasonStat2.position);
}

function teamSeasonStat(teamSeasonStat1, teamSeasonStat2) {
    expect(teamSeasonStat1.id).to.eq(teamSeasonStat2.id);
    expect(teamSeasonStat1.winCount).to.eq(teamSeasonStat2.winCount);
    expect(teamSeasonStat1.matchesPlayed).to.eq(teamSeasonStat2.matchesPlayed);
    expect(teamSeasonStat1.matchesWon).to.eq(teamSeasonStat2.matchesWon);
    expect(teamSeasonStat1.matchesDrawn).to.eq(teamSeasonStat2.matchesDrawn);
    expect(teamSeasonStat1.matchesLost).to.eq(teamSeasonStat2.matchesLost);
    expect(teamSeasonStat1.goalsFor).to.eq(teamSeasonStat2.goalsFor);
    expect(teamSeasonStat1.goalsAgainst).to.eq(teamSeasonStat2.goalsAgainst);
    expect(teamSeasonStat1.goalDifference).to.eq(teamSeasonStat2.goalDifference);
    expect(teamSeasonStat1.points).to.eq(teamSeasonStat2.points);
    expect(teamSeasonStat1.formPoints).to.eq(teamSeasonStat2.formPoints);
    expect(teamSeasonStat1.ranking).to.eq(teamSeasonStat2.ranking);
    expect(teamSeasonStat1.previousRanking).to.eq(teamSeasonStat2.previousRanking);

    expect(teamSeasonStat1.formMatchPoints.length).to.eq(teamSeasonStat2.formMatchPoints.length);
    for (let i = 0; i < teamSeasonStat1.formMatchPoints.length; ++i) {
        expect(teamSeasonStat1.formMatchPoints[i]).to.eq(teamSeasonStat2.formMatchPoints[i]);
    }

    team(teamSeasonStat1.team, teamSeasonStat2.team);
    season(teamSeasonStat1.season, teamSeasonStat2.season);
}

function d11TeamSeasonStat(d11TeamSeasonStat1, d11TeamSeasonStat2) {
    expect(d11TeamSeasonStat1.id).to.eq(d11TeamSeasonStat2.id);

    expect(d11TeamSeasonStat1.winCount).to.eq(d11TeamSeasonStat2.winCount);
    expect(d11TeamSeasonStat1.matchesPlayed).to.eq(d11TeamSeasonStat2.matchesPlayed);
    expect(d11TeamSeasonStat1.matchesWon).to.eq(d11TeamSeasonStat2.matchesWon);
    expect(d11TeamSeasonStat1.matchesDrawn).to.eq(d11TeamSeasonStat2.matchesDrawn);
    expect(d11TeamSeasonStat1.matchesLost).to.eq(d11TeamSeasonStat2.matchesLost);
    expect(d11TeamSeasonStat1.goalsFor).to.eq(d11TeamSeasonStat2.goalsFor);
    expect(d11TeamSeasonStat1.goalsAgainst).to.eq(d11TeamSeasonStat2.goalsAgainst);
    expect(d11TeamSeasonStat1.goalDifference).to.eq(d11TeamSeasonStat2.goalDifference);
    expect(d11TeamSeasonStat1.points).to.eq(d11TeamSeasonStat2.points);
    expect(d11TeamSeasonStat1.formPoints).to.eq(d11TeamSeasonStat2.formPoints);
    expect(d11TeamSeasonStat1.ranking).to.eq(d11TeamSeasonStat2.ranking);
    expect(d11TeamSeasonStat1.previousRanking).to.eq(d11TeamSeasonStat2.previousRanking);

    expect(d11TeamSeasonStat1.formMatchPoints.length).to.eq(d11TeamSeasonStat2.formMatchPoints.length);
    for (let i = 0; i < d11TeamSeasonStat1.formMatchPoints.length; ++i) {
        expect(d11TeamSeasonStat1.formMatchPoints[i]).to.eq(d11TeamSeasonStat2.formMatchPoints[i]);
    }

    d11Team(d11TeamSeasonStat1.d11Team, d11TeamSeasonStat2.d11Team);
    season(d11TeamSeasonStat1.season, d11TeamSeasonStat2.season);
}

function transferWindow(transferWindow1, transferWindow2) {
    expect(transferWindow1.id).to.eq(transferWindow2.id);
    expect(transferWindow1.transferWindowNumber).to.eq(transferWindow2.transferWindowNumber);
    expect(transferWindow1.draft).to.eq(transferWindow2.draft);
    expect(transferWindow1.status).to.eq(transferWindow2.status);
    //expect(transferWindow1.datetime).to.eq(transferWindow2.datetime);
}

function transferDay(transferDay1, transferDay2) {
    expect(transferDay1.id).to.eq(transferDay2.id);
    expect(transferDay1.transferDayNumber).to.eq(transferDay2.transferDayNumber);
    expect(transferDay1.status).to.eq(transferDay2.status);
    //pm.expect(transferDay1.datetime).to.eq(transferDay2.datetime);
}

function transferListing(transferListing1, transferListing2) {
    expect(transferListing1.id).to.eq(transferListing2.id);
    expect(transferListing1.ranking).to.eq(transferListing2.ranking);
    expect(transferListing1.points).to.eq(transferListing2.points);
    expect(transferListing1.formPoints).to.eq(transferListing2.formPoints);
    expect(transferListing1.pointsPerAppearance).to.eq(transferListing2.pointsPerAppearance);
    expect(transferListing1.goals).to.eq(transferListing2.goals);
    expect(transferListing1.goalAssists).to.eq(transferListing2.goalAssists);
    expect(transferListing1.ownGoals).to.eq(transferListing2.ownGoals);
    expect(transferListing1.goalsConceded).to.eq(transferListing2.goalsConceded);
    expect(transferListing1.cleanSheets).to.eq(transferListing2.cleanSheets);
    expect(transferListing1.yellowCards).to.eq(transferListing2.yellowCards);
    expect(transferListing1.redCards).to.eq(transferListing2.redCards);
    expect(transferListing1.substitutionsOn).to.eq(transferListing2.substitutionsOn);
    expect(transferListing1.substitutionsOff).to.eq(transferListing2.substitutionsOff);
    expect(transferListing1.manOfTheMatch).to.eq(transferListing2.manOfTheMatch);
    expect(transferListing1.sharedManOfTheMatch).to.eq(transferListing2.sharedManOfTheMatch);
    expect(transferListing1.rating).to.eq(transferListing2.rating);
    expect(transferListing1.gamesStarted).to.eq(transferListing2.gamesStarted);
    expect(transferListing1.gamesSubstitute).to.eq(transferListing2.gamesSubstitute);
    expect(transferListing1.gamesDidNotParticipate).to.eq(transferListing2.gamesDidNotParticipate);
    expect(transferListing1.minutesPlayed).to.eq(transferListing2.minutesPlayed);
    expect(transferListing1.newPlayer).to.eq(transferListing2.newPlayer);

    expect(transferListing1.formMatchPoints.length).to.eq(transferListing2.formMatchPoints.length);
    for (let i = 0; i < transferListing1.formMatchPoints.length; ++i) {
        expect(transferListing1.formMatchPoints[i]).to.eq(transferListing2.formMatchPoints[i]);
    }

    player(transferListing1.player, transferListing2.player);
    team(transferListing1.team, transferListing2.team);
    d11Team(transferListing1.d11Team, transferListing2.d11Team);
    position(transferListing1.position, transferListing2.position);
}

function transferBid(transferBid1, transferBid2) {
    expect(transferBid1.id).to.eq(transferBid2.id);
    expect(transferBid1.playerRanking).to.eq(transferBid2.playerRanking);
    expect(transferBid1.d11TeamRanking).to.eq(transferBid2.d11TeamRanking);
    expect(transferBid1.fee).to.eq(transferBid2.fee);
    expect(transferBid1.activeFee).to.eq(transferBid2.activeFee);
    expect(transferBid1.successful).to.eq(transferBid2.successful);

    player(transferBid1.player, transferBid2.player);
    d11Team(transferBid1.d11Team, transferBid2.d11Team);
}

function transfer(transfer1, transfer2) {
    expect(transfer1.id).to.eq(transfer2.id);
    expect(transfer1.fee).to.eq(transfer2.fee);

    transferDay(transfer1.transferDay, transfer2.transferDay);
    player(transfer1.player, transfer2.player);
    d11Team(transfer1.d11Team, transfer2.d11Team);
}

const expectToEq = {
    user,
    country,
    stadium,
    team,
    d11Team,
    position,
    player,
    playerSearchResult,
    season,
    matchWeek,
    matchWeekWithoutMVP,
    match,
    d11Match,
    playerMatchStat,
    playerSeasonStat,
    teamSeasonStat,
    d11TeamSeasonStat,
    transferWindow,
    transferDay,
    transferListing,
    transferBid,
    transfer
}

module.exports = {
    expectToEq
}
