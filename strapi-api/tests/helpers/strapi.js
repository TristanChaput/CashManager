'use strict';

const Strapi = require('strapi');
const http = require('http');
const { jest: requiredJest } = require('@jest/globals');

let instance;

requiredJest.setTimeout(300000);

async function setupStrapi() {
  if (!instance) {
    instance = Strapi(undefined);
    await instance.load();

    instance.app.use(instance.router.routes()).use(instance.router.allowedMethods());
    instance.server = http.createServer(instance.app.callback());
  }
  return instance;
}

module.exports = { setupStrapi };
