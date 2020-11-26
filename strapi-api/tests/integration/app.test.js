'use strict';

const { afterAll, beforeAll, it, expect } = require('@jest/globals');
const fs = require('fs');
const { setupStrapi } = require('../helpers/strapi');

beforeAll(async (done) => {
  await setupStrapi();

  done();
});

afterAll(async (done) => {
  const dbSettings = strapi.config.get('database.connections.default.settings');

  //delete test database after all tests
  if (dbSettings && dbSettings.connection) {
    const tmpDbFile = `${__dirname}/../${dbSettings.connection}`;

    if (fs.existsSync(tmpDbFile)) {
      fs.unlinkSync(tmpDbFile);
    }
  }
  done();
});

it('strapi is defined', async (done) => {
  expect(strapi).toBeDefined();
  done();
});

require('./user');
require('./product');
require('./cart');
require('./account');
