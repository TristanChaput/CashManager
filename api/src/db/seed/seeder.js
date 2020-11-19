const seeder = require('mongoose-seed');

const db = "mongodb://localhost/cashmanagermatb"

const data = [{
    model: 'User',
    accounts: [
        {

        }
    ]
}]

seeder.connect(db, function () {
    seeder.loadModels([
        "../models/userModel.js",
        "../models/accountModel.js"
    ])
    seeder.clearModels(['User', 'Account'])
    seeder.populateModels(data, function (err, done) {
        if (err) {
            return console.error(err);
        }
        if (done) {
            return console.log('seed done');
        }
        seeder.disconnect();
    })
})
