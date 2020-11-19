const { model } = require('mongoose');
const AccountSchema = require("../migrations/AccountSchema");

const Account = model('Account', AccountSchema);

module.exports = Account;
