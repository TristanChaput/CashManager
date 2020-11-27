'use strict';

const { describe, it, expect } = require('@jest/globals');
const request = require('supertest');
const users = require('../../../data/users_permissions_user.json');
const carts = require('../../../data/carts.json');
const products = require('../../../data/products.json');

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

  it('should add product to cart', async done => {
    request(strapi.server)
      .get('/carts/add/1')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .expect('Content-Type', /json/)
      .expect(404)
      .then(data => {
        expect(data.body.cart.products).toHaveLength(1);
        expect(data.body.cart.products).toBe(products[0]);
      });

    done();
  });

  /*it('should add product to cart', async done => {
    request(strapi.server)
      .put('/carts')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .send({
        products: [12, 11]
      })
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body.carts).toHaveLength(2);
      });

    done();
  });*/
});
