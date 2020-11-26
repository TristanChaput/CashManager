'use strict';

const { describe, it, expect } = require('@jest/globals');
const request = require('supertest');
const user = require('../../../data/users_permissions_user.json');

describe('user integration test', () => {
  it('should log in the user', async done => {
    request(strapi.server)
      .post('/auth/local')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .send({
        identifier: user[0].email,
        password: 'password',
      })
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body.jwt).toBeDefined();
        expect(data.body.user).toMatchObject({
          id: user[0].id,
          username: user[0].username,
          email: user[0].email,
        });
      });

    done();
  });
});
