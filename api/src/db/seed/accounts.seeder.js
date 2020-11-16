const {Seeder} = require('mongoose-data-seed');
const User = require('../models/userModel');

const data = [{
            number: 230071,
            amount: 0.0
        },
        {
            number: 873330,
            amount: 0.0
        },
        {
            number: 664552,
            amount: 0.0
        }];

class AccountsSeeder extends Seeder {

    async shouldRun() {
        return User.countDocuments().exec().then(count => count === 0);
    }

    async run() {
        return User.create(data);
    }
}

module.exports = AccountsSeeder;