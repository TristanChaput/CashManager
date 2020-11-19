const { Schema } = require('mongoose');

const UserSchema = new Schema({
    name: {
        type: String,
        unique: true,
    },
    accounts: [{
        type: Schema.Types.ObjectID,
        ref: 'Account',
    }],
});

module.exports = UserSchema;
