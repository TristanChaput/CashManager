const mongoose = require('mongoose');

module.exports = async function () {
    if (process.env.NODE !== 'test') {
        await mongoose.connect('mongodb://localhost/cashmanagermatb', {
            useNewUrlParser: true,
            useUnifiedTopology: true,
            useFindAndModify: false,
            useCreateIndex: true
        }, function (err) {
            if (err) {
                throw err;
            }
            console.log('db connected !')
        })
    }
}
