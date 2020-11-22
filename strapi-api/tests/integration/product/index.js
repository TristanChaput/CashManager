'use strict';

const { describe, it, expect } = require('@jest/globals');
const request = require('supertest');
const products = require('../../../data/products.json');

describe('product integration test', () => {
  it('should return the list of products', async done => {
    request(strapi.server)
      .get('/products')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body).toBe(products);
      });

    done();
  });

  it('should return the requested product', async done => {
    request(strapi.server)
      .get(`/products/${products[0].id}`)
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body).toBe(products[0]);
      });

    done();
  });
});
