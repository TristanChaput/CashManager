const { model } = require('mongoose');
const UserSchema = require('../migrations/UserSchema');

const User = model('User', UserSchema);

module.exports = User;
