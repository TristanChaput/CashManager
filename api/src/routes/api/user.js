const userController = require('../../controllers/userController');

module.exports = function (app) {
    app.get('/users/:name', userController.show)
}
