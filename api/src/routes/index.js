const { Router } = require('express');
const user = require('./api/user');

module.exports = function () {
    const app = Router();

    user(app);

    return app;
}