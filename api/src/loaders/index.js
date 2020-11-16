const expressLoader = require('./express');
const mongooseLoader = require('./mongoose');

module.exports = async function (app) {
    await mongooseLoader();
    expressLoader(app);
}
