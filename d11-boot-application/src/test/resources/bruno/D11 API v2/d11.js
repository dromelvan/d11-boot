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

const expectToEq = {
  season
}

module.exports = {
  expectToEq
}
