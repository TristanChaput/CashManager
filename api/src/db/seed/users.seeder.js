const {Seeder} = require('mongoose-data-seed');
const User = require('../models/userModel');

const data = [{
    name: 'Jason',
    accounts: [1, 2, 3]
}];

class UsersSeeder extends Seeder {

    async shouldRun() {
        return User.countDocuments().exec().then(count => count === 0);
    }

    async run() {
        return User.create(data);
    }
}

module.exports = UsersSeeder;