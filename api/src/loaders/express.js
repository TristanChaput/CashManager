const express = require("express");
const path = require('path');
const routes = require('../routes');

module.exports = function (app) {
    app.use('/', express.static(path.resolve(__dirname, '../../public')));
    app.set('views', path.resolve(__dirname, '../views'));

    app.use(express.json());
    app.use(express.urlencoded({extended: true}));

    app.use(routes());
}
