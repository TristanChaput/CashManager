const Joi = require('joi');
const User = require('../db/models/userModel');

exports.createAccount = async function (req, res, next) {
    const schema = Joi.object({
        username: Joi.string()
            .alphanum()
            .min(3)
            .max(30)
            .required(),
        password: Joi.string()
            .pattern(new RegExp('^[a-zA-Z0-9]{3,30}$'))
            .required(),
        confirmPassword: Joi.ref('password'),
        email: Joi.string()
            .email()
            .required()
    })
        .with('password', 'confirmPassword');

    try {
        await schema.validateAsync(req.body);

        const user = await User.findOneAndUpdate(
            {
                email: req.body.email
            }, {
                isConnected: true
            });

        if (user) {
            res.redirect('/');
        } else {
            await User.create(req.body);

            res.redirect('/');
        }
    }
    catch (err) {
        next(err);
    }
}

exports.signin = async function (req, res, next) {
    const schema = Joi.object({
        password: Joi.string()
            .pattern(new RegExp('^[a-zA-Z0-9]{3,30}$'))
            .required(),
        email: Joi.string()
            .email()
            .required()
    });

    try {
        await schema.validateAsync(req.body);

        const user = await User.findOneAndUpdate({
            email: req.body.email
        },
            {
                isConnected: true,
            });

        if (user) {
            res.redirect('/');
        } else {
            res.redirect('/login');
        }
    }
    catch (err) {
        next(err);
    }
}

exports.isConnected = async function (req, res, next) {
    const user = await User.findOne({isConnected: true})

    if (!user) {
        res.redirect('/login');
    }
    res.locals.user = user
    next();
}
