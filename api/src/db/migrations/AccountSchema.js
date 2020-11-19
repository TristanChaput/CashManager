const { Schema } = require('mongoose');

const AccountSchema = new Schema({
    number: Number,
    amount: {
      type: Number,
      default: 0.0,
    },
    user: {
        type: Schema.Types.ObjectID,
        ref: 'User',
    }
}, { timestamps: true })

module.exports = AccountSchema;
