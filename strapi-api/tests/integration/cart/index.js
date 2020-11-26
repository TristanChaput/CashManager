'use strict';

const { describe, it, expect } = require('@jest/globals');
const request = require('supertest');
const users = require('../../../data/users_permissions_user.json');
const carts = require('../../../data/carts.json');

describe('cart integration test', () => {
  it('should return user cart', async done => {
    const jwt = strapi.plugins['users-permissions'].services.jwt.issue({
      id: users[0].id,
    });

    request(strapi.server)
      .get('/carts')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${jwt}`)
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body).toBe(carts[0]);
      });

    done();
  });

  it.skip('should add product to cart', async done => {
    await strapi.query('carts').findOne(1);

    request(strapi.server)
      .put('/cart')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .send({
        products: [12]
      })
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body).toHaveLength(carts[0]);
      });

    done();
  });
});
