const User = require('../db/models/userModel');

exports.findOne = async function (name) {
    return User.findOne({ name });
}
