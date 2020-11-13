const { Schema } = require('mongoose');

const UserSchema = new Schema({
    username: {
        type: String,
        unique: true,
    },
    email: {
        type: String,
        unique: true,
    },
    password: String,
    isConnected: {
        type: Boolean,
        default: false,
    },
});

module.exports = UserSchema;
